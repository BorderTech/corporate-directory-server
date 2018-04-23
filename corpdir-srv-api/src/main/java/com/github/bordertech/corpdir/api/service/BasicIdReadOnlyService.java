package com.github.bordertech.corpdir.api.service;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.io.Serializable;
import java.util.List;

/**
 * Basic read-only service for ID API object.
 *
 * @param <T> the keyed API object
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface BasicIdReadOnlyService <T extends ApiIdObject> extends Serializable {

	DataResponse<List<T>> search(final String search);

	DataResponse<T> retrieve(final String id);
    
}
