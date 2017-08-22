package com.github.bordertech.wcomponents.lib.app.ctrl;

import com.github.bordertech.wcomponents.lib.app.event.ActionEventType;
import com.github.bordertech.wcomponents.lib.app.mode.EntityMode;
import com.github.bordertech.wcomponents.lib.app.view.EntityView;
import com.github.bordertech.wcomponents.lib.app.view.ToolbarView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.flux.Event;
import com.github.bordertech.wcomponents.lib.flux.Listener;
import com.github.bordertech.wcomponents.lib.model.ActionModel;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultController;

/**
 * Controller for an Entity View and Entity Action view.
 *
 * @param <T> the entity type
 * @author jonathan
 */
public class EntityWithToolbarCtrl<T> extends DefaultController {

	public EntityWithToolbarCtrl(final Dispatcher dispatcher) {
		this(dispatcher, null);
	}

	public EntityWithToolbarCtrl(final Dispatcher dispatcher, final String qualifier) {
		super(dispatcher, qualifier);

		// Entity Action Event Listeners
		for (ActionEventType eventType : ActionEventType.values()) {
			Listener listener = new Listener() {
				@Override
				public void handleEvent(final Event event) {
					handleEntityActionEvent(event);
				}
			};
			registerCtrlListener(listener, eventType);
		}
	}

	public final ToolbarView getToolbarView() {
		return getComponentModel().toolbarView;
	}

	public final void setToolbarView(final ToolbarView toolbarView) {
		getOrCreateComponentModel().toolbarView = toolbarView;
		addView(toolbarView);
	}

	public final EntityView<T> getEntityView() {
		return getComponentModel().entityView;
	}

	public final void setEntityView(final EntityView<T> entityView) {
		getOrCreateComponentModel().entityView = entityView;
		addView(entityView);
	}

	public ActionModel<T> getActionModel() {
		return (ActionModel<T>) getModel(ActionModel.class);
	}

	@Override
	protected void checkConfig() {
		super.checkConfig();
		if (getToolbarView() == null) {
			throw new IllegalStateException("An entity control view has not been set.");
		}
		if (getEntityView() == null) {
			throw new IllegalStateException("A entity view has not been set.");
		}
		if (getActionModel() == null) {
			throw new IllegalStateException("Entity service actions have not been set.");
		}
	}

	@Override
	public void configViews() {
		super.configViews();
		getToolbarView().setContentVisible(true);
		getEntityView().setContentVisible(false);
	}

	protected void handleEntityActionEvent(final Event event) {
		ActionEventType type = (ActionEventType) event.getEventType();
		switch (type) {
			case BACK:
				handleBackAction();
				break;
			case CANCEL:
				handleCancelAction();
				break;
			case DELETE:
				handleDeleteAction();
				break;
			case EDIT:
				handleEditAction();
				break;
			case REFRESH:
				handleRefreshAction();
				break;
			case CREATE:
				handleSaveAction(true);
				break;
			case UPDATE:
				handleSaveAction(false);
				break;
			case ADD:
				handleAddAction();
				break;
			case LOAD_OK:
				handleLoadedOKAction();
				break;

			case ENTITY_MODE_CHANGED:
				handleEntityModeChanged();
				break;
		}
	}

	protected void handleBackAction() {
		reset();
	}

	protected void handleCancelAction() {
		EntityView<T> view = getEntityView();
		if (view.getEntityMode() == EntityMode.EDIT) {
			T bean = view.getViewBean();
			resetViews();
			doLoad(bean, EntityMode.VIEW);
			return;
		}
		resetViews();
	}

	protected void handleEditAction() {
		EntityView<T> view = getEntityView();
		if (view.isLoaded()) {
			doEntityModeChange(EntityMode.EDIT);
		}
	}

