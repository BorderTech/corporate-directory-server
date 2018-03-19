package com.github.bordertech.flux.wc.view.dumb.select;

import com.github.bordertech.flux.wc.common.FluxAjaxControl;
import com.github.bordertech.flux.wc.mode.SelectMode;
import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.MenuItem;
import com.github.bordertech.wcomponents.MenuSelectContainer;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WMenu;
import com.github.bordertech.wcomponents.WMenuItem;
import com.github.bordertech.wcomponents.addons.common.WDiv;
import java.util.List;
import java.util.Objects;

/**
 * Single select menu view.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class MenuSelectView<T> extends AbstractListSingleSelectView<T> {

	private final WMenu menu = new WMenu(WMenu.MenuType.TREE) {
		@Override
		protected void preparePaintComponent(final Request request) {
			super.preparePaintComponent(request);
			findMenuItem();
		}

		@Override
		public boolean isDisabled() {
			return isViewMode();
		}
	};

	private final WDiv ajaxPanel = new WDiv();

	public MenuSelectView(final String viewId) {
		super(viewId);
		getContent().add(menu);
		menu.setSelectionMode(MenuSelectContainer.SelectionMode.SINGLE);
		getContent().add(ajaxPanel);
		setSelectMode(SelectMode.SELECT);
	}

	@Override
	protected void initViewContent(final Request request) {
		// Build menu before calling super (which adds AJAX)
		List<T> beans = getViewBean();
		if (beans != null && !beans.isEmpty()) {
			setupMenu(beans);
		}
		super.initViewContent(request);
	}

	protected void setupMenu(final List<T> beans) {
		int i = 0;
		for (T bean : beans) {
			final WMenuItem item = new WMenuItem(bean.toString());
			item.setActionObject(i++);
			item.setAction(new Action() {
				@Override
				public void execute(final ActionEvent event) {
					Integer idx = (Integer) event.getActionObject();
					handleMenuItemSelected((WMenuItem) event.getSource(), idx);
				}
			});
			menu.add(item);
			FluxAjaxControl ctrl = new FluxAjaxControl(item, this);
			ajaxPanel.add(ctrl);
			registerSelectUnselectAjaxControl(ctrl);
		}
	}

	protected void handleMenuItemSelected(final WMenuItem menuItem, final int idx) {
		T item = getViewBean().get(idx);
		setSelectedItem(item);
		doDispatchSelectEvent();
	}

	protected void findMenuItem() {
		T item = getSelectedItem();
		if (item != null) {
			int idx = getViewBean().indexOf(item);
			for (MenuItem menuItem : menu.getMenuItems()) {
				if (menuItem instanceof WMenuItem) {
					Integer menuIdx = (Integer) ((WMenuItem) menuItem).getActionObject();
					if (Objects.equals(menuIdx, idx)) {
						menu.setSelectedMenuItem((WMenuItem) menuItem);
						return;
					}
				}
			}
		}
		menu.setSelectedMenuItem(null);
	}

	@Override
	public void addItem(final T item) {
		super.addItem(item);
		refreshItems(getItems());
	}

	@Override
	public void removeItem(final T entity) {
		super.removeItem(entity);
		refreshItems(getItems());
	}

	@Override
	public void updateItem(final T item) {
		super.updateItem(item);
		refreshItems(getItems());
	}

}
