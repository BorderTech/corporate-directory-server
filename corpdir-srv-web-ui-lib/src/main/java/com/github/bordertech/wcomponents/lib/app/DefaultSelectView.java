package com.github.bordertech.wcomponents.lib.app;

import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.lib.app.mode.ListMode;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import java.util.List;

/**
 * Abstract list view.
 *
 * @author Jonathan Austin
 * @param <T> the view bean
 * @since 1.0.0
 */
public class DefaultSelectView<T> extends DefaultListView<T> implements SelectView<T> {

	public DefaultSelectView(final Dispatcher dispatcher) {
		this(dispatcher, null);
	}

	public DefaultSelectView(final Dispatcher dispatcher, final String qualifier) {
		super(dispatcher, qualifier);
	}

	@Override
	public void setListMode(final ListMode mode) {
		getOrCreateComponentModel().listMode = mode == null ? ListMode.VIEW : mode;
		handleViewState();
	}

	@Override
	public ListMode getListMode() {
		return getComponentModel().listMode;
	}

	@Override
	public void setSelectedIdx(final int idx) {
		int size = getSize();
		if (idx < -1 || idx >= size) {
			throw new IllegalArgumentException("Selected index is invalid. Index: " + idx + " size: " + size + ".");
		}
		getOrCreateComponentModel().selectedIdx = idx;
	}

	@Override
	public int getSelectedIdx() {
		return getComponentModel().selectedIdx;
	}

	@Override
	public void clearSelectedIdx() {
		if (getSelectedIdx() != -1) {
			getOrCreateComponentModel().selectedIdx = -1;
		}
	}

	@Override
	public int getSize() {
		List<T> items = getViewBean();
		return items == null ? 0 : items.size();
	}

	@Override
	public T getSelected() {
		int idx = getSelectedIdx();
		if (idx < 0) {
			return null;
		}
		List<T> items = getViewBean();
		return items.get(idx);
	}

	@Override
	public void setSelected(final T entity) {
		// Find Bean
		int idx = entity == null ? -1 : getViewBean().indexOf(entity);
		setSelectedIdx(idx);
	}

	@Override
	protected void initViewContent(final Request request) {
		super.initViewContent(request);
		handleViewState();
	}

	protected void handleViewState() {
		// Do nothing
	}

	@Override
	protected SelectModel newComponentModel() {
		return new SelectModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SelectModel getComponentModel() {
		return (SelectModel) super.getComponentModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SelectModel getOrCreateComponentModel() {
		return (SelectModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class SelectModel extends ViewModel {

		private ListMode listMode = ListMode.VIEW;

		private int selectedIdx = -1;
	}

}
