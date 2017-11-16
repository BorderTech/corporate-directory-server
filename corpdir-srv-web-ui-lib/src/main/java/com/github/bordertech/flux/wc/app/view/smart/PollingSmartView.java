package com.github.bordertech.flux.wc.app.view.smart;

import com.github.bordertech.flux.wc.app.view.PollingView;
import com.github.bordertech.flux.wc.view.SmartView;

/**
 * Smart view that is a Message Container.
 *
 * @author jonathan
 */
public interface PollingSmartView<T> extends SmartView<T>, PollingView<T> {

	/**
	 *
	 * @return the polling view
	 */
	PollingView<T> getPollingView();
}
