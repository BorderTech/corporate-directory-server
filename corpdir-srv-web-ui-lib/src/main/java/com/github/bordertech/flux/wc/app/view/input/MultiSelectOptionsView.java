package com.github.bordertech.flux.wc.app.view.input;

import com.github.bordertech.flux.wc.app.view.InputOptionsView;
import com.github.bordertech.wcomponents.AbstractWMultiSelectList;

/**
 * Multi select options view.
 *
 * @param <T> the option type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface MultiSelectOptionsView<T> extends MultiSelectable<T>, InputOptionsView<T> {

	@Override
	AbstractWMultiSelectList getSelectInput();

}
