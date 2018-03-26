package com.github.bordertech.corpdir.readonly.api.v1.func;

import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import java.io.Serializable;
import java.util.List;


/**
 * Contact read-only functions.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @param <T> the type having contacts added to it
 * @since 1.0.0
 */
public interface ContactReadOnlyFunctions<T extends ApiVersionable> extends Serializable {

	DataResponse<List<Contact>> getContacts(final String keyId);

	DataResponse<List<Contact>> getContacts(final Long versionId, final String keyId);
}
