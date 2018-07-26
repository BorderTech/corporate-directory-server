package com.github.bordertech.corpdir.srv.lde.config.hk2;

import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.srv.lde.dummy.api.ContactReadOnlyServiceImpl;
import com.github.bordertech.corpdir.srv.lde.dummy.api.LocationReadOnlyServiceImpl;
import com.github.bordertech.corpdir.srv.lde.dummy.api.OrgUnitReadOnlyServiceImpl;
import com.github.bordertech.corpdir.srv.lde.dummy.api.PositionReadOnlyServiceImpl;
import com.github.bordertech.corpdir.srv.lde.dummy.api.PositionTypeReadOnlyServiceImpl;
import com.github.bordertech.corpdir.srv.lde.dummy.api.UnitTypeReadOnlyServiceImpl;
import com.github.bordertech.didums.DidumsBinder;
import com.github.bordertech.didums.DidumsProvider;

/**
 *
 * @author aswinkandula
 */
public class LdeDidumsBinder implements DidumsBinder {

	@Override
	public void configBindings(final DidumsProvider provider) {

		provider.bind(UnitTypeReadOnlyService.class, UnitTypeReadOnlyServiceImpl.class, true);
		provider.bind(PositionTypeReadOnlyService.class, PositionTypeReadOnlyServiceImpl.class, true);
		provider.bind(LocationReadOnlyService.class, LocationReadOnlyServiceImpl.class, true);
		provider.bind(OrgUnitReadOnlyService.class, OrgUnitReadOnlyServiceImpl.class, true);
		provider.bind(PositionReadOnlyService.class, PositionReadOnlyServiceImpl.class, true);
		provider.bind(ContactReadOnlyService.class, ContactReadOnlyServiceImpl.class, true);

	}

}
