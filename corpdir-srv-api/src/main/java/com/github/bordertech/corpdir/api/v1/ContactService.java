package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.service.BasicVersionKeyIdService;
import com.github.bordertech.corpdir.api.v1.func.PositionFunctions;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.modify.api.v1.ContactWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.ContactReadOnlyService;

/**
 * Contact Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into {@link ContactReadOnlyService} and {@link ContactWriteService}
 */
@Deprecated
public interface ContactService extends BasicVersionKeyIdService<Contact>, PositionFunctions<Contact> {

	/**
	 *
	 * @param keyId
	 * @return 
	 * @deprecated use {@link ContactReadOnlyService#getImage(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<byte[]> getImage(final String keyId);

	/**
	 *
	 * @param keyId
	 * @return 
	 * @deprecated use {@link ContactWriteService#deleteImage(java.lang.String) } instead.
	 */
	@Deprecated
	BasicResponse deleteImage(final String keyId);

	/**
	 *
	 * @param keyId
	 * @param image
	 * @return 
	 * @deprecated use {@link ContactWriteService#setImage(java.lang.String, byte[]) } instead.
	 */
	@Deprecated
	BasicResponse setImage(final String keyId, final byte[] image);

	/**
	 *
	 * @param keyId
	 * @return 
	 * @deprecated use {@link ContactReadOnlyService#getThumbnail(java.lang.String) } instead.
	 */
	@Deprecated
	DataResponse<byte[]> getThumbnail(final String keyId);

	/**
	 *
	 * @param keyId
	 * @return 
	 * @deprecated use {@link ContactWriteService#deleteThumbnail(java.lang.String) } instead.
	 */
	@Deprecated
	BasicResponse deleteThumbnail(final String keyId);

	/**
	 *
	 * @param keyId
	 * @param image
	 * @return 
	 * @deprecated use {@link ContactWriteService#setThumbnail(java.lang.String, byte[]) } instead.
	 */
	@Deprecated
	BasicResponse setThumbnail(final String keyId, final byte[] image);

}
