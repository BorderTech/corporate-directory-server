package com.github.bordertech.corpdir.readonly.api.v1;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.readonly.BasicVersionKeyIdReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.readonly.api.v1.func.PositionReadOnlyFunctions;

/**
 * Contact read-only Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface ContactReadOnlyService extends BasicVersionKeyIdReadOnlyService<Contact>, PositionReadOnlyFunctions<Contact> {

	DataResponse<byte[]> getImage(final String keyId);

	DataResponse<byte[]> getThumbnail(final String keyId);
}
