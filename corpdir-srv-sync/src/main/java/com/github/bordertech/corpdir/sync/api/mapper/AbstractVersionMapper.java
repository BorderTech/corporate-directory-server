package com.github.bordertech.corpdir.sync.api.mapper;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.common.ApiVersionable;

/**
 * Abstract mapper to copy API versioned object common fields.
 * @author aswinkandula
 * @param <A>
 */
public abstract class AbstractVersionMapper<A extends ApiKeyIdObject & ApiVersionable> extends AbstractMapper<A> {

	@Override
	public void map(A source, A destination) {
		super.map(source, destination);
		destination.setVersionId(source.getVersionId());
	}

	
	
}