	protected void handleDeleteAction() {
		EntityView<T> view = getEntityView();
		if (!view.isLoaded()) {
			return;
		}
		doEntityModeChange(EntityMode.VIEW);
		T bean = view.getViewBean();
		try {
			doDelete(bean);
			dispatchCtrlEvent(ActionEventType.DELETE_OK, bean);
			getViewMessages().success("Delete OK.");
			resetViews();
		} catch (Exception e) {
			getViewMessages().error("Delete failed. " + e.getMessage());
			dispatchCtrlEvent(ActionEventType.DELETE_ERROR, bean, e);
		}
	}

	protected void handleRefreshAction() {
		EntityView<T> view = getEntityView();
		if (!view.isLoaded()) {
			return;
		}
		T bean = view.getViewBean();
		resetViews();
		try {
			bean = doRefresh(bean);
			getViewMessages().success("Refreshed OK.");
			dispatchCtrlEvent(ActionEventType.REFRESH_OK, bean);
		} catch (Exception e) {
			getViewMessages().error("Refresh failed. " + e.getMessage());
			dispatchCtrlEvent(ActionEventType.REFRESH_ERROR, bean, e);
		}
	}

	protected void handleSaveAction(final boolean create) {
		EntityView<T> view = getEntityView();
		if (!view.isLoaded()) {
			return;
		}
		// Do Validation
		if (!view.validateView()) {
			return;
		}
		// Do Save
		try {
			view.updateViewBean();
			T bean = view.getViewBean();
			if (create) {
				bean = doCreate(bean);
				dispatchCtrlEvent(ActionEventType.CREATE_OK, bean);
			} else {
				bean = doUpdate(bean);
				dispatchCtrlEvent(ActionEventType.UPDATE_OK, bean);
			}
			getViewMessages().success("Saved OK.");
			doLoad(bean, EntityMode.VIEW);
		} catch (Exception e) {
			getViewMessages().error("Save failed. " + e.getMessage());
			if (create) {
				dispatchCtrlEvent(ActionEventType.CREATE_ERROR, e);
			} else {
				dispatchCtrlEvent(ActionEventType.UPDATE_ERROR, e);
			}
		}
	}

	protected void handleAddAction() {
		resetViews();
		try {
			T bean = doCreateInstance();
			doLoad(bean, EntityMode.ADD);
			doEntityModeChange(EntityMode.ADD);
		} catch (Exception e) {
			getViewMessages().error("ADD failed. " + e.getMessage());
			dispatchCtrlEvent(ActionEventType.LOAD_ERROR, e);
		}
	}

	protected void handleLoadedOKAction() {
		getToolbarView().setContentVisible(true);
		getEntityView().setContentVisible(true);
	}

	protected void handleEntityModeChanged() {
		// Keep Action View in SYNC
		EntityView entityView = getEntityView();
		ToolbarView actionView = getToolbarView();
		actionView.setEntityMode(entityView.getEntityMode());
		actionView.setEntityLoaded(entityView.isLoaded());
	}

	protected void doEntityModeChange(final EntityMode mode) {
		getEntityView().setEntityMode(mode);
	}

	protected void doLoad(final T entity, final EntityMode mode) {
		resetViews();
		getEntityView().loadEntity(entity, mode);
	}

	protected T doCreate(final T entity) {
		return getActionModel().create(entity);
	}

	protected T doUpdate(final T entity) {
		return getActionModel().update(entity);
	}

	protected T doRefresh(final T entity) {
		return getActionModel().refresh(entity);
	}

	protected void doDelete(final T entity) {
		getActionModel().delete(entity);
	}

	protected T doCreateInstance() {
		return getActionModel().createInstance();
	}

	@Override
	protected EntityCtrlModel<T> newComponentModel() {
		return new EntityCtrlModel();
	}

	@Override
	protected EntityCtrlModel<T> getComponentModel() {
		return (EntityCtrlModel<T>) super.getComponentModel();
	}

	@Override
	protected EntityCtrlModel<T> getOrCreateComponentModel() {
		return (EntityCtrlModel<T>) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class EntityCtrlModel<T> extends CtrlModel {

		private ToolbarView toolbarView;

		private EntityView<T> entityView;
	}

}
