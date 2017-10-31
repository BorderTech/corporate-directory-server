package com.github.bordertech.flux.impl;

import com.github.bordertech.flux.Event;
import com.github.bordertech.flux.EventKey;
import com.github.bordertech.flux.EventType;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultEvent implements Event {

	private final EventKey eventKey;
	private final Object data;
	private final Exception exception;

	public DefaultEvent(final EventType eventType) {
		this(eventType, null);
	}

	public DefaultEvent(final EventType eventType, final Object data) {
		this(eventType, data, null);
	}

	public DefaultEvent(final EventType eventType, final Object data, final Exception exception) {
		this(eventType, null, data, exception);
	}

	public DefaultEvent(final EventType eventType, final String qualifier, final Object data, final Exception exception) {
		this(new EventKey(eventType, qualifier), data, exception);
	}

	public DefaultEvent(final EventKey eventKey, final Object data, final Exception exception) {
		if (eventKey == null) {
			throw new IllegalArgumentException("An event key must be provided.");
		}
		if (eventKey.getEventType() == null) {
			throw new IllegalArgumentException("An event type must be provided.");
		}
		this.eventKey = eventKey;
		this.data = data;
		this.exception = exception;
	}

	@Override
	public EventKey getEventKey() {
		return eventKey;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public Exception getException() {
		return exception;
	}

}
