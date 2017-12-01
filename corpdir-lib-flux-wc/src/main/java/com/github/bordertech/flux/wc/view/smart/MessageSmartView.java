package com.github.bordertech.flux.wc.view.smart;

import com.github.bordertech.flux.wc.view.dumb.MessageView;
import com.github.bordertech.flux.wc.view.FluxSmartView;

/**
 * Smart view that is a Message Container.
 *
 * @author jonathan
 */
public interface MessageSmartView<T> extends FluxSmartView<T>, MessageView<T> {

	/**
	 *
	 * @return the message view
	 */
	MessageView<T> getMessageView();
}