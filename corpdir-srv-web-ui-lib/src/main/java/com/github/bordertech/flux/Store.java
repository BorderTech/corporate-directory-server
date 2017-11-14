package com.github.bordertech.flux;

import java.io.Serializable;

/**
 * Store the current state for views.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface Store extends Serializable {

	/**
	 * @return the store identifier
	 */
	StoreKey getStoreKey();

	/**
	 * Register the event listeners.
	 */
	void registerListeners();

	/**
	 * Unregister the event listeners.
	 */
	void unregisterListeners();

	/**
	 * Dispatch the change event.
	 */
	void dispatchChangeEvent();

	/**
	 * @return the attached dispatcher.
	 */
	Dispatcher getDispatcher();
}
