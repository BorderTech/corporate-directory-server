package com.github.bordertech.flux.wc.crud.smart.impl;

import com.github.bordertech.flux.crud.actioncreator.CrudTreeActionCreator;
import com.github.bordertech.flux.crud.store.CrudTreeStore;
import com.github.bordertech.flux.wc.crud.smart.CrudSearchTreeSmartView;
import com.github.bordertech.flux.wc.view.event.base.FormBaseEventType;
import com.github.bordertech.flux.wc.view.event.base.FormBaseOutcomeEventType;
import com.github.bordertech.flux.wc.view.smart.ListOrTreeSelectView;
import com.github.bordertech.flux.wc.view.smart.tree.DefaultListOrTreeSmartView;
import com.github.bordertech.taskmaster.service.CallType;
import com.github.bordertech.taskmaster.service.ResultHolder;
import com.github.bordertech.wcomponents.WComponent;
import java.util.List;

/**
 * Default CRUD Tree view.
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <K> the entity key type
 * @param <T> the entity type
 */
public class DefaultCrudTreeSmartView<S, K, T> extends DefaultCrudSmartView<S, K, T> implements CrudSearchTreeSmartView<S, K, T> {

	public DefaultCrudTreeSmartView(final String viewId, final String title, final WComponent panel) {
		super(viewId, title, panel);
		// Setup Tree View and Defaults
		DefaultListOrTreeSmartView selectView = new DefaultListOrTreeSmartView("vw_list");
		selectView.setContentVisible(false);
		selectView.setDumbMode(true);
		selectView.setPassAllEvents(true);
		setSelectView(selectView);
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
	protected ResultHolder<S, List<T>> doSearchAction(final CallType callType) {
		if (getCriteria() == null) {
			return getStoreByKey().getRootItems(callType);
		} else {
			return super.doSearchAction(callType);
		}
	}

	@Override
	protected void handleSearchSuccessful(final List<T> items) {
		if (getCriteria() == null) {
			getSelectView().resetView();
			getSelectView().setUseTree(true);
			getSelectView().setEntityTreeStoreKey(getStoreKey());
		}
		super.handleSearchSuccessful(items);
	}

	@Override
	protected void handleFormOutcomeEvents(final FormBaseOutcomeEventType type, final T entity) {
		if (getSelectView().isUseTree()) {
			switch (type) {
				case CREATE_OK:
				case DELETE_OK:
				case UPDATE_OK:
					// Refresh Tree
					getSelectView().resetView();
					getSelectView().setUseTree(true);
					getSelectView().setEntityTreeStoreKey(getStoreKey());
					getSelectView().setContentVisible(true);

					// Get Root Items (SYNC) - SYNC should always have a result
					ResultHolder<S, List<T>> resultHolder = getStoreByKey().getRootItems(CallType.CALL_SYNC);
					if (resultHolder == null) {
						dispatchMessageError("Error refreshing items as no result returned. ");
						return;
					}
					if (resultHolder.isException()) {
						dispatchMessageError("Error refreshing items. " + resultHolder.getException().getMessage());
						return;
					}

					List<T> items = resultHolder.getResult();
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
