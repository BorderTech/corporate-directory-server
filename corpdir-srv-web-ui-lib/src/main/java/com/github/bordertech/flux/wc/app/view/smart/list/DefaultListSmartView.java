package com.github.bordertech.flux.wc.app.view.smart.list;

import com.github.bordertech.flux.wc.app.view.ListView;
import java.util.List;

/**
 * Polling View and List View.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the item type
 */
public class DefaultListSmartView<S, T> extends AbstractListSmartView<S, T, List<T>> implements ListView<T> {

	private final ListView<T> listView;

	public DefaultListSmartView(final String viewId, final ListView<T> listView) {
		super(viewId, "wclib/hbs/layout/combo-list-crit.hbs");
		// Add to Template
		this.listView = listView;
		addComponentToTemplate("vw-list", listView);
	}

	public ListView<T> getListView() {
		return listView;
	}

	@Override
	protected void handleResultSuccessful(final List<T> items) {
		setItems(items);
	}

	@Override
	public List<T> getViewBean() {
		return listView.getViewBean();
	}

	@Override
	public void setViewBean(final List<T> viewBean) {
		listView.setViewBean(viewBean);
	}

	@Override
	public void updateViewBean() {
		listView.updateViewBean();
	}

	@Override
	public boolean validateView() {
		return listView.validateView();
	}

	@Override
	public void setItems(final List<T> items) {
		listView.setItems(items);
	}

	@Override
	public List<T> getItems() {
		return listView.getItems();
	}

	@Override
	public void refreshItems(final List<T> items) {
		listView.refreshItems(items);
	}

}
