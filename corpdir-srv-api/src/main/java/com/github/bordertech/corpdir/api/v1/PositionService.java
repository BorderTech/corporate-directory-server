package com.github.bordertech.corpdir.api.v1;

import com.github.bordertech.corpdir.api.service.BasicVersionTreeService;
import com.github.bordertech.corpdir.api.v1.func.ContactFunctions;
import com.github.bordertech.corpdir.api.v1.model.Position;

/**
 * Position read and write Service Interface.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface PositionService extends PositionReadOnlyService, BasicVersionTreeService<Position>, ContactFunctions<Position> {
    
}
