package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.service.BasicTreeService;
import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.modify.api.v1.LocationWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.LocationReadOnlyService;

/**
 * Location Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into {@link LocationReadOnlyService} and {@link LocationWriteService}
 */
@Deprecated
public interface LocationService extends BasicTreeService<Location> {

}
