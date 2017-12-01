package com.github.bordertech.flux.store.collection;

import com.github.bordertech.flux.Action;
import com.github.bordertech.flux.Listener;
import com.github.bordertech.flux.action.StoreActionType;
import com.github.bordertech.flux.action.base.ListBaseActionType;
import com.github.bordertech.flux.key.ActionKey;
import com.github.bordertech.flux.store.DefaultStore;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * List store.
 *
 * @param <T> the item type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public abstract class AbstractListStore<T> extends DefaultStore implements ListStore<T> {

	public AbstractListStore(final String storeKey) {
		super(storeKey);
	}

	@Override
	public void registerListeners(final Set<String> ids) {
		super.registerListeners(ids);
		// LIST Listeners
		for (ListBaseActionType type : ListBaseActionType.values()) {
			Listener listener = new Listener() {
				@Override
				public void handleAction(final Action action) {
					handleListActions(action);
				}
			};
			String id = registerListener(type, listener);
			ids.add(id);
		}
	}

	protected void handleListActions(final Action action) {
		ListBaseActionType type = (ListBaseActionType) action.getKey().getType();
		boolean handled = true;
		switch (type) {
			case RESET_ITEMS:
				handleResetItemsAction();
				break;
			case LOAD_ITEMS:
				handleLoadItemsAction(action.getData());
				break;
			case ADD_ITEM:
				handleAddItemAction((T) action.getData());
				break;
			case REMOVE_ITEM:
				handleRemoveItemAction((T) action.getData());
				break;
			case UPDATE_ITEM:
				handleUpdateItemAction((T) action.getData());
				break;

			default:
				handled = false;
		}
		if (handled) {
			dispatchChangeAction(type);
		}

	}

	protected void handleResetItemsAction() {
		getItems().clear();
	}

	protected void handleAddItemAction(final T item) {
		getItems().add(item);
	}

	protected void handleRemoveItemAction(final T item) {
		getItems().remove(item);
	}

	protected void handleUpdateItemAction(final T item) {
		int idx = getItems().indexOf(item);
		if (idx > -1) {
			getItems().remove(item);
			getItems().add(idx, item);
		} else {
			getItems().add(item);
		}
	}

	protected void handleLoadItemsAction(final Object data) {
		getItems().clear();
		if (data instanceof Collection) {
			getItems().addAll((Collection) data);
		} else if (data instanceof Object[]) {
			getItems().addAll(Arrays.asList((T[]) data));
		}
	}

	/**
	 * A helper method to register a listener with an Action Type and the Controller qualifier automatically added.
	 *
	 * @param listener the listener to register
	 * @param actionType the action type
	 * @return the listener id
	 */
	protected String registerListener(final StoreActionType actionType, final Listener listener) {
		return getDispatcher().registerListener(new ActionKey(actionType, getKey()), listener);
	}

}
