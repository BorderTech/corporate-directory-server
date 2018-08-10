package com.github.bordertech.corpdir.sync.api.mapper;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;

/**
 * Abstract mapper to copy API Keyed object common fields
 * @author aswinkandula
 * @param <A>
 */
public abstract class AbstractMapper<A extends ApiKeyIdObject> implements ApiModelMapper<A>  {

	@Override
	public void map(A source, A destination) {
		destination.setActive(source.isActive());
		destination.setBusinessKey(source.getBusinessKey());
		destination.setCustom(source.isCustom());
		destination.setDescription(source.getDescription());
	}
}
