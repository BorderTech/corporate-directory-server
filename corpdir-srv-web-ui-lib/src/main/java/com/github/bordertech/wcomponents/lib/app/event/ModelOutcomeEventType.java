package com.github.bordertech.wcomponents.lib.app.event;

import com.github.bordertech.wcomponents.lib.flux.EventType;

/**
 * Model (ie entitiy) event outcome.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public enum ModelOutcomeEventType implements EventType {
	REFRESH_OK,
	REFRESH_ERROR,
	DELETE_OK,
	DELETE_ERROR,
	CREATE_OK,
	CREATE_ERROR,
	UPDATE_OK,
	UPDATE_ERROR
}
