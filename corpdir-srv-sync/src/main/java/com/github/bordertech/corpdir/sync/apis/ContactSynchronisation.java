package com.github.bordertech.corpdir.sync.apis;

import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.ContactService;
import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.sync.api.mapper.ContactMapper;
import com.github.bordertech.corpdir.sync.common.AbstractVersionKeyIdSynchronisation;
import javax.inject.Inject;

/**
 * One-way Contact synchronisation.
 * 
 * @author aswinkandula
 * @since 1.0.0
 */
public class ContactSynchronisation extends AbstractVersionKeyIdSynchronisation<Contact> {
	
	private static final ContactMapper API_MAPPER = new ContactMapper();

	@Inject
	public ContactSynchronisation(final ContactReadOnlyService sourceService, final ContactService destinationService) {
		super(sourceService, destinationService);
	}

	@Override
	public ContactMapper getApiMapper() {
		return API_MAPPER;
	}
}
