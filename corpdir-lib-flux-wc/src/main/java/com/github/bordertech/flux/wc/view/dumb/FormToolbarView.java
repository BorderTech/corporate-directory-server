package com.github.bordertech.flux.wc.view.dumb;

import com.github.bordertech.flux.wc.mode.FormMode;

/**
 * Form toolbar view.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface FormToolbarView<T> extends ToolbarView<T> {

	FormMode getFormMode();

	void setFormMode(final FormMode mode);

	boolean isFormReady();

	void setFormReady(final boolean formReady);

}
