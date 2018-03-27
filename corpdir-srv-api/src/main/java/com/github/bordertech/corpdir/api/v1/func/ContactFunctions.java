package com.github.bordertech.corpdir.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.modify.api.v1.func.ContactWriteFunctions;
import com.github.bordertech.corpdir.readonly.api.v1.func.ContactReadOnlyFunctions;
import java.io.Serializable;
import java.util.List;

/**
 * Contact functions.
 *
 * @author Jonathan Austin
 * @param <T> the type having contacts added to it
 * @since 1.0.0
 * @deprecated split into {@link ContactReadOnlyFunctions} and {@link ContactWriteFunctions}
 */
@Deprecated
public interface ContactFunctions<T extends ApiVersionable> extends Serializable {

	@Deprecated
	DataResponse<List<Contact>> getContacts(final String keyId);

	@Deprecated
	DataResponse<T> addContact(final String keyId, final String contactKeyId);

	@Deprecated
	DataResponse<T> removeContact(final String keyId, final String contactKeyId);

	@Deprecated
	DataResponse<List<Contact>> getContacts(final Long versionId, final String keyId);

	@Deprecated
	DataResponse<T> addContact(final Long versionId, final String keyId, final String contactKeyId);

	@Deprecated
	DataResponse<T> removeContact(final Long versionId, final String keyId, final String contactKeyId);

}
