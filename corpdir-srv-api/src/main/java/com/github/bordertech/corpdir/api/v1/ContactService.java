package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import com.github.bordertech.corpdir.api.v1.func.PositionFunctions;
import com.github.bordertech.corpdir.api.v1.model.Contact;

/**
 * Contact read and write Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface ContactService extends ContactReadOnlyService, BasicVersionKeyIdService<Contact>, PositionFunctions<Contact> {

	BasicResponse deleteImage(final String keyId);

	BasicResponse setImage(final String keyId, final byte[] image);

	BasicResponse deleteThumbnail(final String keyId);

	BasicResponse setThumbnail(final String keyId, final byte[] image);
}
