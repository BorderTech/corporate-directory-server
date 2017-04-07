package com.github.bordertech.corpdir.jpa.common;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract persistent keyed object.
 *
 * @author jonathan
 */
@MappedSuperclass
public abstract class AbstractPersistentKeyIdObject implements PersistentKeyIdObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String businessKey;
	private String description;

	private boolean active = true;
	private boolean custom = true;

	@Version
	private Timestamp version;

	/**
	 * Default constructor.
	 */
	protected AbstractPersistentKeyIdObject() {
		// Default constructor
	}

	/**
	 * @param id the entity id
	 */
	public AbstractPersistentKeyIdObject(final Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getBusinessKey() {
		return businessKey;
	}

	@Override
	public void setBusinessKey(final String businessKey) {
		this.businessKey = businessKey;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public Timestamp getVersion() {
		return version;
	}

	@Override
	public void setVersion(final Timestamp version) {
		this.version = version;
	}

	/**
	 *
	 * @return true if active record
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/**
	 *
	 * @param active true if active record
	 */
	@Override
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 *
	 * @return true if custom record
	 */
	@Override
	public boolean isCustom() {
		return custom;
	}

	/**
	 *
	 * @param custom true if custom record
	 */
	@Override
	public void setCustom(final boolean custom) {
		this.custom = custom;
	}

	@Override
	public int hashCode() {
		return id == null ? 31 : id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return obj instanceof AbstractPersistentKeyIdObject && Objects.equals(id, ((AbstractPersistentKeyIdObject) obj).id);
	}

}
