package com.github.bordertech.flux.wc.app.view.smart;

import com.github.bordertech.flux.view.SmartView;
import com.github.bordertech.flux.wc.app.view.MessageView;

/**
 * Smart view that is a Message Container.
 *
 * @author jonathan
 */
public interface MessageSmartView<T> extends SmartView<T>, MessageView<T> {

	/**
	 *
	 * @return the message view
	 */
	MessageView<T> getMessageView();
}
