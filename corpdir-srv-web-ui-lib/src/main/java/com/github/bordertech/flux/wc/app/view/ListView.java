package com.github.bordertech.flux.wc.app.view;

import com.github.bordertech.flux.wc.view.DumbView;
import java.util.List;

/**
 * List view.
 *
 * @param <T> the item type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface ListView<T> extends DumbView<List<T>> {

	void setItems(final List<T> items);

	List<T> getItems();

	void refreshItems(final List<T> items);
}