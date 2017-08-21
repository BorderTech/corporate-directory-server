package com.github.bordertech.wcomponents.lib.app;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.AjaxTrigger;
import com.github.bordertech.wcomponents.MenuItem;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WMenu;
import com.github.bordertech.wcomponents.WMenuItem;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.lib.app.event.ActionEventType;
import com.github.bordertech.wcomponents.lib.app.mode.EntityMode;
import com.github.bordertech.wcomponents.lib.app.view.ToolbarView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.flux.EventType;
import com.github.bordertech.wcomponents.lib.flux.wc.DefaultView;
import com.github.bordertech.wcomponents.lib.flux.wc.WViewContent;
import java.util.Arrays;
import java.util.List;

/**
 * Action menu bar implementation.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultToolbarView extends DefaultView implements ToolbarView {

	private final WMenu actionMenu = new WMenu();

	private final WMenuItem itemBack = new MyMenuItem("Back", ActionEventType.BACK) {
		@Override
		public boolean isVisible() {
			return isUseBack();
		}
	};

	private final WMenuItem itemEdit = new MyMenuItem("Edit", ActionEventType.EDIT) {
		@Override
		public boolean isVisible() {
			return isEntityLoaded();
		}

		@Override
		public boolean isDisabled() {
			EntityMode mode = getEntityMode();
			return !EntityMode.VIEW.equals(mode);
		}
	};

	private final WMenuItem itemCancel = new MyMenuItem("Cancel", ActionEventType.CANCEL) {
		@Override
		public boolean isVisible() {
			return isEntityLoaded();
		}

		@Override
		public boolean isDisabled() {
			EntityMode mode = getEntityMode();
			return EntityMode.VIEW.equals(mode);
		}

		@Override
		public boolean isCancel() {
			return true;
		}
	};

	private final WMenuItem itemRefresh = new MyMenuItem("Refresh", ActionEventType.REFRESH) {
		@Override
		public boolean isVisible() {
			return isEntityLoaded();
		}

		@Override
		public boolean isDisabled() {
			EntityMode mode = getEntityMode();
			return EntityMode.ADD == mode;
		}

	};

	private final WMenuItem itemUpdate = new MyMenuItem("Save", ActionEventType.UPDATE) {
		@Override
		public boolean isVisible() {
			return getEntityMode() == EntityMode.EDIT;
		}
	};

	private final WMenuItem itemCreate = new MyMenuItem("Save", ActionEventType.CREATE) {
		@Override
		public boolean isVisible() {
			return getEntityMode() == EntityMode.ADD;
		}
	};

	private final WMenuItem itemDelete = new MyMenuItem("Delete", ActionEventType.DELETE) {
		@Override
		public boolean isVisible() {
			return isEntityLoaded();
		}

		@Override
		public boolean isDisabled() {
			EntityMode mode = getEntityMode();
			return EntityMode.ADD == mode;
		}
	};

	private final WMenuItem itemAdd = new MyMenuItem("Add", ActionEventType.ADD) {
		@Override
		public boolean isDisabled() {
			EntityMode mode = getEntityMode();
			return EntityMode.ADD == mode || EntityMode.EDIT == mode;
		}
	};

	private final WPanel ajaxPanel = new WPanel() {
		@Override
		public boolean isHidden() {
			return true;
		}

	};

	/**
	 * Construct the Menu Bar.
	 *
	 * @param dispatcher the controller for this view
	 */
	public DefaultToolbarView(final Dispatcher dispatcher) {
		this(dispatcher, null);
	}

	/**
	 * Construct the Menu Bar.
	 *
	 * @param dispatcher the controller for this view
	 * @param qualifier the qualifier
	 */
	public DefaultToolbarView(final Dispatcher dispatcher, final String qualifier) {
		super(dispatcher, qualifier);

		WViewContent holder = getContent();

		holder.add(actionMenu);
		holder.add(ajaxPanel);

		// Menu Items
		actionMenu.add(itemBack);
		actionMenu.add(itemEdit);
		actionMenu.add(itemUpdate);
		actionMenu.add(itemCreate);
		actionMenu.add(itemCancel);
		actionMenu.add(itemDelete);
		actionMenu.add(itemRefresh);
		actionMenu.add(itemAdd);

		// Action
		Action action = new Action() {
			@Override
			public void execute(final ActionEvent event) {
				MyMenuItem item = (MyMenuItem) event.getSource();
				doDispatchToolbarEvent(item.getItemEvent());
			}
		};

		// Add Action and AJAX control for each menu item
		for (MenuItem menuItem : actionMenu.getMenuItems()) {
			WMenuItem item = (WMenuItem) menuItem;
			item.setAction(action);
			ajaxPanel.add(new MyAjaxControl(item));
		}
	}

	protected void addTargets(final List<AjaxTarget> targets) {
		// Add a target to each AJAX control
		for (WComponent child : ajaxPanel.getChildren()) {
			WAjaxControl ctrl = (WAjaxControl) child;
			ctrl.addTargets(targets);
		}
	}

	@Override
	public void setEntityMode(final EntityMode mode) {
		getOrCreateComponentModel().entityMode = mode == null ? EntityMode.VIEW : mode;
	}

	@Override
	public EntityMode getEntityMode() {
		return getComponentModel().entityMode;
	}

	@Override
	public boolean isEntityLoaded() {
		return getComponentModel().entityLoaded;
	}

	@Override
	public void setEntityLoaded(final boolean entityLoaded) {
		getOrCreateComponentModel().entityLoaded = entityLoaded;
	}

	@Override
	public boolean isUseBack() {
		return getComponentModel().useBack;
	}

	@Override
	public void setUseBack(final boolean useBack) {
		getOrCreateComponentModel().useBack = useBack;
	}

	@Override
	public void addEventTarget(final AjaxTarget target, final EventType... eventType) {
		addTargets(Arrays.asList(target));
	}

	protected void doDispatchToolbarEvent(final ActionEventType eventType) {
		dispatchViewEvent(eventType);
	}

	@Override
	protected EntityViewModel newComponentModel() {
		return new EntityViewModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntityViewModel getComponentModel() {
		return (EntityViewModel) super.getComponentModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntityViewModel getOrCreateComponentModel() {
		return (EntityViewModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class EntityViewModel extends DefaultView.ViewModel {

		private EntityMode entityMode = EntityMode.VIEW;

		private boolean entityLoaded;

		private boolean useBack;
	}

	private static class MyMenuItem extends WMenuItem {

		private final ActionEventType event;

		public MyMenuItem(final String text, final ActionEventType event) {
			super(text);
			this.event = event;
		}

		public ActionEventType getItemEvent() {
			return event;
		}
	}

	private static class MyAjaxControl extends WAjaxControl {

		public MyAjaxControl(final AjaxTrigger trigger) {
			super(trigger);
		}

		@Override
		public boolean isVisible() {
			return getTrigger().isVisible();
		}
	}
}
