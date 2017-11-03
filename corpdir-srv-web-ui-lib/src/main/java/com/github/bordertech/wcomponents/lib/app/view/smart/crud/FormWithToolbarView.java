package com.github.bordertech.wcomponents.lib.app.view.smart.crud;

import com.github.bordertech.wcomponents.WContainer;
import com.github.bordertech.wcomponents.lib.app.mode.FormMode;
import com.github.bordertech.wcomponents.lib.app.model.keys.ActionModelKey;
import com.github.bordertech.wcomponents.lib.app.view.FormToolbarView;
import com.github.bordertech.wcomponents.lib.app.view.FormView;
import com.github.bordertech.wcomponents.lib.app.view.form.AbstractFormView;
import com.github.bordertech.wcomponents.lib.app.view.smart.msg.DefaultMessageSmartView;
import com.github.bordertech.wcomponents.lib.app.view.toolbar.DefaultFormToolbarView;

/**
 * Form View with a Toolbar View.
 *
 * @author jonathan
 * @param <T> the entity type
 */
public class FormWithToolbarView<T> extends DefaultMessageSmartView<T> implements FormView<T>, ActionModelKey {

	private final FormView<T> formView;

	private final FormToolbarView toolbarView;

	public FormWithToolbarView(final String viewId) {
		this(viewId, new AbstractFormView<T>("vw-entity"));
	}

	public FormWithToolbarView(final String viewId, final FormView<T> formView) {
		this(viewId, formView, new DefaultFormToolbarView("vw-toolbar"));
	}

	public FormWithToolbarView(final String viewId, final FormView<T> formView, final FormToolbarView toolbarView) {
		super(viewId, "wclib/hbs/layout/combo-ent-toolbar.hbs");

		// Views
		this.formView = formView;
		this.toolbarView = toolbarView;

		addComponentToTemplate("vw-toolbar", toolbarView);
		addComponentToTemplate("vw-form", formView);
	}

	public final FormView<T> getFormView() {
		return formView;
	}

	public final FormToolbarView getToolbarView() {
		return toolbarView;
	}

	@Override
	public FormMode getFormMode() {
		return formView.getFormMode();
	}

	@Override
	public void setFormMode(final FormMode mode) {
		formView.setFormMode(mode);
	}

	@Override
	public boolean isFormReadonly() {
		return formView.isFormReadonly();
	}

	@Override
	public boolean isLoaded() {
		return formView.isLoaded();
	}

	@Override
	public void loadEntity(final T entity, final FormMode mode) {
		formView.loadEntity(entity, mode);
	}

	@Override
	public T getViewBean() {
		return formView.getViewBean();
	}

	@Override
	public void setViewBean(final T viewBean) {
		formView.setViewBean(viewBean);
	}

	@Override
	public void updateViewBean() {
		formView.updateViewBean();
	}

	@Override
	public boolean validateView() {
		return formView.validateView();
	}

	@Override
	public WContainer getFormHolder() {
		return formView.getFormHolder();
	}

	@Override
	public void setActionModelKey(final String key) {
//		ctrl.setActionModelKey(key);
	}

	@Override
	public String getActionModelKey() {
		return "";
//		return ctrl.getActionModelKey();
	}

//		// MODE CHANGED
//		registerListener(FormEventType.ENTITY_MODE_CHANGED, new Listener() {
//			@Override
//			public void handleEvent(final Event event) {
//				handleSyncToolbar();
//			}
//		});
//
//		// LOADED OK
//		registerListener(FormEventType.LOAD_OK, new Listener() {
//			@Override
//			public void handleEvent(final Event event) {
//				handleSyncToolbar();
//			}
//		});
//
//		// RESET FORM
//		registerListener(FormEventType.RESET_FORM, new Listener() {
//			@Override
//			public void handleEvent(final Event event) {
//				resetViews();
//			}
//		});
}
