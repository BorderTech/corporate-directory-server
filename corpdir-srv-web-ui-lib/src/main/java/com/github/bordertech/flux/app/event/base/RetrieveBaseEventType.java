package com.github.bordertech.flux.app.event.base;

import com.github.bordertech.flux.app.event.RetrieveEventType;

/**
 * Model (ie entity) event type.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public enum RetrieveBaseEventType implements RetrieveEventType {
	RETRIEVE,
	REFRESH,
	RETRIEVE_ASYNC,
	RETRIEVE_ASYNC_OK,
	RETRIEVE_ASYNC_ERROR,
	REFRESH_ASYNC
}
