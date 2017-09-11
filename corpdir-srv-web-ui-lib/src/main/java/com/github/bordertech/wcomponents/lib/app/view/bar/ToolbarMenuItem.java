package com.github.bordertech.wcomponents.lib.app.view.bar;

import com.github.bordertech.wcomponents.WMenuItem;
import com.github.bordertech.wcomponents.lib.flux.EventType;

/**
 *
 * @author jonathan
 */
public class ToolbarMenuItem extends WMenuItem {

	private final EventType event;
	private final Object data;

	public ToolbarMenuItem(final String text, final EventType event) {
		this(text, event, null);
	}

	public ToolbarMenuItem(final String text, final EventType event, final Object data) {
		super(text);
		this.event = event;
		this.data = data;
	}

	public EventType getItemEvent() {
		return event;
	}

	public Object getItemData() {
		return data;
	}

}
