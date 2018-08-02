package com.github.bordertech.corpdir.web.ui.flux.view.impl;

import com.github.bordertech.corpdir.api.common.ApiIdObject;
import com.github.bordertech.corpdir.sync.service.SynchronisationService;
import com.github.bordertech.corpdir.web.ui.CardType;
import com.github.bordertech.corpdir.web.ui.common.EntityLink;
import com.github.bordertech.didums.Didums;
import com.github.bordertech.flux.view.ViewEventType;
import com.github.bordertech.flux.wc.common.TemplateConstants;
import com.github.bordertech.flux.wc.view.DefaultSmartView;
import com.github.bordertech.flux.wc.view.dumb.toolbar.DefaultToolbarView;
import com.github.bordertech.flux.wc.view.dumb.toolbar.ToolbarModifyItemType;
import com.github.bordertech.flux.wc.view.dumb.toolbar.ToolbarNavigationItemType;
import com.github.bordertech.flux.wc.view.event.base.ToolbarBaseEventType;
import com.github.bordertech.taskmaster.service.ServiceAction;
import com.github.bordertech.wcomponents.WLabel;
import com.github.bordertech.wcomponents.addons.common.WDiv;
import com.github.bordertech.wcomponents.addons.polling.PollingServicePanel;
import com.github.bordertech.wcomponents.addons.polling.PollingStartType;
import java.io.Serializable;

/**
 * Import smart view.
 * 
 * @author aswinkandula
 * @param <T>
 */
public class ImportSmartView<T extends Serializable> extends DefaultSmartView {

	private final DefaultToolbarView toolbar;
	private final EntityLink versionLink;
	
	private final PollingServicePanel panel = new PollingServicePanel<T, ApiIdObject>() {

		@Override
		protected void handleStoppedPolling() {
			super.handleStoppedPolling();
			toolbar.getItemImport().setDisabled(false);
		}

		@Override
		protected void handleResultSuccessful(ApiIdObject result) {
			super.handleResultSuccessful(result);
			versionLink.setBean(result);
		}

	};

	public ImportSmartView(String viewId) {

		super(viewId, "wclib/hbs/layout/import-view.hbs");

		// Toolbar Defaults
		toolbar = new DefaultToolbarView("vw_toolbar_1");
		toolbar.addToolbarItem(ToolbarModifyItemType.IMPORT);
		toolbar.removeToolbarItem(ToolbarNavigationItemType.RESET);

		// Result
		WLabel resultLbl = new WLabel("Import successful, version ");
		versionLink = new EntityLink(CardType.VERSION_CTRL);
		WDiv resultDiv = new WDiv();
		resultDiv.add(resultLbl);
		resultDiv.add(versionLink);

		// Polling Panel
		ServiceAction action = new ServiceAction<T, ApiIdObject> () {
			@Override
			public ApiIdObject service(T criteria) {
				SynchronisationService service = Didums.getService(SynchronisationService.class);
				return service.sync(criteria);
			}
		};
		panel.setServiceAction(action);
		panel.setStartType(PollingStartType.MANUAL);
		panel.setUseCachedResult(false);
		panel.getContentResultHolder().add(resultDiv);

		// Add (dumb) views
		addComponentToTemplate(TemplateConstants.TAG_VW_TOOLBAR_TOP, toolbar);
		addComponentToTemplate("import-poll", panel);

	}

	@Override
	protected void handleViewEvent(final String viewId, final ViewEventType event, final Object data) {
		super.handleViewEvent(viewId, event, data);

		if (isEvent(ToolbarBaseEventType.IMPORT, event)) {
			// If previously fetched, then remove the  resultholder
			panel.getContentResultHolder().setVisible(false);
			// start service
			panel.doManualStart();
			// disable button while it is running
			toolbar.getItemImport().setDisabled(true);
		}
	}

}
