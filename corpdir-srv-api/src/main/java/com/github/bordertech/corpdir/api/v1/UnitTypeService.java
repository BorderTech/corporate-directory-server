package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.api.v1.model.UnitType;

/**
 * Organisation Unit Type read and write Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface UnitTypeService extends UnitTypeReadOnlyService, BasicKeyIdService<UnitType> {
    
}
