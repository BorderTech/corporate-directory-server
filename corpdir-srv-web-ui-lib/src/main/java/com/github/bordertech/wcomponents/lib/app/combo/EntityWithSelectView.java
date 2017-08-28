package com.github.bordertech.wcomponents.lib.app.combo;

import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.WContainer;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WTemplate;
import com.github.bordertech.wcomponents.lib.app.ctrl.EntityWithSelectCtrl;
import com.github.bordertech.wcomponents.lib.app.mode.EntityMode;
import com.github.bordertech.wcomponents.lib.app.view.EntityView;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultComboView;
import com.github.bordertech.wcomponents.lib.mvc.impl.ViewMessages;

/**
 *
 * @author jonathan
 */
public class EntityWithSelectView<S, T> extends DefaultComboView implements MessageContainer, EntityView<T> {

	private final WMessages messages = new ViewMessages();

	private final EntityView<T> entityView;

	private final SelectView<T> selectView;

	private final EntityWithSelectCtrl<S, T> ctrl;

	public EntityWithSelectView(final Dispatcher dispatcher, final String qualifier, final EntityView<T> entityView, final SelectView<T> selectView) {
		super("wclib/hbs/layout/combo-ent-select.hbs", dispatcher, qualifier);

		this.entityView = entityView;
		this.selectView = selectView;
		this.ctrl = new EntityWithSelectCtrl<>(dispatcher, qualifier);

		ctrl.setEntityView(entityView);
		ctrl.setSelectView(selectView);

		WTemplate content = getContent();
		content.addTaggedComponent("vw-messages", messages);
		content.addTaggedComponent("vw-ctrl", ctrl);
		content.addTaggedComponent("vw-select", selectView);
		content.addTaggedComponent("vw-entity", entityView);

		messages.addHtmlClass("wc-margin-s-lg");
		selectView.addHtmlClass("wc-panel-type-box");
		entityView.addHtmlClass("wc-panel-type-box");

		// Default visibility
		entityView.setContentVisible(false);

	}

	public final EntityView<T> getEntityView() {
		return entityView;
	}

	public final SelectView<T> getSelectView() {
		return selectView;
	}

	@Override
	public EntityMode getEntityMode() {
		return entityView.getEntityMode();
	}

	@Override
	public void setEntityMode(final EntityMode mode) {
		entityView.setEntityMode(mode);
	}

	@Override
	public boolean isFormReadOnly() {
		return entityView.isFormReadOnly();
	}

	@Override
	public boolean isLoaded() {
		return entityView.isLoaded();
	}

	@Override
	public void loadEntity(final T entity, final EntityMode mode) {
		entityView.loadEntity(entity, mode);
	}

	@Override
	public T getViewBean() {
		return entityView.getViewBean();
	}

	@Override
	public void setViewBean(final T viewBean) {
		entityView.setViewBean(viewBean);
	}

	@Override
	public void updateViewBean() {
		entityView.updateViewBean();
	}

	@Override
	public boolean validateView() {
		return entityView.validateView();
	}

	@Override
	public WContainer getEntityDetailsHolder() {
		return entityView.getEntityDetailsHolder();
	}

	@Override
	public WMessages getMessages() {
		return messages;
	}

}
