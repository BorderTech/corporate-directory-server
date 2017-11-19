package com.github.bordertech.flux.wc.app.view.smart.crud;

import com.github.bordertech.flux.app.action.CallType;
import com.github.bordertech.flux.app.store.retrieve.EntityStore;
import com.github.bordertech.flux.app.store.retrieve.SearchStore;
import com.github.bordertech.flux.util.FluxUtil;
import com.github.bordertech.flux.view.DefaultSmartView;
import com.github.bordertech.flux.view.ViewEventType;
import com.github.bordertech.flux.wc.app.view.FormToolbarView;
import com.github.bordertech.flux.wc.app.view.FormView;
import com.github.bordertech.flux.wc.app.view.MessageView;
import com.github.bordertech.flux.wc.app.view.PollingView;
import com.github.bordertech.flux.wc.app.view.SearchView;
import com.github.bordertech.flux.wc.app.view.SelectSingleView;
import com.github.bordertech.flux.wc.app.view.ToolbarView;
import com.github.bordertech.flux.wc.app.view.dumb.form.DefaultFormView;
import com.github.bordertech.flux.wc.app.view.dumb.list.MenuSelectView;
import com.github.bordertech.flux.wc.app.view.dumb.msg.DefaultMessageView;
import com.github.bordertech.flux.wc.app.view.dumb.polling.DefaultPollingView;
import com.github.bordertech.flux.wc.app.view.dumb.search.SearchTextView;
import com.github.bordertech.flux.wc.app.view.dumb.toolbar.DefaultFormToolbarView;
import com.github.bordertech.flux.wc.app.view.dumb.toolbar.DefaultToolbarView;
import com.github.bordertech.flux.wc.app.view.dumb.toolbar.ToolbarModifyItemType;
import com.github.bordertech.flux.wc.app.view.event.base.FormBaseEventType;
import com.github.bordertech.flux.wc.app.view.event.base.FormBaseOutcomeEventType;
import com.github.bordertech.flux.wc.app.view.event.base.PollingBaseEventType;
import com.github.bordertech.flux.wc.app.view.event.base.SearchBaseEventType;
import com.github.bordertech.flux.wc.app.view.event.base.SelectBaseEventType;
import com.github.bordertech.flux.wc.app.view.event.util.FormEventUtil;
import com.github.bordertech.flux.wc.app.view.smart.CrudSmartView;
import com.github.bordertech.flux.wc.app.view.smart.msg.DefaultMessageSmartView;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.lib.polling.PollingStatus;
import java.util.List;
import com.github.bordertech.flux.app.actioncreator.EntityActionCreator;

/**
 * Default CRUD view.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the entity type
 */
public class DefaultCrudView<S, T> extends DefaultMessageSmartView<T> implements CrudSmartView<S, T> {

	private final SearchView<S> searchView;
	private final SelectSingleView<T> selectView;
	private final PollingView pollingView = new DefaultPollingView<>("vw-poll");
	private final ToolbarView searchToolbar = new DefaultToolbarView("vw-toolbar-1");
	private final MessageView searchMessages = new DefaultMessageView("vw-crit-msg");
	// Form Details
	private final DefaultSmartView formHolder = new DefaultSmartView("vw-form", "wclib/hbs/layout/combo-ent-crud-form.hbs");
	private final MessageView formMessages = new DefaultMessageView("vw-form-msg");
	private final FormToolbarView<T> formToolbarView = new DefaultFormToolbarView("vw-form-toolbar");
	private final FormView<T> formView;

	public DefaultCrudView(final String viewId, final String title, final WComponent panel) {
		this(viewId, title, null, null, null, panel);
	}

	public DefaultCrudView(final String viewId, final String title, final WComponent panel, final SelectSingleView<T> selectView2) {
		this(viewId, title, null, selectView2, null, panel);
	}

	public DefaultCrudView(final String viewId, final String title, final SearchView<S> criteriaView2, final SelectSingleView<T> selectView2, final FormView<T> formView2, final WComponent panel) {
		super(viewId, "wclib/hbs/layout/combo-ent-crud.hbs");

		// Setup Defaults
		searchView = criteriaView2 == null ? (SearchView<S>) new SearchTextView("vw-crit") : criteriaView2;
		selectView = selectView2 == null ? (SelectSingleView) new MenuSelectView("vw-list") : selectView2;
		formView = formView2 == null ? new DefaultFormView<T>("vw-form") : formView2;
		if (panel != null) {
			formView.getFormHolder().add(panel);
		}

		// Form Holder
		formHolder.addHtmlClass("wc-panel-type-box");
		formHolder.addComponentToTemplate("vw-form-toolbar", formToolbarView);
		formHolder.addComponentToTemplate("vw-form-msg", formMessages);
		formHolder.addComponentToTemplate("vw-form-view", formView);

		// Add views
		addComponentToTemplate("vw-toolbar-1", searchToolbar);
		addComponentToTemplate("vw-crit-msg", searchMessages);
		addComponentToTemplate("vw-crit", searchView);
		addComponentToTemplate("vw-poll", pollingView);
		addComponentToTemplate("vw-list", selectView);
		addComponentToTemplate("vw-form", formHolder);

		// Title
		getContent().addParameter("vw-title", title);

		// Toolbar Defaults
		searchToolbar.addToolbarItem(ToolbarModifyItemType.ADD);

		// Default visibility
		selectView.setContentVisible(false);
		formHolder.setContentVisible(false);

		setQualifierContext(true);
	}

