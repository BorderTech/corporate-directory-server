package com.github.bordertech.corpdir.jpa.v1.mapper;

import com.github.bordertech.corpdir.api.v1.model.Channel;
import com.github.bordertech.corpdir.api.v1.model.ChannelTypeEnum;
import com.github.bordertech.corpdir.jpa.common.AbstractKeyIdApiEntityMapper;
import com.github.bordertech.corpdir.jpa.entity.ChannelEntity;
import javax.persistence.EntityManager;

/**
 * Map {@link Channel} and {@link ChannelEntity}.
 *
 * @author jonathan
 */
public class ChannelMapper extends AbstractKeyIdApiEntityMapper<Channel, ChannelEntity> {

	@Override
	public void copyApiToEntityFields(final EntityManager em, final Channel from, final ChannelEntity to) {
		to.setChannelValue(from.getChannelValue());
		if (from.getType() != null) {
			switch (from.getType()) {
				case EMAIL:
					to.setType(com.github.bordertech.corpdir.jpa.entity.ChannelTypeEnum.EMAIL);
					break;
				case MOBILE:
					to.setType(com.github.bordertech.corpdir.jpa.entity.ChannelTypeEnum.MOBILE);
					break;
				case OFFICE:
					to.setType(com.github.bordertech.corpdir.jpa.entity.ChannelTypeEnum.OFFICE);
					break;
				default:
					// TODO Throw exception
					to.setType(null);
			}
		}
	}

	@Override
	public void copyEntityToApiFields(final EntityManager em, final ChannelEntity from, final Channel to) {
		to.setChannelValue(from.getChannelValue());
		if (from.getType() != null) {
			switch (from.getType()) {
				case EMAIL:
					to.setType(ChannelTypeEnum.EMAIL);
					break;
				case MOBILE:
					to.setType(ChannelTypeEnum.MOBILE);
					break;
				case OFFICE:
					to.setType(ChannelTypeEnum.OFFICE);
					break;
				default:
					// TODO Throw exception
					to.setType(null);
			}
		}
	}

	@Override
	protected Channel createApiObject() {
		return new Channel();
	}

	@Override
	protected ChannelEntity createEntityObject(final Long id, final String businessKey) {
		return new ChannelEntity(id, businessKey);
	}

}
