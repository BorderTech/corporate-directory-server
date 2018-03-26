package com.github.bordertech.corpdir.modify.api.v1;

import com.github.bordertech.corpdir.api.service.modify.BasicVersionTreeWriteService;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.modify.api.v1.func.ContactWriteFunctions;

/**
 * Position write (modifiable) Service Interface.
 *
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
public interface PositionWriteService extends BasicVersionTreeWriteService<Position>, ContactWriteFunctions<Position> {
    
}
