package com.github.bordertech.corpdir.jpa.readonly.common.svc;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.readonly.BasicVersionTreeReadOnlyService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionableKeyId;
import com.github.bordertech.corpdir.jpa.common.version.ItemTreeVersion;
import java.util.Collections;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;


/**
 * Tree Entity read-only service.
 *
 * @param <A> the API object type
 * @param <U> the versionable type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicVersionTreeReadOnlyService<A extends ApiTreeable & ApiVersionable, U extends ItemTreeVersion<P, U>, P extends PersistVersionableKeyId<P, U>> extends JpaBasicVersionKeyIdReadOnlyService<A, U, P> implements BasicVersionTreeReadOnlyService<A> {

	@Override
	public DataResponse<List<A>> getRootItems() {
		return getRootItems(getCurrentVersionId());
	}

	@Override
	public DataResponse<List<A>> getSubs(final String keyId) {
		return getSubs(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<List<A>> getSubs(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			P entity = getEntity(em, keyId);
			ItemTreeVersion tree = entity.getVersion(versionId);
			List<A> list;
			if (tree == null) {
				list = Collections.emptyList();
			} else {
				list = getMapper().convertEntitiesToApis(em, tree.getChildrenItems(), versionId);
			}
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	@Override
	public DataResponse<List<A>> getRootItems(final Long versionId) {
		EntityManager em = getEntityManager();
		try {
			List<P> rows = handleRootItems(em, versionId);
			List<A> list = getMapper().convertEntitiesToApis(em, rows, versionId);
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}

	protected List<P> handleRootItems(final EntityManager em, final Long versionId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT E.* FROM ").append(getEntityTableName()).append(" AS E");
		sql.append(" LEFT JOIN ").append(getVersionTableName()).append(" AS V");
		sql.append(" ON E.id = V.item_id");
		sql.append(" AND V.versionCtrl_id = ").append(" :vid ");
		sql.append(" WHERE V.item_id IS NULL");
		sql.append(" OR (");
		sql.append(" V.item_id IS NOT NULL");
		sql.append(" AND V.parent_item_id IS NULL");
		sql.append(" )");
		sql.append(" ORDER BY E.description");
		Query qry = em.createNativeQuery(sql.toString(), getEntityClass());
		qry.setParameter("vid", versionId);
		return qry.getResultList();
	}

	protected String getEntityTableName() {
		Table tbl = getEntityClass().getAnnotation(Table.class);
		return tbl.name();
	}

	protected String getVersionTableName() {
		Table tbl = getVersionEntityClass().getAnnotation(Table.class);
		return tbl.name();
	}
}
