package com.github.bordertech.corpdir.jersey.config.hk2;

import com.github.bordertech.corpdir.api.v1.SystemCtrlService;
import com.github.bordertech.corpdir.api.v1.UnitTypeService;
import com.github.bordertech.corpdir.api.v1.VersionCtrlService;
import com.github.bordertech.corpdir.jpa.readonly.v1.api.ContactReadOnlyServiceImpl;
import com.github.bordertech.corpdir.jpa.readonly.v1.api.LocationReadOnlyServiceImpl;
import com.github.bordertech.corpdir.jpa.readonly.v1.api.OrgUnitReadOnlyServiceImpl;
import com.github.bordertech.corpdir.jpa.readonly.v1.api.PositionReadOnlyServiceImpl;
import com.github.bordertech.corpdir.jpa.readonly.v1.api.PositionTypeReadOnlyServiceImpl;
import com.github.bordertech.corpdir.jpa.v1.api.SystemCtrlServiceImpl;
import com.github.bordertech.corpdir.jpa.v1.api.UnitTypeServiceImpl;
import com.github.bordertech.corpdir.jpa.v1.api.VersionCtrlServiceImpl;
import com.github.bordertech.corpdir.jpa.write.v1.api.ContactWriteServiceImpl;
import com.github.bordertech.corpdir.jpa.write.v1.api.LocationWriteServiceImpl;
import com.github.bordertech.corpdir.jpa.write.v1.api.OrgUnitWriteServiceImpl;
import com.github.bordertech.corpdir.jpa.write.v1.api.PositionTypeWriteServiceImpl;
import com.github.bordertech.corpdir.jpa.write.v1.api.PositionWriteServiceImpl;
import com.github.bordertech.corpdir.modify.api.v1.ContactWriteService;
import com.github.bordertech.corpdir.modify.api.v1.LocationWriteService;
import com.github.bordertech.corpdir.modify.api.v1.OrgUnitWriteService;
import com.github.bordertech.corpdir.modify.api.v1.PositionTypeWriteService;
import com.github.bordertech.corpdir.modify.api.v1.PositionWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.ContactReadOnlyService;
import com.github.bordertech.corpdir.readonly.api.v1.LocationReadOnlyService;
import com.github.bordertech.corpdir.readonly.api.v1.OrgUnitReadOnlyService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionReadOnlyService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionTypeReadOnlyService;
import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationServicesBinder extends AbstractBinder {

	@Override
	protected void configure() {
		// Services
		bind(UnitTypeServiceImpl.class).to(UnitTypeService.class).in(Singleton.class);
		bind(SystemCtrlServiceImpl.class).to(SystemCtrlService.class).in(Singleton.class);
		bind(VersionCtrlServiceImpl.class).to(VersionCtrlService.class).in(Singleton.class);

		bind(ContactReadOnlyServiceImpl.class).to(ContactReadOnlyService.class).in(Singleton.class);
		bind(PositionReadOnlyServiceImpl.class).to(PositionReadOnlyService.class).in(Singleton.class);
		bind(OrgUnitReadOnlyServiceImpl.class).to(OrgUnitReadOnlyService.class).in(Singleton.class);
		bind(LocationReadOnlyServiceImpl.class).to(LocationReadOnlyService.class).in(Singleton.class);
		bind(PositionTypeReadOnlyServiceImpl.class).to(PositionTypeReadOnlyService.class).in(Singleton.class);

		bind(ContactWriteServiceImpl.class).to(ContactWriteService.class).in(Singleton.class);
		bind(PositionWriteServiceImpl.class).to(PositionWriteService.class).in(Singleton.class);
		bind(OrgUnitWriteServiceImpl.class).to(OrgUnitWriteService.class).in(Singleton.class);
		bind(LocationWriteServiceImpl.class).to(LocationWriteService.class).in(Singleton.class);
		bind(PositionTypeWriteServiceImpl.class).to(PositionTypeWriteService.class).in(Singleton.class);
	}
}
