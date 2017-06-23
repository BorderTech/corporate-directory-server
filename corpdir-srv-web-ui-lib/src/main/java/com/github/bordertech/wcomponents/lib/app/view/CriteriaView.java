package com.github.bordertech.wcomponents.lib.app.view;

import com.github.bordertech.wcomponents.lib.view.BasicEventView;
import com.github.bordertech.wcomponents.lib.view.ViewAction;

/**
 * Entity Criteria View.
 *
 * @author Jonathan Austin
 * @param <T> the criteria type
 * @since 1.0.0
 *
 */
public interface CriteriaView<T> extends BasicEventView {

	T getCriteria();

	void registerViewAction(final ViewAction<CriteriaView<T>, CriteriaEvent> viewAction, final CriteriaEvent... viewEvent);

}
