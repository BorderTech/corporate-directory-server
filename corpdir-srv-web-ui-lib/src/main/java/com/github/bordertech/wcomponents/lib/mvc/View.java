package com.github.bordertech.wcomponents.lib.mvc;

import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.SubordinateTarget;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.flux.EventType;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface View extends AjaxTarget, SubordinateTarget {

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
	 * @param qualifier the qualifier to be used on events
	 */
	void setQualifier(final String qualifier);

	/**
	 * Reset the view to the default state
	 */
	void resetView();

	/**
	 *
	 * @return the view content holder.
	 */
	WComponent getContent();

	/**
	 * Reset the view content.
	 */
	void resetContent();

	/**
	 * @return true if view content is visible
	 */
	boolean isContentVisible();

	/**
	 * @param visible true if make view content visible
	 */
	void setContentVisible(final boolean visible);

	/**
	 *
	 * @param target the AJAX target to add
	 * @param eventType the event the target is for
	 */
	void addEventTarget(final AjaxTarget target, final EventType... eventType);

	/**
	 * Validate the view. Will dispatch validation errors.
	 *
	 * @return true if valid
	 */
	boolean validateView();

	/**
	 * Sometimes an event has to be given a more specific qualifier. The same event might happen more then once in a
	 * Combo View.
	 *
	 * @param qualifier the qualifier value
	 * @param types the event type to override the qualifier
	 */
	void addDispatcherOverride(final String qualifier, final EventType... types);

}
