package com.github.bordertech.flux.wc.view.smart.tree;

import com.github.bordertech.flux.crud.store.EntityTreeStore;
import com.github.bordertech.flux.crud.store.RetrieveActionException;
import com.github.bordertech.flux.wc.view.dumb.tree.TreeViewItemModel;
import com.github.bordertech.wcomponents.AbstractTreeItemModel;
import com.github.bordertech.wcomponents.util.SystemException;
import com.github.bordertech.wcomponents.util.TableUtil;
import java.util.List;
import java.util.Objects;

/**
 * Tree Model that retrieves it data via a RetrieveTreeApi.
 *
 * @author jonathan
 * @param <K> the key type
 * @param <T> the item type
 */
public class EntityStoreTreeItemModel<K, T> extends AbstractTreeItemModel implements TreeViewItemModel<T> {

	private final T EMPTY_ITEM = null;
	private final List<T> rootItems;
	private final EntityTreeStore<T> model;

	public EntityStoreTreeItemModel(final List<T> rootItems, final EntityTreeStore<T> model) {
		this.rootItems = rootItems;
		this.model = model;
	}

	@Override
	public String getItemId(final T item) {
		return model.getItemId(item);
	}

	@Override
	public T getItem(final List<Integer> row) {
		T item = getRootItems().get(row.get(0));
		for (int i = 1; i < row.size(); i++) {
			item = loadChildren(item).get(row.get(i));
		}
		return item;
	}

	@Override
	public String getItemId(final List<Integer> row) {
		T item = getItem(row);
		String id = model.getItemId(item);
		return id == null ? TableUtil.rowIndexListToString(row) : id;
	}

	@Override
	public String getItemLabel(final List<Integer> row) {
		T item = getItem(row);
		return model.getItemLabel(item);
	}

	@Override
	public int getRowCount() {
		return getRootItems().size();
	}

	@Override
	public int getChildCount(final List<Integer> row) {
		T item = getItem(row);
		return loadChildren(item).size();
	}

	@Override
	public boolean hasChildren(final List<Integer> row) {
		T item = getItem(row);
		try {
			return model.hasChildren(item);
		} catch (RetrieveActionException e) {
			throw new SystemException("Could not retrieve child count. " + e.getMessage(), e);
		}
	}

	@Override
	public boolean isDisabled(final List<Integer> row) {
		T item = getItem(row);
		return Objects.equals(item, EMPTY_ITEM);
	}

	protected List<T> getRootItems() {
		return rootItems;
	}

	protected List<T> loadChildren(final T item) {
		try {
			return model.getChildren(item);
		} catch (Exception e) {
			throw new SystemException("Could not load child items. " + e.getMessage(), e);
		}
	}

}
