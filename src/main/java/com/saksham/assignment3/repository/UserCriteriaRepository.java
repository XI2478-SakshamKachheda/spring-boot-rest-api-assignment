package com.saksham.assignment3.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.saksham.assignment3.model.User;
import com.saksham.assignment3.model.UserPage;
import com.saksham.assignment3.model.UserSearchCriteria;

@Repository
public class UserCriteriaRepository {

	private final EntityManager entityManager;
	
	private final CriteriaBuilder criteriaBuilder;

	public UserCriteriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
	
	public Page<User> findAllWithFilters(UserPage userPage, 
			UserSearchCriteria userSearchCriteria) {
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		Predicate predicate = getPredicate(userSearchCriteria, userRoot);
		criteriaQuery.where(predicate);
		setOrder(userPage, criteriaQuery, userRoot);
		
		TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(userPage.getPageNumber() * userPage.getPageSize());
		typedQuery.setMaxResults(userPage.getPageSize());
		
		Pageable pageable = getPageable(userPage);
		
		Long usersCount = getUsersCount(predicate);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, usersCount);
	}

	private Predicate getPredicate(UserSearchCriteria userSearchCriteria, 
			Root<User> userRoot) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(userSearchCriteria.getPosh_status())) {
			predicates.add(
						criteriaBuilder.like(userRoot.get("posh_status"), 
								"%" +userSearchCriteria.getPosh_status()+ "%")
					);
		}
		if(Objects.nonNull(userSearchCriteria.getXfc_status())) {
			predicates.add(
						criteriaBuilder.like(userRoot.get("xfc_status"),
								"%" +userSearchCriteria.getXfc_status()+ "%")
					);
		}
		if(Objects.nonNull(userSearchCriteria.getIsms_status())) {
			predicates.add(
						criteriaBuilder.like(userRoot.get("isms_status"), 
								"%" +userSearchCriteria.getIsms_status()+ "%")
					);
		}
		if(Objects.nonNull(userSearchCriteria.getEmp_status())) {
			predicates.add(
						criteriaBuilder.like(userRoot.get("emp_status"), 
								"%" +userSearchCriteria.getEmp_status()+ "%")
					);
		}
		if(Objects.nonNull(userSearchCriteria.getBgv_status())) {
			predicates.add(
						criteriaBuilder.like(userRoot.get("bgv_status"), 
								"%" +userSearchCriteria.getBgv_status()+ "%")
					);
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	private void setOrder(UserPage userPage, 
			CriteriaQuery<User> criteriaQuery, 
			Root<User> userRoot) {
		if(userPage.getSortDirection().equals(Sort.Direction.ASC)) {
			criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(userPage.getSortBy())));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(userPage.getSortBy())));

		}
	}
	
	private Pageable getPageable(UserPage userPage) {
		Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
		return PageRequest.of(userPage.getPageNumber(), userPage.getPageSize(), sort);
	}
	
	private Long getUsersCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<User> countRoot = countQuery.from(User.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getSingleResult();
	}

}
