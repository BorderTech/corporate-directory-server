package com.github.bordertech.corpdir.api.v1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact details.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class Contact extends AbstractApiObject {

	private String firstName;
	private String lastName;
	private String companyTitle;
	private Address address;
	private Location location;
	private boolean hasImage;
	private List<Channel> channels;
	private List<String> positionIds;

	/**
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the company title
	 */
	public String getCompanyTitle() {
		return companyTitle;
	}

	/**
	 * @param companyTitle the company title
	 */
	public void setCompanyTitle(final String companyTitle) {
		this.companyTitle = companyTitle;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 *
	 * @param address the address
	 */
	public void setAddress(final Address address) {
		this.address = address;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location
	 */
	public void setLocation(final Location location) {
		this.location = location;
	}

	/**
	 *
	 * @return true if contact has image
	 */
	public boolean getHasImage() {
		return hasImage;
	}

	/**
	 *
	 * @param hasImage true if contact has image
	 */
	public void setHasImage(final boolean hasImage) {
		this.hasImage = hasImage;
	}

	/**
	 *
	 * @return the channels
	 */
	public List<Channel> getChannels() {
		if (channels == null) {
			channels = new ArrayList<>();
		}
		return channels;
	}

	/**
	 *
	 * @param channels the channels
	 */
	public void setChannels(final List<Channel> channels) {
		this.channels = channels;
	}

	/**
	 *
	 * @return the assigned position ids
	 */
	public List<String> getPositionIds() {
		if (positionIds == null) {
			positionIds = new ArrayList<>();
		}
		return positionIds;
	}

	/**
	 *
	 * @param positionIds the assigned position ids
	 */
	public void setPositionIds(final List<String> positionIds) {
		this.positionIds = positionIds;
	}

}
