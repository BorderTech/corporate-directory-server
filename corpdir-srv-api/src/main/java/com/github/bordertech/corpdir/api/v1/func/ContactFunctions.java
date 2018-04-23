package com.github.bordertech.corpdir.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import java.io.Serializable;

/**
 * Contact read and write functions.
 *
 * @author Jonathan Austin
 * @param <T> the type having contacts added to it
 * @since 1.0.0
 */
public interface ContactFunctions<T extends ApiVersionable> extends ContactReadOnlyFunctions<T>, Serializable {

	DataResponse<T> addContact(final String keyId, final String contactKeyId);

	DataResponse<T> removeContact(final String keyId, final String contactKeyId);

	DataResponse<T> addContact(final Long versionId, final String keyId, final String contactKeyId);

	DataResponse<T> removeContact(final Long versionId, final String keyId, final String contactKeyId);
}
