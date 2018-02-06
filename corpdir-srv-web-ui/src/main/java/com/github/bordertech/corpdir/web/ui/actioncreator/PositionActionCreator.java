package com.github.bordertech.corpdir.web.ui.actioncreator;

import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.web.ui.dataapi.PositionApi;
import com.github.bordertech.corpdir.web.ui.flux.actioncreator.CorpCrudTreeActionCreator;

/**
 * Position CRUD ActionCreator.
 *
 * @author jonathan
 */
public interface PositionActionCreator extends CorpCrudTreeActionCreator<Position, PositionApi> {
}
