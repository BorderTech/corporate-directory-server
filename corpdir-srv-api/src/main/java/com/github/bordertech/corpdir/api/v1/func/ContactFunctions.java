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
 * @deprecated split into read and write
 */
@Deprecated
public interface ContactFunctions<T extends ApiVersionable> extends Serializable {

	/**
	 * 
	 * @param keyId
	 * @return 
	 * @deprecated use {@link ContactReadOnlyFunctions#getContacts(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<List<Contact>> getContacts(final String keyId);

	/**
	 * 
	 * @param keyId
	 * @param contactKeyId
	 * @return 
	 * @deprecated use {@link ContactWriteFunctions#addContact(java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> addContact(final String keyId, final String contactKeyId);

	/**
	 * 
	 * @param keyId
	 * @param contactKeyId
	 * @return 
	 * @deprecated use {@link ContactWriteFunctions#removeContact(java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> removeContact(final String keyId, final String contactKeyId);

	/**
	 * 
	 * @param versionId
	 * @param keyId
	 * @return 
	 * @deprecated use {@link ContactReadOnlyFunctions#getContacts(java.lang.Long, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<List<Contact>> getContacts(final Long versionId, final String keyId);

	/**
	 * 
	 * @param versionId
	 * @param keyId
	 * @param contactKeyId
	 * @return 
	 * @deprecated use {@link ContactWriteFunctions#addContact(java.lang.Long, java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> addContact(final Long versionId, final String keyId, final String contactKeyId);

	/**
	 * 
	 * @param versionId
	 * @param keyId
	 * @param contactKeyId
	 * @return 
	 * @deprecated use {@link ContactWriteFunctions#removeContact(java.lang.Long, java.lang.String, java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<T> removeContact(final Long versionId, final String keyId, final String contactKeyId);

}
