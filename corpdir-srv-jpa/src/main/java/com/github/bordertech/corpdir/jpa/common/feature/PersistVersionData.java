package com.github.bordertech.corpdir.jpa.common.feature;

import com.github.bordertech.corpdir.jpa.entity.VersionCtrlEntity;
import java.util.Set;

/**
 * Object with a Business Key and versioned data.
 *
 * @author jonathan
 * @param <T>
 */
public interface PersistVersionData<T extends PersistVersionable> extends PersistKeyIdObject {

	Set<T> getDataVersions();

	void setDataVersions(final Set<T> dataVersions);

	void addDataVersion(final T versionData);

	void removeDataVersion(final Long versionId);

	T getDataVersion(final Long versionId);

	T getOrCreateDataVersion(final VersionCtrlEntity ctrl);

}
