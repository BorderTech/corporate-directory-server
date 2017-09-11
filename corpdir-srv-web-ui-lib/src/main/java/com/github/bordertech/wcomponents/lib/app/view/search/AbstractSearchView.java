package com.github.bordertech.wcomponents.lib.app.view.search;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.lib.app.common.AppAjaxControl;
import com.github.bordertech.wcomponents.lib.app.event.SearchEventType;
import com.github.bordertech.wcomponents.lib.app.view.SearchView;
import com.github.bordertech.wcomponents.lib.flux.EventType;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultView;

/**
 * Default search view.
 *
 * @param <T> the criteria type
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class AbstractSearchView<T> extends DefaultView<T> implements SearchView<T> {

	private final WButton searchButton = new WButton("Search");

	private final WAjaxControl ajax = new AppAjaxControl(searchButton);

	public AbstractSearchView() {
		searchButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				doDispatchStartSearchEvent();
				if (validateView()) {
					doSearchButtonAction();
				}
			}
		});

		getContent().add(ajax);
	}

	public final WButton getSearchButton() {
		return searchButton;
	}

	/**
	 * The search button action.
	 */
	protected void doSearchButtonAction() {
		updateViewBean();
		doDispatchSearchEvent();
	}

	/**
	 * Dispatch the search event.
	 */
	protected void doDispatchStartSearchEvent() {
		dispatchEvent(SearchEventType.SEARCH_VALIDATING);
	}

	/**
	 * Dispatch the search event.
	 */
	protected void doDispatchSearchEvent() {
		T criteria = getViewBean();
		dispatchEvent(SearchEventType.SEARCH, criteria);
	}

	@Override
	public void addEventTarget(final AjaxTarget target, final EventType... eventType) {
		super.addEventTarget(target, eventType);
		addEventTargetsToAjaxCtrl(ajax, target);
	}

}
