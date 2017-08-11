package com.github.bordertech.wcomponents.lib.app.view;

import com.github.bordertech.wcomponents.lib.flux.impl.BasicView;

/**
 * Entity Criteria View.
 *
 * @author Jonathan Austin
 * @param <T> the criteria type
 * @since 1.0.0
 *
 */
public interface CriteriaView<T> extends BasicView {

	T getCriteria();

}
