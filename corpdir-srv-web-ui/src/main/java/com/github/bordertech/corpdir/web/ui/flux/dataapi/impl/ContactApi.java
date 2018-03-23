package com.github.bordertech.corpdir.web.ui.flux.dataapi.impl;

import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.modify.api.v1.ContactWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.web.ui.flux.dataapi.temp.DefaultCorpCrudVersionDataApiTemp;
import javax.inject.Inject;

/**
 * Contact CRUD API implementation.
 *
 * @author jonathan
 */
public class ContactApi extends DefaultCorpCrudVersionDataApiTemp<Contact, ContactReadOnlyService, ContactWriteService> {

	@Inject
	public ContactApi(final ContactReadOnlyService readService, ContactWriteService writeService) {
		super(Contact.class, readService, writeService);
	}

}
