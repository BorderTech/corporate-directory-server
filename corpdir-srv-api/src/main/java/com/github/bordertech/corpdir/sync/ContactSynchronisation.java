package com.github.bordertech.corpdir.sync;

import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.ContactService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import java.util.List;
import javax.inject.Inject;

/**
 * One-way Contacts synchronisation from Source to Destination
 *
 * @author exiqaj
 */
public class ContactSynchronisation extends AbstractVersionSynchronisation<ContactReadOnlyService, ContactService, Contact> {

    @Inject
    public ContactSynchronisation(ContactReadOnlyService sourceService, ContactService destinationService) {
	super(sourceService, destinationService);
    }

    @Override
    public void syncBaseData() {
	DataResponse<List<Contact>> sourceContacts = getSourceData();
	final Long versionId = Long.parseLong(getOrCreateNewVersion());
	for (Contact sourceContact : sourceContacts.getData()) {
	    createOrUpdateData(versionId, sourceContact);
	}
    }

    @Override
    public void syncLinkedData() {
	// TODO
    }
}
