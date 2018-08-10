package com.github.bordertech.corpdir.sync.api.mapper;

import com.github.bordertech.corpdir.api.v1.model.OrgUnit;

/**
 * Organisation Unit mapper
 * @author aswinkandula
 */
public class OrgUnitMapper extends AbstractVersionMapper<OrgUnit> {

	@Override
	public void map(OrgUnit source, OrgUnit destination) {
		super.map(source, destination);
		destination.setTypeId(source.getTypeId());
	}
	
}
