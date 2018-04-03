package com.github.bordertech.corpdir.jpa.v1.api;

import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.jpa.common.map.MapperApiVersion;
import com.github.bordertech.corpdir.jpa.common.svc.JpaBasicVersionKeyIdService;
import com.github.bordertech.corpdir.jpa.entity.ContactEntity;
import com.github.bordertech.corpdir.jpa.entity.version.ContactVersionEntity;
import com.github.bordertech.corpdir.jpa.v1.mapper.ChannelMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.ContactMapper;
import com.github.bordertech.corpdir.jpa.v1.mapper.PositionMapper;

/**
 * Abstract contact service implementation.
 * 
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public abstract class ContactServiceImpl extends JpaBasicVersionKeyIdService<Contact, ContactVersionEntity, ContactEntity> {

	protected static final ContactMapper CONTACT_MAPPER = new ContactMapper();
	protected static final PositionMapper POSITION_MAPPER = new PositionMapper();
	protected static final ChannelMapper CHANNEL_MAPPER = new ChannelMapper();

	@Override
	protected MapperApiVersion<Contact, ContactVersionEntity, ContactEntity> getMapper() {
		return CONTACT_MAPPER;
	}
	
	@Override
	protected Class<ContactEntity> getEntityClass() {
		return ContactEntity.class;
	}

	@Override
	protected Class<ContactVersionEntity> getVersionEntityClass() {
		return ContactVersionEntity.class;
	}
}
