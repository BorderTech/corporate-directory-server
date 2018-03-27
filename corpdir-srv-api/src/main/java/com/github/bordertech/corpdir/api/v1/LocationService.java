package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.service.BasicTreeService;
import com.github.bordertech.corpdir.api.v1.model.Location;

/**
 * Location Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split into read and write
 */
@Deprecated
public interface LocationService extends BasicTreeService<Location> {

}
