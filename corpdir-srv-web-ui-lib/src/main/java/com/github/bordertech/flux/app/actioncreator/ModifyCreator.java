package com.github.bordertech.flux.app.actioncreator;

import com.github.bordertech.flux.*;
import com.github.bordertech.flux.app.dataapi.modify.ModifyApi;

/**
 * Provides the action (event) creator interface to handle change store events.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface ModifyCreator extends ActionCreator, ModifyApi {

}