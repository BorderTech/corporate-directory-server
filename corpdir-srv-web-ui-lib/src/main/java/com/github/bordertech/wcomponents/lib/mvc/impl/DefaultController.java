package com.github.bordertech.wcomponents.lib.mvc.impl;

import com.github.bordertech.wcomponents.lib.flux.EventType;
import com.github.bordertech.wcomponents.lib.flux.Listener;
import com.github.bordertech.wcomponents.lib.flux.Matcher;
import com.github.bordertech.wcomponents.lib.flux.impl.EventMatcher;
import com.github.bordertech.wcomponents.lib.model.Model;
import com.github.bordertech.wcomponents.lib.mvc.ComboView;
import com.github.bordertech.wcomponents.lib.mvc.Controller;
import com.github.bordertech.wcomponents.lib.mvc.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultController extends AbstractBaseView implements Controller {

	public DefaultController() {
		super(null);
	}

	@Override
	public void checkConfig() {
	}

	protected Model getModel(final String key) {
		// Get Parent Combo
		ComboView combo = findParentCombo();
		return combo == null ? null : combo.getModel(key);
	}

	@Override
	public void reset() {
		for (View view : getViews()) {
			view.reset();
		}
		super.reset();
	}

	@Override
	public List<View> getViews() {
		CtrlModel model = getComponentModel();
		return model.views == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(model.views);
	}

	@Override
	public void addView(final View view) {
		CtrlModel model = getOrCreateComponentModel();
		if (model.views == null) {
			model.views = new ArrayList<>();
		}
		model.views.add(view);
	}

	@Override
	public void resetViews() {
		for (View view : getViews()) {
			view.resetView();
		}
	}

	@Override
	public void setupListeners() {
	}

	/**
	 * A helper method to register a listener with an Event Type and the Controller qualifier automatically added.
	 *
	 * @param listener
	 * @param eventType
	 */
	protected final void registerListener(final Listener listener, final EventType eventType) {
		String qualifier = getListenerQualifier(eventType);
		registerListener(listener, new EventMatcher(eventType, qualifier));
	}

	protected void registerListener(final Listener listener, final Matcher matcher) {
		String id = getDispatcher().register(listener, matcher);
		findParentCombo().registerListenerId(id);
	}

	protected String getListenerQualifier(final EventType type) {
		String qualifier = getListenerOverride(type);
		return qualifier == null ? getFullQualifier() : qualifier;
	}

	@Override
	public void addListenerOverride(final String qualifier, final EventType... types) {
		CtrlModel model = getOrCreateComponentModel();
		if (model.listenerOverride == null) {
			model.listenerOverride = new HashMap<>();
		}
		for (EventType type : types) {
			model.listenerOverride.put(type, qualifier);
		}
	}

	protected String getListenerOverride(final EventType type) {
		CtrlModel model = getComponentModel();
		if (model.listenerOverride != null) {
			return model.listenerOverride.get(type);
		}
		return null;
	}

	@Override
	protected CtrlModel newComponentModel() {
		return new CtrlModel();
	}

	@Override
	protected CtrlModel getComponentModel() {
		return (CtrlModel) super.getComponentModel();
	}

	@Override
	protected CtrlModel getOrCreateComponentModel() {
		return (CtrlModel) super.getOrCreateComponentModel();
	}

	/**
	 * Just here as a place holder and easier for other Views to extend.
	 */
	public static class CtrlModel extends BaseModel {

		private List<View> views;
		private Map<EventType, String> listenerOverride;

	}

}
