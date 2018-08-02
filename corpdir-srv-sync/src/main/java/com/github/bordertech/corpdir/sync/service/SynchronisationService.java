package com.github.bordertech.corpdir.sync.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import java.io.Serializable;

/**
 * Sync service interface.
 *
 * @param <T> the criteria type
 * @param <U> the response type
 * @author aswinkandula
 * @since 1.0.0
 */

public interface SynchronisationService<T extends Serializable, U extends ApiIdObject> {

	public U sync(T criteria);
}
