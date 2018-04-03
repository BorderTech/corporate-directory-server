package com.github.bordertech.corpdir.jpa.readonly.v1.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.jpa.entity.ContactEntity;
import com.github.bordertech.corpdir.jpa.entity.version.ContactVersionEntity;
import com.github.bordertech.corpdir.jpa.v1.api.ContactServiceImpl;
import com.github.bordertech.corpdir.readonly.api.v1.ContactReadOnlyService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

/**
 * Contact JPA read-only service implementation.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public class ContactReadOnlyServiceImpl extends ContactServiceImpl implements ContactReadOnlyService {

	@Override
	public DataResponse<byte[]> getImage(final String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<byte[]> getThumbnail(final String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getPositions(final String keyId) {
		return getPositions(getCurrentVersionId(), keyId);
	}

	@Override
	public DataResponse<List<Position>> getPositions(final Long versionId, final String keyId) {
		EntityManager em = getEntityManager();
		try {
			ContactEntity entity = getEntity(em, keyId);
			ContactVersionEntity links = entity.getVersion(versionId);
			List<Position> list;
			if (links == null) {
				list = new ArrayList<>();
			} else {
				list = POSITION_MAPPER.convertEntitiesToApis(em, links.getPositions(), versionId);
			}
			return new DataResponse<>(list);
		} finally {
			em.close();
		}
	}
    
}
