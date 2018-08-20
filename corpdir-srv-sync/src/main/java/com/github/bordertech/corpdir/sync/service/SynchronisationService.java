package com.github.bordertech.corpdir.sync.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;

/**
 * Sync service interface.
 *
 * @param <U> the response type
 * @author aswinkandula
 * @since 1.0.0
 */

public interface SynchronisationService<U extends ApiIdObject> {

	public U sync();
}
