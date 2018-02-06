package com.github.bordertech.corpdir.jpa.entity;

import com.github.bordertech.corpdir.jpa.common.DefaultVersionableKeyIdTreeObject;
import com.github.bordertech.corpdir.jpa.entity.version.PositionVersionEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Position in organization unit.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Entity
@Table(name = "Position")
public class PositionEntity extends DefaultVersionableKeyIdTreeObject<PositionEntity, PositionVersionEntity> {

	@OneToMany(mappedBy = "item", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PositionVersionEntity> versions;

	@ManyToOne()
	private PositionTypeEntity type;

	/**
	 * Default constructor.
	 */
	protected PositionEntity() {
	}

	/**
	 *
	 * @param id the entity id
	 */
	public PositionEntity(final Long id) {
		super(id);
	}

	/**
	 *
	 * @return the position type
	 */
	public PositionTypeEntity getType() {
		return type;
	}

	/**
	 *
	 * @param type the position type
	 */
	public void setType(final PositionTypeEntity type) {
		this.type = type;
	}

	@Override
	public PositionVersionEntity createVersion(final VersionCtrlEntity ctrl) {
		return new PositionVersionEntity(ctrl, this);
	}

	@Override
	public Set<PositionVersionEntity> getVersions() {
		if (versions == null) {
			versions = new HashSet<>();
		}
		return versions;
	}

}
