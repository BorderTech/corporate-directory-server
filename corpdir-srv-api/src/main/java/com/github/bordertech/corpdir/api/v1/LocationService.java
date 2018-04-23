package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.service.BasicTreeService;
import com.github.bordertech.corpdir.api.v1.model.Location;

/**
 * Location read and write Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface LocationService extends LocationReadOnlyService, BasicTreeService<Location> {
    
}
