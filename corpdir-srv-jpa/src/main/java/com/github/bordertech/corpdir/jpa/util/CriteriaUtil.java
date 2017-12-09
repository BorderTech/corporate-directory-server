package com.github.bordertech.corpdir.jpa.util;

import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdObject;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Criteria helper.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public final class CriteriaUtil {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private CriteriaUtil() {
		// prevent instatiation
	}

	public static <T extends PersistKeyIdObject> Predicate createSearchIDCriteria(final CriteriaBuilder cb, final Root<T> root, final Long id) {
		Predicate p1 = cb.equal(root.<Long>get("id"), id);
		return p1;
	}

	/**
	 *
	 * @param <T> the entity type
	 * @param cb the criteria builder
	 * @param root the root entity
	 * @param search the search text
	 * @return the predicate search
	 */
	public static <T extends PersistKeyIdObject> Predicate createSearchTextCriteria(final CriteriaBuilder cb, final Root<T> root, final String search) {
		Predicate p1 = cb.like(root.<String>get("description"), "%" + search + "%");
		Predicate p2 = cb.like(root.<String>get("businessKey"), "%" + search + "%");
		Predicate or = cb.or(p1, p2);
		return or;
	}

	/**
	 *
	 * @param <T> the entity type
	 * @param cb the criteria builder
	 * @param root the root entity
	 * @return the default order by
	 */
	public static <T extends PersistKeyIdObject> Order getDefaultOrderBy(final CriteriaBuilder cb, final Root<T> root) {
		return cb.asc(root.get("description"));
	}

	/**
	 * Create an AND predicate with the list of predicates.
	 *
	 * @param <T> the entity type
	 * @param cb the criteria builder
	 * @param predicates the list of predicates
	 * @return the predicates as an AND
	 */
	public static <T extends PersistKeyIdObject> Predicate createAndCriteria(final CriteriaBuilder cb, final List<Predicate> predicates) {
		if (predicates == null || predicates.isEmpty()) {
			return null;
		}
		if (predicates.size() == 1) {
			return predicates.get(0);
		}
		Predicate pred = cb.and(predicates.toArray(new Predicate[]{}));
		return pred;
	}

}
