package com.github.bordertech.corpdir.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import java.io.Serializable;
import java.util.List;

/**
 * Contact functions.
 *
 * @author Jonathan Austin
 * @param <T> the type having contacts added to it
 * @since 1.0.0
 */
public interface ContactFunctions<T extends ApiVersionable> extends Serializable {

	DataResponse<List<Contact>> getContacts(final String keyId);

	DataResponse<T> addContact(final String keyId, final String contactKeyId);

	DataResponse<T> removeContact(final String keyId, final String contactKeyId);

	DataResponse<List<Contact>> getContacts(final Long versionId, final String keyId);

	DataResponse<T> addContact(final Long versionId, final String keyId, final String contactKeyId);

	DataResponse<T> removeContact(final Long versionId, final String keyId, final String contactKeyId);

}