	@Override
	public void handleViewEvent(final String viewId, final ViewEventType event, final Object data) {
		super.handleViewEvent(viewId, event, data);

		// Handle the Form and Form Toolbar Events
		if (isView(viewId, formView) || isView(viewId, formToolbarView)) {
			FormEventUtil.handleFormEvents(this, viewId, event, data);
			if (event instanceof FormBaseOutcomeEventType) {
				handleFormOutcomeEvents((FormBaseOutcomeEventType) event, (T) data);
			}
			return;
		}

		// Search Validating
		if (isEvent(SearchBaseEventType.SEARCH_VALIDATING, event)) {
			selectView.reset();
			pollingView.reset();

			// Search
		} else if (isEvent(SearchBaseEventType.SEARCH, event)) {
			selectView.reset();
			// Do ASYNC Search Action
			FluxUtil.dispatchSearchAction(getSearchStoreKey(), getCriteria(), CallType.ASYNC_OK);
			// Start Polling
			pollingView.reset();
			pollingView.doStartPolling();

			// POLLING
		} else if (isEvent(PollingBaseEventType.CHECK_STATUS, event)) {
			// Check if action is done
			boolean done = FluxUtil.isSearchActionDone(getEntityStoreKey(), getCriteria());
			if (done) {
				// Stop polling
				pollingView.setPollingStatus(PollingStatus.STOPPED);
				// Handle the result
				try {
					List<T> result = FluxUtil.getSearchActionResult(getEntityStoreKey(), getCriteria());
					selectView.setItems(result);
					selectView.setContentVisible(true);
				} catch (Exception e) {
					dispatchMessageError("Error loading details. " + e.getMessage());
				}
			}

			// SELECT
		} else if (isEvent(SelectBaseEventType.SELECT, event)) {
			formHolder.reset();
			formHolder.setContentVisible(true);
			dispatchViewEvent(FormBaseEventType.LOAD, (T) data);
		}
	}

	/**
	 * Extra config for the FORM Outcome events.
	 *
	 * @param type
	 * @param entity
	 */
	protected void handleFormOutcomeEvents(final FormBaseOutcomeEventType type, final T entity) {
		switch (type) {
			case ADD_OK:
				dispatchMessageReset();
				selectView.clearSelected();
				break;

			case CREATE_OK:
				selectView.addItem(entity);
				selectView.setContentVisible(true);
				selectView.setSelectedItem(entity);
				break;

			case UPDATE_OK:
			case REFRESH_OK:
				selectView.updateItem(entity);
				break;

			case DELETE_OK:
				selectView.removeItem(entity);
				if (selectView.getViewBean().isEmpty()) {
					selectView.setContentVisible(false);
				}
				formHolder.setContentVisible(false);
				break;
		}
	}

	@Override
	public String getEntityActionCreatorKey() {
		return getComponentModel().entityCreatorKey;
	}

	@Override
	public void setEntityActionCreatorKey(final String entityCreatorKey) {
		getOrCreateComponentModel().entityCreatorKey = entityCreatorKey;
	}

	@Override
	public EntityActionCreator<T> getEntityActionCreator() {
		return FluxUtil.getActionCreator(getEntityActionCreatorKey());
	}

	@Override
	public String getEntityStoreKey() {
		return getComponentModel().entityStoreKey;
	}

	@Override
	public void setEntityStoreKey(final String entityStoreKey) {
		getOrCreateComponentModel().entityStoreKey = entityStoreKey;
	}

	@Override
	public EntityStore<T> getEntityStore() {
		return FluxUtil.getStore(getEntityStoreKey());
	}

	@Override
	public void setSearchStoreKey(final String searchStoreKey) {
		getOrCreateComponentModel().searchStoreKey = searchStoreKey;
	}

	@Override
	public String getSearchStoreKey() {
		return getComponentModel().searchStoreKey;
	}

	@Override
	public SearchStore<S, T> getSearchStore() {
		return FluxUtil.getStore(getSearchStoreKey());
	}

	@Override
	public FormView<T> getFormView() {
		return formView;
	}

	@Override
	public FormToolbarView<T> getFormToolbarView() {
		return formToolbarView;
	}

	@Override
	public void resetFormViews() {
		formHolder.reset();
	}

	protected S getCriteria() {
		return searchView.getViewBean();
	}

	protected SearchView<S> getSearchView() {
		return searchView;
	}

	protected PollingView getPollingView() {
		return pollingView;
	}

	protected ToolbarView getSearchToolbar() {
		return searchToolbar;
	}

	protected MessageView getSearchMessages() {
		return searchMessages;
	}

	protected DefaultSmartView getFormHolder() {
		return formHolder;
	}

	protected MessageView getFormMessages() {
		return formMessages;
	}

	@Override
	protected CrudFormModel newComponentModel() {
		return new CrudFormModel();
	}

	@Override
	protected CrudFormModel getComponentModel() {
		return (CrudFormModel) super.getComponentModel();
	}

	@Override
	protected CrudFormModel getOrCreateComponentModel() {
		return (CrudFormModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class CrudFormModel extends SmartViewModel {

		private String searchStoreKey;

		private String entityStoreKey;

		private String entityCreatorKey;
	}

}
