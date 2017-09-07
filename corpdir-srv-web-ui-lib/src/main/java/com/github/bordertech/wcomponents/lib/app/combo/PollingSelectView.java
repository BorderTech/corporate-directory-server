package com.github.bordertech.wcomponents.lib.app.combo;

import com.github.bordertech.wcomponents.lib.app.mode.ListMode;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;

/**
 * Select view with a criteria view.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the entity type
 */
public class PollingSelectView<S, T> extends PollingListView<S, T> implements SelectView<T> {

	public PollingSelectView(final SelectView<T> listView) {
		this(listView, null);
	}

	public PollingSelectView(final SelectView<T> listView, final String qualifier) {
		super(listView, qualifier);
	}

	@Override
	public SelectView<T> getListView() {
		return (SelectView<T>) super.getListView();
	}

	@Override
	public ListMode getListMode() {
		return getListView().getListMode();
	}

	@Override
	public void setListMode(ListMode mode) {
		getListView().setListMode(mode);
	}

	@Override
	public void clearSelectedIdx() {
		getListView().clearSelectedIdx();
	}

	@Override
	public void setSelectedIdx(int idx) {
		getListView().setSelectedIdx(idx);
	}

	@Override
	public int getSelectedIdx() {
		return getListView().getSelectedIdx();
	}

	@Override
	public int getSize() {
		return getListView().getSize();
	}

	@Override
	public T getSelected() {
		return getListView().getSelected();
	}

	@Override
	public void setSelected(final T entity) {
		getListView().setSelected(entity);
	}
}
