package com.github.bordertech.wcomponents.lib.app.view;

/**
 * Entity select list view.
 *
 * @param <T> the entity type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface SelectListView<T> extends ListView<T> {

	ListMode getListMode();

	void setListMode(final ListMode mode);

	void clearSelectedIdx();

	void setSelectedIdx(final int idx);

	int getSelectedIdx();

	int getSize();

	T getSelected();

}
