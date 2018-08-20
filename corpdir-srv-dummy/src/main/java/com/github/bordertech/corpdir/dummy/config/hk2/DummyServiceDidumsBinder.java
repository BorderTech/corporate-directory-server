package com.github.bordertech.corpdir.dummy.config.hk2;

import com.github.bordertech.corpdir.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.api.v1.PositionTypeReadOnlyService;
import com.github.bordertech.corpdir.api.v1.UnitTypeReadOnlyService;
import com.github.bordertech.corpdir.corpdir.srv.dummy.ContactReadOnlyServiceImpl;
import com.github.bordertech.corpdir.corpdir.srv.dummy.LocationReadOnlyServiceImpl;
import com.github.bordertech.corpdir.corpdir.srv.dummy.OrgUnitReadOnlyServiceImpl;
import com.github.bordertech.corpdir.corpdir.srv.dummy.PositionReadOnlyServiceImpl;
import com.github.bordertech.corpdir.corpdir.srv.dummy.PositionTypeReadOnlyServiceImpl;
import com.github.bordertech.corpdir.corpdir.srv.dummy.UnitTypeReadOnlyServiceImpl;
import com.github.bordertech.didums.DidumsBinder;
import com.github.bordertech.didums.DidumsProvider;

/**
 * Didums binders for dummy implementation of read-only service APIs.
 * 
 * @author aswinkandula
 */
public class DummyServiceDidumsBinder implements DidumsBinder {

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
