package com.github.bordertech.wcomponents.lib.app.ctrl;

import com.github.bordertech.wcomponents.lib.app.event.CollectionEventType;
import com.github.bordertech.wcomponents.lib.app.event.FormEventType;
import com.github.bordertech.wcomponents.lib.app.event.ModelEventType;
import com.github.bordertech.wcomponents.lib.app.event.ModelOutcomeEventType;
import com.github.bordertech.wcomponents.lib.app.event.SelectEventType;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;
import com.github.bordertech.wcomponents.lib.flux.Event;
import com.github.bordertech.wcomponents.lib.flux.Listener;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultController;

/**
 * Controller for a Form View and Select View.
 *
 * @author jonathan
 * @param <T> the select entity
 */
public class FormSelectCtrl<T> extends DefaultController {

	@Override
	public void setupController() {
		super.setupController();
		// Listeners

		// Model outcome event type Listeners
		for (ModelOutcomeEventType eventType : ModelOutcomeEventType.values()) {
			Listener listener = new Listener() {
				@Override
				public void handleEvent(final Event event) {
					handleFormCtrlEvents(event);
				}
			};
			registerListener(eventType, listener);
		}

		// ADD EVENT
		registerListener(ModelEventType.ADD, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				handleAddEvent();
			}
		});

		// Select EVENT
		registerListener(SelectEventType.SELECT, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				T selected = (T) event.getData();
				handleSelectEvent(selected);
			}
		});

		// LIST Reset
		registerListener(CollectionEventType.RESET_COLLECTION, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				handleListResetEvent();
			}
		});

	}

	@Override
	public void checkConfig() {
		super.checkConfig();
		if (getSelectView() == null) {
			throw new IllegalStateException("A list view has not been set.");
		}
	}

	public final SelectView<T> getSelectView() {
		return getComponentModel().selectView;
	}

	public final void setSelectView(final SelectView<T> selectView) {
		getOrCreateComponentModel().selectView = selectView;
		addView(selectView);
	}

	protected void handleFormCtrlEvents(final Event event) {
		ModelOutcomeEventType type = (ModelOutcomeEventType) event.getQualifier().getEventType();
		switch (type) {
			case CREATE_OK:
				handleCreateOkEvent((T) event.getData());
				break;
			case CREATE_ERROR:
				break;
			case DELETE_OK:
				handleDeleteOkEvent((T) event.getData());
				break;
			case UPDATE_OK:
				handleUpdateOkEvent((T) event.getData());
				break;

			case DELETE_ERROR:
			case REFRESH_ERROR:
			case REFRESH_OK:
			case UPDATE_ERROR:
				break;
		}

	}

	protected void handleAddEvent() {
		dispatchMessageReset();
		getSelectView().clearSelected();
	}

	protected void handleSelectEvent(final T selected) {
		dispatchMessageReset();
		doLoadEntity(selected);
	}

	protected void handleListResetEvent() {
		dispatchMessageReset();
		dispatchEvent(FormEventType.RESET_FORM);
	}

	protected void handleUpdateOkEvent(final T entity) {
		getSelectView().updateItem(entity);
		// Reload Entity
		doLoadEntity(entity);
	}

	protected void handleCreateOkEvent(final T entity) {
		getSelectView().addItem(entity);
		getSelectView().showList(true);
		getSelectView().setSelectedItem(entity);
		// Reload Entity
		doLoadEntity(entity);
	}

	protected void handleDeleteOkEvent(final T entity) {
		getSelectView().removeItem(entity);
		if (getSelectView().getViewBean().isEmpty()) {
			getSelectView().showList(false);
		}
		dispatchEvent(FormEventType.RESET_FORM);
	}

	protected void doLoadEntity(final T entity) {
		dispatchEvent(FormEventType.RESET_FORM);
		dispatchEvent(FormEventType.LOAD, entity);
	}

	@Override
	protected FormSelectModel newComponentModel() {
		return new FormSelectModel();
	}

	@Override
	protected FormSelectModel getComponentModel() {
		return (FormSelectModel) super.getComponentModel();
	}

	@Override
	protected FormSelectModel getOrCreateComponentModel() {
		return (FormSelectModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class FormSelectModel<T> extends CtrlModel {

		private SelectView<T> selectView;
	}

}
