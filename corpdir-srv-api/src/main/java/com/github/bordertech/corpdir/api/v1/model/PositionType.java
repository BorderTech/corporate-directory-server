package com.github.bordertech.corpdir.api.v1.model;

import com.github.bordertech.corpdir.api.common.DefaultKeyIdObject;

/**
 * Position type.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class PositionType extends DefaultKeyIdObject {

	private int level;

	protected PositionType() {
	}

	public PositionType(final String id) {
		super(id);
	}

	/**
	 *
	 * @return the position level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 *
	 * @param level the position level
	 */
	public void setLevel(final int level) {
		this.level = level;
	}

}
