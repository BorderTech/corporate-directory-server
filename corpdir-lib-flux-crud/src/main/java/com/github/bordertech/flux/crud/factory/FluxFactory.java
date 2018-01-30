package com.github.bordertech.flux.crud.factory;

import com.github.bordertech.didums.Didums;
import com.github.bordertech.didums.Factory;
import com.github.bordertech.flux.ActionCreator;
import com.github.bordertech.flux.DataApi;
import com.github.bordertech.flux.Dispatcher;
import com.github.bordertech.flux.Store;
import com.github.bordertech.flux.crud.actioncreator.DefaultCrudActionCreator;
import com.github.bordertech.flux.crud.actioncreator.DefaultCrudTreeActionCreator;
import com.github.bordertech.flux.crud.dataapi.CrudApi;
import com.github.bordertech.flux.crud.dataapi.CrudTreeApi;
import com.github.bordertech.flux.dataapi.SearchApi;
import com.github.bordertech.flux.crud.store.impl.DefaultCrudStore;
import com.github.bordertech.flux.crud.store.impl.DefaultCrudTreeStore;
import com.github.bordertech.flux.store.datapi.DefaultSearchStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public final class FluxFactory {

	private static final Map<String, DataApi> DATA_APIS = new HashMap<>();

	private static final Dispatcher DISPATCHER = Didums.getService(Dispatcher.class);
	private static final Map<String, ActionCreator> CREATORS = new HashMap<>();

	private FluxFactory() {
	}

	public static Dispatcher getDispatcher() {
		return DISPATCHER;
	}

	public static DataApi getDataApi(final String key) {
		DataApi model = DATA_APIS.get(key);
		if (model != null) {
			return model;
		}
		model = Factory.newInstance("dataapi." + key);
		DATA_APIS.put(key, model);
		return model;
	}

	public static <T extends ActionCreator> T getActionCreator(final String key, final String apiKey) {
		ActionCreator creator = CREATORS.get(key);
		if (creator != null) {
			return (T) creator;
		}

		try {
			DataApi api = getDataApi(apiKey);
			if (api instanceof CrudTreeApi) {
				creator = new DefaultCrudTreeActionCreator<>(key, (CrudTreeApi) api);
			} else {
				creator = new DefaultCrudActionCreator<>(key, (CrudApi) api);
			}
		} catch (Exception e) {
			throw new IllegalStateException("Could not create action creator [" + key + "]." + e.getMessage(), e);
		}
		CREATORS.put(key, creator);
		return (T) creator;
	}

	public static <T extends Store> T getStore(final String storeKey, final Set<String> actionCreatorKeys, final String apiKey) {
		try {
			DataApi api = getDataApi(apiKey);
			if (api instanceof CrudTreeApi && storeKey.startsWith("e-")) {
				return (T) new DefaultCrudTreeStore(storeKey, actionCreatorKeys, (CrudTreeApi) api);
			} else if (api instanceof CrudApi && storeKey.startsWith("e-")) {
				return (T) new DefaultCrudStore(storeKey, actionCreatorKeys, (CrudApi) api);
			} else if (api instanceof SearchApi && storeKey.startsWith("s-")) {
				return (T) new DefaultSearchStore(storeKey, actionCreatorKeys, (SearchApi) api);
			} else {
				throw new IllegalStateException("Default Stores do not support Data API [" + storeKey + "].");
			}
		} catch (Exception e) {
			throw new IllegalStateException("Could not create store [" + storeKey + "]." + e.getMessage(), e);
		}
	}

}
