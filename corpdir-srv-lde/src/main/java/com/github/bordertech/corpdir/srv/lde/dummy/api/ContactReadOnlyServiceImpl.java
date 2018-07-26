package com.github.bordertech.corpdir.srv.lde.dummy.api;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.model.Address;
import com.github.bordertech.corpdir.api.v1.model.Channel;
import com.github.bordertech.corpdir.api.v1.model.ChannelTypeEnum;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.api.v1.model.Position;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aswinkandula
 */
public class ContactReadOnlyServiceImpl implements ContactReadOnlyService {

	@Override
	public DataResponse<byte[]> getImage(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<byte[]> getThumbnail(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Contact>> search(Long versionId, String search) {
		return search(search);
	}

	@Override
	public DataResponse<Contact> retrieve(Long versionId, String keyId) {
		return new DataResponse<>(search(keyId).getData().get(0));
	}

	@Override
	public DataResponse<List<Contact>> search(String search) {
		Contact dummy = new Contact(null) {{
			setCustom(false);
			setActive(true);
			setBusinessKey("CO-1");
			setDescription("CO-D");
			setFirstName("Contact-F");
			setLastName("Contact-L");
			getChannels().add(new Channel(null) {{
				setCustom(false);
				setType(ChannelTypeEnum.MOBILE);
				setValue("123");
			}});
			getPositionIds().add("PO-1");
			setCompanyTitle("TIE");
			setAddress(new Address() {{
				setState("STA");
			}});
			setLocationId("LO-1");
		}};
		return new DataResponse<>(Arrays.asList(dummy));
	}

	@Override
	public DataResponse<Contact> retrieve(String id) {
		return new DataResponse<>(search(id).getData().get(0));
	}

	@Override
	public DataResponse<List<Position>> getPositions(String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public DataResponse<List<Position>> getPositions(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
