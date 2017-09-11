package com.github.bordertech.wcomponents.lib.app.view.combo;

import com.github.bordertech.wcomponents.lib.app.mode.SelectMode;
import com.github.bordertech.wcomponents.lib.app.view.SearchView;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;

/**
 * Select view with a criteria view.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the entity type
 */
public class SelectWithCriteriaView<S, T> extends ListWithCriteriaView<S, T> implements SelectView<T> {

	public SelectWithCriteriaView(final SearchView<S> criteriaView, final SelectView<T> listView) {
		super(criteriaView, listView);
	}

	@Override
	public SelectView<T> getListView() {
		return (SelectView<T>) super.getListView();
	}

	@Override
	public SelectMode getListMode() {
		return getListView().getListMode();
	}

	@Override
	public void setListMode(SelectMode mode) {
		getListView().setListMode(mode);
	}

	@Override
	public void clearSelected() {
		getListView().clearSelected();
	}

	@Override
	public T getSelectedItem() {
		return getListView().getSelectedItem();
	}

	@Override
	public void setSelectedItem(final T item) {
		getListView().setSelectedItem(item);
	}

	@Override
	public void doMakeFormReadonly(final boolean readonly) {
		getListView().doMakeFormReadonly(readonly);
	}

	@Override
	public int getIndexOfItem(final T item) {
		return getListView().getIndexOfItem(item);
	}

	@Override
	public T getItem(final int idx) {
		return getListView().getItem(idx);
	}

}
