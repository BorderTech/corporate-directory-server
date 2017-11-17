package com.github.bordertech.flux.store;

import com.github.bordertech.flux.Dispatcher;
import com.github.bordertech.flux.Listener;
import com.github.bordertech.flux.Store;
import com.github.bordertech.flux.dispatcher.DefaultEvent;
import com.github.bordertech.flux.dispatcher.DispatcherFactory;
import com.github.bordertech.flux.event.base.StateBaseEventType;
import com.github.bordertech.flux.key.EventKey;
import com.github.bordertech.flux.key.EventType;
import com.github.bordertech.flux.key.StoreKey;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultStore implements Store {

	private final StoreKey storeKey;
	private final Set<String> registeredIds = new HashSet<>();

	public DefaultStore(final StoreKey storeKey) {
		this.storeKey = storeKey;
	}

	@Override
	public StoreKey getKey() {
		return storeKey;
	}

	@Override
	public void registerListeners() {
		// Do nothing
	}

	@Override
	public void unregisterListeners() {
		Dispatcher dispatcher = getDispatcher();
		for (String id : registeredIds) {
			dispatcher.unregisterListener(id);
		}
	}

	@Override
	public void dispatchChangeEvent(final EventType eventType) {
		DefaultEvent event = new DefaultEvent(new EventKey(StateBaseEventType.STORE_CHANGED, storeKey.getQualifier()), eventType);
		getDispatcher().dispatch(event);
	}

	@Override
	public final Dispatcher getDispatcher() {
		return DispatcherFactory.getInstance();
	}

	/**
	 * A helper method to register a listener with an Event Type and the Controller qualifier automatically added.
	 *
	 * @param listener
	 * @param eventType
	 */
	protected void registerListener(final EventType eventType, final Listener listener) {
		String id = getDispatcher().registerListener(new EventKey(eventType, storeKey.getQualifier()), listener);
		registeredIds.add(id);
	}

}
