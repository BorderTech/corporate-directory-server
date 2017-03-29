package com.github.bordertech.corpdir.api.v1.model;

import com.github.bordertech.corpdir.api.common.AbstractApiKeyIdObject;

/**
 * Organization unit type.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class UnitType extends AbstractApiKeyIdObject {

	private String description;

	/**
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

}
