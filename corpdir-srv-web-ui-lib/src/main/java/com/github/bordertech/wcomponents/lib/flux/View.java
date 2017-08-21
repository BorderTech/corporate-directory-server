package com.github.bordertech.wcomponents.lib.flux;

import java.io.Serializable;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface View extends Serializable {

	/**
	 *
	 * @return the dispatcher attached to this view.
	 */
	Dispatcher getDispatcher();

	/**
	 * @return the view qualifier (if needed)
	 */
	String getQualifier();

	/**
	 *
	 * @return the view controller
	 */
	Controller getController();

	/**
	 * @param controller the view controller
	 */
	void setController(final Controller controller);

	/**
	 * Reset the view to the default state
	 */
	void resetView();

	/**
	 *
	 * @return the view content holder.
	 */
	ViewContent getContent();

	/**
	 * Reset the view content.
	 */
	void resetContent();

	/**
	 * Make the view content visible.
	 */
	void makeContentVisible();

	/**
	 * Make the view content invisible.
	 */
	void makeContentInvisible();

}
