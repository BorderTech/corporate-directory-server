package com.github.bordertech.corpdir.jpa.v1.entity;

import com.github.bordertech.corpdir.jpa.AbstractPersistentObject;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Channel of communication.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Entity()
@Table(name = "Channel")
public class ChannelEntity extends AbstractPersistentObject {

	@Enumerated(EnumType.STRING)
	private ChannelTypeEnum type;
	private String channelValue;

	/**
	 * Default constructor.
	 */
	protected ChannelEntity() {
	}

	/**
	 *
	 * @param id the entity id
	 * @param businessKey the business key.
	 */
	public ChannelEntity(final Long id, final String businessKey) {
		super(id, businessKey);
	}

	/**
	 *
	 * @return the channel type
	 */
	public ChannelTypeEnum getType() {
		return type;
	}

	/**
	 *
	 * @param type the channel type
	 */
	public void setType(final ChannelTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the channel value (eg mobile number, email)
	 */
	public String getChannelValue() {
		return channelValue;
	}

	/**
	 *
	 * @param channelValue the channel value (eg mobile number, email)
	 */
	public void setChannelValue(final String channelValue) {
		this.channelValue = channelValue;
	}

}
