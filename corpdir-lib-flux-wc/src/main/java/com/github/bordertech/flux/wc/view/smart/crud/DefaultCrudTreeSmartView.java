package com.github.bordertech.flux.wc.view.smart.crud;

import com.github.bordertech.flux.crud.actioncreator.CrudTreeActionCreator;
import com.github.bordertech.flux.crud.store.CrudTreeStore;
import com.github.bordertech.flux.wc.view.dumb.FormView;
import com.github.bordertech.flux.wc.view.dumb.SearchView;
import com.github.bordertech.flux.wc.view.event.base.FormBaseEventType;
import com.github.bordertech.flux.wc.view.event.base.FormBaseOutcomeEventType;
import com.github.bordertech.flux.wc.view.smart.CrudSearchTreeSmartView;
import com.github.bordertech.flux.wc.view.smart.tree.DefaultListOrTreeSmartView;
import com.github.bordertech.flux.wc.view.smart.tree.ListOrTreeSelectView;
import com.github.bordertech.taskmanager.service.CallType;
import com.github.bordertech.taskmanager.service.ResultHolder;
import com.github.bordertech.wcomponents.WComponent;
import java.util.List;

/**
 * Default CRUD Tree view.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the entity type
 */
public class DefaultCrudTreeSmartView<S, K, T> extends DefaultCrudSmartView<S, K, T> implements CrudSearchTreeSmartView<S, K, T> {

	public DefaultCrudTreeSmartView(final String viewId, final String title, final WComponent panel) {
		this(viewId, title, panel, new DefaultListOrTreeSmartView<K, T>("vw_list"));
	}

	public DefaultCrudTreeSmartView(final String viewId, final String title, final WComponent panel, final ListOrTreeSelectView<K, T> selectView2) {
		this(viewId, title, null, selectView2, null, panel);
	}

	public DefaultCrudTreeSmartView(final String viewId, final String title, final SearchView<S> criteriaView2, final ListOrTreeSelectView<K, T> selectView2, final FormView<T> formView2, final WComponent panel) {
		super(viewId, title, criteriaView2, selectView2, formView2, panel);
		selectView2.setDumbMode(true);
		selectView2.setPassAllEvents(true);
	}

	@Override
	public CrudTreeActionCreator<T> getActionCreatorByKey() {
		return (CrudTreeActionCreator<T>) super.getActionCreatorByKey();
	}

	@Override
	public CrudTreeStore<S, K, T> getStoreByKey() {
		return (CrudTreeStore<S, K, T>) super.getStoreByKey();
	}

	@Override
	public ListOrTreeSelectView<K, T> getSelectView() {
		return (ListOrTreeSelectView) super.getSelectView();
	}

	@Override
	protected void doDispatchSearchAction() {
		if (getCriteria() == null) {
			// Use Root Items
			getStoreByKey().getRootItems(CallType.REFRESH_ASYNC);
		} else {
			super.doDispatchSearchAction();
		}
	}

	@Override
	protected ResultHolder<S, List<T>> getSearchActionResult() {
		if (getCriteria() == null) {
			return getStoreByKey().getRootItems(CallType.CALL_ASYNC);
		} else {
			return super.getSearchActionResult();
		}
	}

	@Override
	protected void handleSearchResult(final List<T> items) {
		if (getCriteria() == null) {
			getSelectView().resetView();
			getSelectView().setUseTree(true);
			getSelectView().setEntityTreeStoreKey(getStoreKey());
		}
		super.handleSearchResult(items);
	}

	@Override
	protected void handleFormOutcomeEvents(final FormBaseOutcomeEventType type, final T entity) {
		if (getSelectView().isUseTree()) {
			switch (type) {
				case CREATE_OK:
				case DELETE_OK:
				case UPDATE_OK:
					// FIXME Check this works
					// Refresh Tree
					// Get Root Items (SYNC)
					ResultHolder<S, List<T>> resultHolder = getStoreByKey().getRootItems(CallType.CALL_ASYNC);
					getSelectView().resetView();
					if (resultHolder.isException()) {
						dispatchMessageError("Error refreshing items. " + resultHolder.getException().getMessage());
						return;
					}

					List<T> items = resultHolder.getResult();
					getSelectView().setUseTree(true);
					getSelectView().setEntityTreeStoreKey(getStoreKey());
					getSelectView().setContentVisible(true);
					getSelectView().setItems(items);
					if (type != FormBaseOutcomeEventType.DELETE_OK) {
						getSelectView().setSelectedItem(entity);
						dispatchViewEvent(FormBaseEventType.LOAD, entity);
					}
					return;
			}
		}
		super.handleFormOutcomeEvents(type, entity);
	}

}
