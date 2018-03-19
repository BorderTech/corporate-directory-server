package com.github.bordertech.flux.wc.view.dumb;

import com.github.bordertech.wcomponents.TreeItemModel;
import java.util.List;

/**
 *
 * @author jonathan
 */
public interface TreeViewItemModel<T> extends TreeItemModel {

	String getItemId(final T item);

	T getItem(final List<Integer> row);

}
