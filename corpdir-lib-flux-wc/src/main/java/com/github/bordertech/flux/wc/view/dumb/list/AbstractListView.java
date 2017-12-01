package com.github.bordertech.flux.wc.view.dumb.list;

import com.github.bordertech.flux.wc.view.dumb.ListView;
import com.github.bordertech.flux.wc.view.DefaultDumbView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Basic list view.
 *
 * @author Jonathan Austin
 * @param <T> the item type
 * @since 1.0.0
 */
public class AbstractListView<T> extends DefaultDumbView<List<T>> implements ListView<T> {

	public AbstractListView(final String viewId) {
		super(viewId);
	}

	@Override
	public List<T> getItems() {
		List<T> current = getViewBean();
		return current == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(current);
	}

	@Override
	public void setItems(final List<T> items) {
		setViewBean(items);
	}

	@Override
	public void refreshItems(final List<T> items) {
		boolean wasVisible = isContentVisible();
		resetContent();
		setItems(items);
		setContentVisible(wasVisible);
	}

	@Override
	public void addItem(final T item) {
		List<T> items = getItems();
		if (!items.contains(item)) {
			items = new ArrayList<>(items);
			items.add(item);
			refreshItems(items);
		}
	}

	@Override
	public void removeItem(final T item) {
		List<T> items = getItems();
		if (items.contains(item)) {
			items = new ArrayList<>(items);
			items.remove(item);
			refreshItems(items);
		}
	}

	@Override
	public void updateItem(final T item) {
		List<T> items = getItems();
		if (items.contains(item)) {
			items = new ArrayList<>(items);
			items.remove(item);
			items.add(item);
			refreshItems(items);
		}
	}
}
