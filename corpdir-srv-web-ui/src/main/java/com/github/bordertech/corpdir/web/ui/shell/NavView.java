package com.github.bordertech.corpdir.web.ui.shell;

/**
 * Entity action menu.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface NavView extends BasicEventView {

	void setPosition(final int idx, final int size);

	int getCurrentIdx();

	int getSize();

	public void addViewAction(final NavEvent viewEvent, final ViewAction<NavView, NavEvent> viewAction);
}
