package com.github.bordertech.wcomponents.lib.app.view.smart.list;

import com.github.bordertech.wcomponents.lib.app.view.ListView;
import com.github.bordertech.wcomponents.lib.app.view.search.SearchTextView;

/**
 * List View with a Text Search View.
 *
 * @author jonathan
 * @param <T> the entity type
 */
public class ListWithSearchTextView<T> extends ListWithSearchView<String, T> {

	public ListWithSearchTextView(final String viewId, final ListView<T> listView) {
		super(viewId, new SearchTextView("vw-srch"), listView);
	}
}
