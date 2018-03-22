package com.github.bordertech.corpdir.api.service.readonly;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;

/**
 * Basic read-only service for keyed API object.
 * <p>
 * The ID maybe a record ID or a BusinessKey.
 * </p>
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicKeyIdReadOnlyService<T extends ApiKeyIdObject> extends BasicIdReadOnlyService<T> {
    
}
