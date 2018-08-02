package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.ContactService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.sync.common.AbstractVersionKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way Contact synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class ContactSynchronisation extends AbstractVersionKeyIdSynchronisation<ContactReadOnlyService, ContactService, Contact> {

	@Inject
	public ContactSynchronisation(ContactReadOnlyService sourceService, ContactService destinationService) {
		super(sourceService, destinationService);
	}
}
