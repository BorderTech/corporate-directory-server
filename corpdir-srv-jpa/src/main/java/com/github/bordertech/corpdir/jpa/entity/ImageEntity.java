package com.github.bordertech.corpdir.jpa.entity;

import com.github.bordertech.corpdir.jpa.common.DefaultKeyIdObject;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Image details.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Entity
@Table(name = "Image")
public class ImageEntity extends DefaultKeyIdObject {

	private String mimeType;
	private byte[] image;
	private byte[] thumbNail;

	/**
	 * Default constructor.
	 */
	protected ImageEntity() {
	}

	/**
	 *
	 * @param id the entity id
	 */
	public ImageEntity(final Long id) {
		super(id);
	}

	/**
	 *
	 * @return the location id
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 *
	 * @param mimeType the location id
	 */
	public void setMimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 *
	 * @return the image bytes
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 *
	 * @param image the image bytes
	 */
	public void setImage(final byte[] image) {
		this.image = image;
	}

	/**
	 *
	 * @return the thumbnail bytes
	 */
	public byte[] getThumbNail() {
		return thumbNail;
	}

	/**
	 *
	 * @param thumbNail the thumbnail bytes
	 */
	public void setThumbNail(final byte[] thumbNail) {
		this.thumbNail = thumbNail;
	}

}
