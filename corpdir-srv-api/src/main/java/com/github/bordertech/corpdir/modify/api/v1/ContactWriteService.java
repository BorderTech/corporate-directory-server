package com.github.bordertech.corpdir.modify.api.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.modify.service.BasicVersionKeyIdWriteService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.modify.api.v1.func.PositionWriteFunctions;

/**
 * Contact write (modifiable) Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface ContactWriteService extends BasicVersionKeyIdWriteService<Contact>, PositionWriteFunctions<Contact> {

	BasicResponse deleteImage(final String keyId);

	BasicResponse setImage(final String keyId, final byte[] image);

	BasicResponse deleteThumbnail(final String keyId);

	BasicResponse setThumbnail(final String keyId, final byte[] image);
}
