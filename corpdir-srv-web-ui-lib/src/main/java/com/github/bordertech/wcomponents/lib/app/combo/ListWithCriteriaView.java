package com.github.bordertech.wcomponents.lib.app.combo;

import com.github.bordertech.wcomponents.WTemplate;
import com.github.bordertech.wcomponents.lib.app.ctrl.PollingListCtrl;
import com.github.bordertech.wcomponents.lib.app.toolbar.DefaultToolbarView;
import com.github.bordertech.wcomponents.lib.app.view.ListView;
import com.github.bordertech.wcomponents.lib.app.view.SearchView;
import com.github.bordertech.wcomponents.lib.app.view.ToolbarView;

/**
 * List View with a Text Search View.
 *
 * @author jonathan
 * @param <S> the search type
 * @param <T> the entity type
 */
public class ListWithCriteriaView<S, T> extends PollingListView<S, T> {

	private final ToolbarView toolbarView;

	private final SearchView<S> searchView;

	public ListWithCriteriaView(final SearchView<S> searchView, final ListView<T> listView) {
		super(listView);

		// Views
		this.toolbarView = new DefaultToolbarView();
		this.searchView = searchView;

		// Polling and List Ctrl
		PollingListCtrl ctrl = getPollingCtrl();
		ctrl.addView(searchView);

		// Add views to holder
		WTemplate content = getContent();
		content.addTaggedComponent("vw-toolbar", toolbarView);
		content.addTaggedComponent("vw-crit", searchView);

		// Default visibility
		listView.setContentVisible(false);
	}

	public SearchView<S> getSearchView() {
		return searchView;
	}

	public ToolbarView getToolbarView() {
		return toolbarView;
	}

}
