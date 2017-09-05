package com.github.bordertech.wcomponents.lib.app;

import com.github.bordertech.wcomponents.Container;
import com.github.bordertech.wcomponents.Input;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WContainer;
import com.github.bordertech.wcomponents.lib.app.event.ActionEventType;
import com.github.bordertech.wcomponents.lib.app.mode.FormMode;
import com.github.bordertech.wcomponents.lib.app.view.FormView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultViewBound;
import com.github.bordertech.wcomponents.lib.mvc.msg.MsgEventType;

/**
 * Default form view.
 *
 * @param <T> the entity type
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultFormView<T> extends DefaultViewBound<T> implements FormView<T> {

	public DefaultFormView(final Dispatcher dispatcher) {
		this(dispatcher, null);
	}

	public DefaultFormView(final Dispatcher dispatcher, final String qualifier) {
		super(dispatcher, qualifier);
	}

	@Override
	public void setFormMode(final FormMode mode) {
		if (getFormMode() != mode) {
			getOrCreateComponentModel().entityMode = mode == null ? FormMode.NONE : mode;
			doRefreshViewState();
			doDispatchChangeModeEvent();
		}
	}

	@Override
	public FormMode getFormMode() {
		return getComponentModel().entityMode;
	}

	public void doRefreshViewState() {
		doMakeReadOnly(getContent(), isFormReadOnly());
	}

	/**
	 * @return true if form read only
	 */
	@Override
	public boolean isFormReadOnly() {
		FormMode mode = getFormMode();
		return !(FormMode.ADD.equals(mode) || FormMode.EDIT.equals(mode));
	}

	@Override
	public boolean isLoaded() {
		FormMode mode = getFormMode();
		return !FormMode.NONE.equals(mode);
	}

	@Override
	public void loadEntity(final T entity, final FormMode mode) {
		resetView();
		setViewBean(entity);
		setFormMode(mode);
		doDispatchLoadOKEvent();
	}

	@Override
	public WContainer getFormHolder() {
		return getContent();
	}

	@Override
	protected void initViewContent(final Request request) {
		super.initViewContent(request);
		// Check entity is loaded
		if (!isLoaded()) {
			dispatchMessage(MsgEventType.ERROR, "No entity has been loaded.");
			setContentVisible(false);
			return;
		}
		doRefreshViewState();
	}

	protected void doMakeReadOnly(final WComponent component, final boolean readOnly) {
		if (component instanceof Input) {
			((Input) component).setReadOnly(readOnly);
		}

		if (component instanceof Container) {
			for (WComponent child : ((Container) component).getChildren()) {
				doMakeReadOnly(child, readOnly);
			}
		}
	}

	protected void doDispatchLoadOKEvent() {
		dispatchViewEvent(ActionEventType.LOAD_OK, getViewBean());
	}

	protected void doDispatchChangeModeEvent() {
		dispatchViewEvent(ActionEventType.ENTITY_MODE_CHANGED, getFormMode());
	}

	@Override
	protected FormModel newComponentModel() {
		return new FormModel();
	}

	@Override
	protected FormModel getComponentModel() {
		return (FormModel) super.getComponentModel();
	}

	@Override
	protected FormModel getOrCreateComponentModel() {
		return (FormModel) super.getOrCreateComponentModel();
	}

	public static class FormModel extends ViewModel {

		private FormMode entityMode = FormMode.NONE;
	}
}
