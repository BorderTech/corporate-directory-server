package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.service.BasicVersionTreeService;
import com.github.bordertech.corpdir.api.v1.func.PositionFunctions;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;

/**
 * Organisation Unit read and write Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface OrgUnitService extends OrgUnitReadOnlyService, BasicVersionTreeService<OrgUnit>, PositionFunctions<OrgUnit> {
    
}
