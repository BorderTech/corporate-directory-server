package com.github.bordertech.wcomponents.lib.flux;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface Controller extends Serializable {

	/**
	 *
	 * @return the dispatcher attached to this controller.
	 */
	Dispatcher getDispatcher();

	/**
	 * @return the qualifier to be used on listeners or events (if needed)
	 */
	String getQualifier();

	/**
	 *
	 * @return the views added to this controller
	 */
	List<View> getViews();

}
