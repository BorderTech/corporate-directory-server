package com.github.bordertech.corpdir.sync.impl;

import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.ContactService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.sync.common.AbstractVersionSynchronisation;
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
}
