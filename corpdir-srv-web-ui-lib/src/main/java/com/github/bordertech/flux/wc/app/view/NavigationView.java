package com.github.bordertech.flux.wc.app.view;

import com.github.bordertech.flux.wc.view.DumbView;

/**
 * Navigation menu.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface NavigationView<T> extends DumbView<T> {

	int getCurrentIdx();

	void setCurrentIdx(final int idx);

	int getSize();

	void setSize(final int size);

	boolean isUseSwipe();

	void setUseSwipe(final boolean useSwipe);

}
