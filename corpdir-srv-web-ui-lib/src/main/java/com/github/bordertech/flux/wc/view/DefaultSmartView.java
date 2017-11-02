package com.github.bordertech.flux.wc.view;

import com.github.bordertech.flux.Dispatcher;
import com.github.bordertech.flux.Event;
import com.github.bordertech.flux.EventKey;
import com.github.bordertech.flux.event.StoreEventType;
import com.github.bordertech.flux.event.ViewEventType;
import com.github.bordertech.flux.impl.DefaultEvent;
import com.github.bordertech.flux.impl.DispatcherUtil;
import com.github.bordertech.flux.wc.DispatcherFactory;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WebUtilities;
import com.github.bordertech.wcomponents.lib.app.view.event.ToolbarViewEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	public void handleViewEvent(final ViewEventType event, final Object data) {
		if (event == ToolbarViewEvent.RESET) {
			handleEventReset();
		}
	}

	protected void handleEventReset() {
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
	protected void dispatchEvent(final StoreEventType eventType) {
		dispatchEvent(eventType, null);
	}

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param eventType the event type
	 * @param data the event data
	 */
	protected void dispatchEvent(final StoreEventType eventType, final Object data) {
		String qualifier = getFullQualifier();
		DefaultEvent event = new DefaultEvent(new EventKey(eventType, qualifier), data);
		dispatchEvent(event);
	}

	protected void dispatchEvent(final Event event) {
		getDispatcher().dispatch(event);
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
	protected ViewContainerModel newComponentModel() {
		return new ViewContainerModel();
	}

	@Override
	protected ViewContainerModel getComponentModel() {
		return (ViewContainerModel) super.getComponentModel();
	}

	@Override
	protected ViewContainerModel getOrCreateComponentModel() {
		return (ViewContainerModel) super.getOrCreateComponentModel();
	}

	/**
	 * Just here as a place holder and easier for other Views to extend.
	 */
	public static class ViewContainerModel extends ViewModel {

		private String qualifier;

		private boolean qualifierContext;

		private boolean ajaxContext;

	}
}
