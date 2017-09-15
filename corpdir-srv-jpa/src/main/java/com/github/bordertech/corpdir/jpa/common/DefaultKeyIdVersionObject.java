package com.github.bordertech.corpdir.jpa.common;

import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionData;
import com.github.bordertech.corpdir.jpa.common.feature.PersistVersionable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.MappedSuperclass;

/**
 * Default keyed object with version data.
 *
 * @author Jonathan Austin
 * @param <T> the versionable data type
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class DefaultKeyIdVersionObject<T extends PersistVersionable> extends DefaultKeyIdObject implements PersistVersionData<T> {

	/**
	 * Default constructor.
	 */
	protected DefaultKeyIdVersionObject() {
	}

	/**
	 *
	 * @param id the entity id
	 */
	public DefaultKeyIdVersionObject(final Long id) {
		super(id);
	}

	@Override
	public void addDataVersion(final T versionData) {
		Set<T> dataVersions = getDataVersions();
		if (dataVersions == null) {
			dataVersions = new HashSet<>();
			setDataVersions(dataVersions);
		}
		dataVersions.add(versionData);
	}

	@Override
	public void removeDataVersion(final Long versionId) {
		Set<T> dataVersions = getDataVersions();
		if (dataVersions != null) {
			for (T link : dataVersions) {
				if (Objects.equals(link.getVersionId(), versionId)) {
					dataVersions.remove(link);
					if (dataVersions.isEmpty()) {
						setDataVersions(null);
					}
					return;
				}
			}
		}
	}

	@Override
	public T getDataVersion(final Long versionId) {
		Set<T> dataVersions = getDataVersions();
		if (dataVersions != null) {
			for (T data : dataVersions) {
				if (Objects.equals(data.getVersionId(), versionId)) {
					return data;
				}
			}
		}
		return null;
	}

}
