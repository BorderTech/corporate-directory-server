package com.github.bordertech.wcomponents.lib.app.view.search;

import com.github.bordertech.flux.wc.view.DumbView;
import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.lib.app.common.AppAjaxControl;
import com.github.bordertech.wcomponents.lib.app.view.event.SearchViewEvent;
import com.github.bordertech.wcomponents.lib.app.view.SearchView;
import com.github.bordertech.wcomponents.lib.icons.IconConstants;

/**
 * Default search view.
 *
 * @param <T> the criteria type
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class AbstractSearchView<T> extends DumbView<T> implements SearchView<T> {

	private final WButton searchButton = new WButton("Search");

	private final AppAjaxControl ajax = new AppAjaxControl(searchButton);

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
		searchButton.setImageUrl(IconConstants.SEARCH_IMAGE);
		searchButton.setRenderAsLink(true);

		getContent().add(ajax);
		registerEventAjaxControl(SearchViewEvent.SEARCH_VALIDATING, ajax);
		registerEventAjaxControl(SearchViewEvent.SEARCH, ajax);
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
		dispatchViewEvent(SearchViewEvent.SEARCH_VALIDATING);
	}

	/**
	 * Dispatch the search event.
	 */
	protected void doDispatchSearchEvent() {
		T criteria = getViewBean();
		dispatchViewEvent(SearchViewEvent.SEARCH, criteria);
	}

}
