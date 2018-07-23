package com.github.bordertech.corpdir.web.ui.flux.view.impl;

import com.github.bordertech.corpdir.sync.ImportServiceAction;
import com.github.bordertech.flux.view.ViewEventType;
import com.github.bordertech.flux.wc.common.TemplateConstants;
import com.github.bordertech.flux.wc.view.DefaultSmartView;
import com.github.bordertech.flux.wc.view.dumb.toolbar.DefaultToolbarView;
import com.github.bordertech.flux.wc.view.dumb.toolbar.ToolbarModifyItemType;
import com.github.bordertech.flux.wc.view.dumb.toolbar.ToolbarNavigationItemType;
import com.github.bordertech.flux.wc.view.event.base.ToolbarBaseEventType;
import com.github.bordertech.wcomponents.WLabel;
import com.github.bordertech.wcomponents.addons.polling.PollingServicePanel;
import com.github.bordertech.wcomponents.addons.polling.PollingStartType;

/**
 *
 * @author exiqaj
 * @param <T>
 */
public class ImportSmartView<T> extends DefaultSmartView<T> {

    private final DefaultToolbarView toolbar = new DefaultToolbarView("vw_toolbar_1");
    private final PollingServicePanel panel = new PollingServicePanel() {
	@Override
	protected void handleStoppedPolling() {
	    super.handleStoppedPolling();
	    toolbar.getItemImport().setDisabled(false);
	}

    };

    public ImportSmartView(String viewId) {

	super(viewId, "wclib/hbs/layout/import-view.hbs");

	// Toolbar Defaults
	toolbar.addToolbarItem(ToolbarModifyItemType.IMPORT);
	toolbar.removeToolbarItem(ToolbarNavigationItemType.RESET);

	// Polling Panel
	panel.setServiceAction(new ImportServiceAction());
	panel.setStartType(PollingStartType.MANUAL);
	panel.setServiceCacheKey("import");
	panel.getContentResultHolder().add(new WLabel("Imported successfully"));

	// Add (dumb) views
	addComponentToTemplate(TemplateConstants.TAG_VW_TOOLBAR_TOP, toolbar);
	addComponentToTemplate("import-poll", panel);

    }

    @Override
    protected void handleViewEvent(final String viewId, final ViewEventType event, final Object data) {
	super.handleViewEvent(viewId, event, data);

	if (isEvent(ToolbarBaseEventType.IMPORT, event)) {

	    panel.doManualStart();
	    toolbar.getItemImport().setDisabled(true);
	}
    }

}
