package com.github.bordertech.wcomponents.lib.app.view;

import com.github.bordertech.wcomponents.lib.flux.impl.BasicView;

/**
 * Navigation menu.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface NavigationView<T> extends BasicView<T> {

	int getCurrentIdx();

	void setCurrentIdx(final int idx);

	int getSize();

	void setSize(final int size);

	boolean isUseSwipe();

	void setUseSwipe(final boolean useSwipe);
}
