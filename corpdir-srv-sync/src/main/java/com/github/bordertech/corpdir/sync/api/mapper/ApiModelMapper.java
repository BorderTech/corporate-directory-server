package com.github.bordertech.corpdir.sync.api.mapper;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;

/**
 * Mapper interface to copy API model data from source to destination
 * @author aswinkandula
 * @param <A>
 */
public interface ApiModelMapper<A extends ApiKeyIdObject> {
	
	void map(A source, A destination);
}
