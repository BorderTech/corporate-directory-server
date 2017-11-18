package com.github.bordertech.flux.view;

import com.github.bordertech.flux.Action;
import com.github.bordertech.flux.Dispatcher;
import com.github.bordertech.flux.Listener;
import com.github.bordertech.flux.action.DefaultAction;
import com.github.bordertech.flux.action.StoreActionType;
import com.github.bordertech.flux.action.base.StateBaseActionType;
import com.github.bordertech.flux.app.action.RetrieveAction;
import com.github.bordertech.flux.app.action.RetrieveActionType;
import com.github.bordertech.flux.app.action.RetrieveCallType;
import com.github.bordertech.flux.dispatcher.DispatcherFactory;
import com.github.bordertech.flux.dispatcher.DispatcherUtil;
import com.github.bordertech.flux.key.ActionKey;
import com.github.bordertech.flux.key.ActionType;
import com.github.bordertech.flux.wc.app.view.event.base.ToolbarBaseViewEvent;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WebUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Smart view.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultSmartView<T> extends DefaultDumbTemplateView<T> implements SmartView<T> {

	public DefaultSmartView(final String viewId, final String templateName) {
		super(viewId, templateName);
		setNamingContext(true);
	}

	@Override
	public void configAjax(final DumbView view) {
		// By default ADD all the views as AJAX
		for (DumbView vw : getViews()) {
			if (vw != view) {
				((DumbView) view).addEventAjaxTarget(vw);
			}
		}
		if (isAjaxContext()) {
			return;
		}
		// Get the parent controller AJAX Targets
		SmartView parent = findParentViewContainer();
		if (parent != null) {
			parent.configAjax(view);
		}
	}

	@Override
	public boolean isAjaxContext() {
		return getComponentModel().ajaxContext;
	}

	@Override
	public void setAjaxContext(final boolean ajaxContext) {
		getOrCreateComponentModel().ajaxContext = ajaxContext;
	}

	@Override
	public void handleViewEvent(final String viewId, final ViewEventType event, final Object data) {
		if (isEvent(event, ToolbarBaseViewEvent.RESET)) {
			handleResetEvent(viewId);
		}
	}

	protected void handleResetEvent(final String viewId) {
		resetView();
	}

	@Override
	public List<DumbView> getViews() {
		List<DumbView> views = new ArrayList<>();
		for (WComponent child : getContent().getTaggedComponents().values()) {
			if (child instanceof DumbView) {
				views.add((DumbView) child);
			}
		}
		return Collections.unmodifiableList(views);
	}

	@Override
	public DumbView getView(final String viewId) {
		for (WComponent child : getContent().getTaggedComponents().values()) {
			if (child instanceof DumbView) {
				DumbView view = (DumbView) child;
				if (Objects.equals(viewId, view.getViewId())) {
					return view;
				}
			}
		}
		return null;
	}

	@Override
	public boolean isQualifierContext() {
		return getComponentModel().qualifierContext;
	}

	@Override
	public void setQualifierContext(final boolean qualifierContext) {
		getOrCreateComponentModel().qualifierContext = qualifierContext;
	}

	@Override
	public final String getQualifier() {
		return getComponentModel().qualifier;
	}

	@Override
	public final void setQualifier(final String qualifier) {
		DispatcherUtil.validateQualifier(qualifier);
		getOrCreateComponentModel().qualifier = qualifier;
	}

	@Override
	public boolean isDumbMode() {
		return getComponentModel().dumbMode;
	}

	@Override
	public void setDumbMode(final boolean dumbMode) {
		getOrCreateComponentModel().dumbMode = dumbMode;
	}

	@Override
	public void addPassThrough(final ViewEventType type) {
		SmartViewModel model = getOrCreateComponentModel();
		if (model.passThroughs == null) {
			model.passThroughs = new HashSet();
		}
		model.passThroughs.add(type);
	}

	@Override
	public void removePassThrough(final ViewEventType type) {
		SmartViewModel model = getOrCreateComponentModel();
		if (model.passThroughs != null) {
			model.passThroughs.remove(type);
			if (model.passThroughs.isEmpty()) {
				model.passThroughs = null;
			}
		}
	}

	@Override
	public Set<ViewEventType> getPassThroughs() {
		SmartViewModel model = getComponentModel();
		return model.passThroughs == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(model.passThroughs);
	}

	@Override
	public String getFullQualifier() {
		// As determining the qualifier involves a fair bit of tree traversal, it is cached in the scratch map.
		// Try to retrieve the cached name first.
		Map scratchMap = getScratchMap();
		if (scratchMap != null) {
			String name = (String) scratchMap.get("vw-qualifier");
			if (name != null) {
				return name;
			}
		}

		// Get qualifier
		String qualifier = getQualifier();
		// Derive its full context name
		String name = deriveFullQualifier(qualifier);
		if (scratchMap != null) {
			scratchMap.put("vw-qualifier", name);
		}

		return name;
	}

	/**
	 * Derive the full qualifier from its context.
	 *
	 * @param qualifier the component id name
	 * @return the derived id in its context
	 */
	protected String deriveFullQualifier(final String qualifier) {
		// Find parent naming context
		SmartView parent = getParentQualifierContext(this);

		// No Parent
		if (parent == null) {
			return qualifier;
		}

		// Get prefix
		String prefix = parent.getFullQualifier();

		// No Prefix, just use qualifier
		if (prefix.length() == 0) {
			return qualifier;
		}

		// No qualifier, just use prefix
		if (qualifier == null || qualifier.isEmpty()) {
			return prefix;
		}

		// Add Prefix
		StringBuilder nameBuf = new StringBuilder(prefix.length() + qualifier.length() + 1);
		nameBuf.append(prefix);
		nameBuf.append(QUALIFIER_CONTEXT_SEPERATOR);
		nameBuf.append(qualifier);

		return nameBuf.toString();
	}

	protected final Dispatcher getDispatcher() {
		return DispatcherFactory.getInstance();
	}

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param eventType the event type
	 */
	protected void dispatchEvent(final StoreActionType eventType) {
		dispatchEvent(eventType, null);
	}

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param eventType the event type
	 * @param data the event data
	 */
	protected void dispatchEvent(final StoreActionType eventType, final Object data) {
		String qualifier = getFullQualifier();
		DefaultAction event = new DefaultAction(new ActionKey(eventType, qualifier), data);
		dispatchEvent(event);
	}

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param storeKey the target store
	 * @param eventType the retrieve event type
	 * @param data the event data
	 * @param action the retrieve action
	 */
	protected void dispatchRetrieveEvent(final String storeKey, final RetrieveActionType eventType, final Object data, final RetrieveCallType action) {
		DefaultAction event = new RetrieveAction(eventType, storeKey, data, action);
		dispatchEvent(event);
	}

	protected void dispatchEvent(final Action event) {
		getDispatcher().dispatch(event);
	}

	protected void handleStoreChangedEvent(final String storeKey, final Action event) {
	}

	protected void registerStoreChangeListener(final String storeKey) {
		Listener listener = new Listener() {
			@Override
			public void handleAction(final Action event) {
				handleStoreChangedEvent(storeKey, event);
			}
		};
		registerListener(StateBaseActionType.STORE_CHANGED, storeKey, listener);
	}

	protected void registerListener(final ActionType eventType, final String qualifier, final Listener listener) {
		String id = getDispatcher().registerListener(new ActionKey(eventType, qualifier), listener);
		SmartViewModel model = getOrCreateComponentModel();
		if (model.registeredIds == null) {
			model.registeredIds = new HashSet<>();
		}
		model.registeredIds.add(id);
	}

	protected void unregisterListeners() {
		SmartViewModel model = getComponentModel();
		if (model.registeredIds != null) {
			Dispatcher dispatcher = getDispatcher();
			for (String id : model.registeredIds) {
				dispatcher.unregisterListener(id);
			}
			model.registeredIds = null;
		}
	}

	@Override
	public void reset() {
		unregisterListeners();
		super.reset();
	}

	/**
	 * Get this component's parent qualifier context.
	 *
	 * @param component the component to process
	 * @return the parent qualifier context or null
	 */
	public static SmartView getParentQualifierContext(final WComponent component) {
		if (component == null) {
			return null;
		}

		WComponent child = component;
		SmartView parent = null;
		while (true) {
			SmartView naming = WebUtilities.getAncestorOfClass(SmartView.class, child);
			if (naming == null) {
				break;
			}
			// Can only be considered active if a qualifier has been set and flagged as a context.
			if (naming.isQualifierContext() && naming.getQualifier() != null) {
				parent = naming;
				break;
			}
			child = (WComponent) naming;
		}
		return parent;
	}

	@Override
	protected SmartViewModel newComponentModel() {
		return new SmartViewModel();
	}

	@Override
	protected SmartViewModel getComponentModel() {
		return (SmartViewModel) super.getComponentModel();
	}

	@Override
	protected SmartViewModel getOrCreateComponentModel() {
		return (SmartViewModel) super.getOrCreateComponentModel();
	}

	/**
	 * Just here as a place holder and easier for other Views to extend.
	 */
	public static class SmartViewModel extends ViewModel {

		private String qualifier;

		private boolean qualifierContext;

		private boolean ajaxContext;

		private Set<String> registeredIds;

		private boolean dumbMode;

		private Set<ViewEventType> passThroughs;

	}
}
