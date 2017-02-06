package com.github.bordertech.corpdir.jpa.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Organization unit type.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Entity
@Table(name = "OrgUnitType")
public class OrgUnitTypeEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String alternateKey;
	private String desc;
	private boolean custom;

	/**
	 *
	 * @return the unique id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the unique id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 *
	 * @return the alternate org unit type key
	 */
	public String getAlternateKey() {
		return alternateKey;
	}

	/**
	 *
	 * @param alternateKey the alternate org unit type key
	 */
	public void setAlternateKey(final String alternateKey) {
		this.alternateKey = alternateKey;
	}

	/**
	 *
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 *
	 * @param desc the description
	 */
	public void setDesc(final String desc) {
		this.desc = desc;
	}

	/**
	 *
	 * @return true if custom record
	 */
	public boolean isCustom() {
		return custom;
	}

	/**
	 *
	 * @param custom true if custom record
	 */
	public void setCustom(final boolean custom) {
		this.custom = custom;
	}
}
