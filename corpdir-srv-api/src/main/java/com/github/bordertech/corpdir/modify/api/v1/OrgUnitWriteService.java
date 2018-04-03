package com.github.bordertech.corpdir.modify.api.v1;

import com.github.bordertech.corpdir.api.modify.service.BasicVersionTreeWriteService;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.modify.api.v1.func.PositionWriteFunctions;

/**
 * Organisation Unit write (modifiable) Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface OrgUnitWriteService extends BasicVersionTreeWriteService<OrgUnit>, PositionWriteFunctions<OrgUnit> {
    
}
