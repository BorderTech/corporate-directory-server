package com.github.bordertech.corpdir.web.ui.smart.main;

import com.github.bordertech.corpdir.web.ui.dataapi.impl.DataApiType;
import com.github.bordertech.corpdir.web.ui.event.CardEventType;
import com.github.bordertech.flux.ActionCreator;
import com.github.bordertech.flux.Dispatcher;
import com.github.bordertech.flux.Store;
import com.github.bordertech.flux.factory.FluxFactory;
import com.github.bordertech.flux.view.ViewEventType;
import com.github.bordertech.flux.wc.app.view.smart.msg.DefaultMessageSmartView;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WTemplate;

/**
 * Main Application View.
 *
 * @author jonathan
 */
public class MainAppView extends DefaultMessageSmartView {

	private final MainCardManagerView cardView = new MainCardManagerView("MNC");
	private final MainToolbarView toolbar = new MainToolbarView("MNT");

	public MainAppView() {
		super("MNV", "wclib/hbs/layout/combo-app-main.hbs");
		cardView.addHtmlClass("wc-margin-all-lg");

		WTemplate content = getContent();
		content.addTaggedComponent("vw-toolbar", toolbar);
		content.addTaggedComponent("vw-main", cardView);
	}

	public String getCardTitle() {
		CardType card = cardView.getCurrentType();
		return card == null ? "" : card.getDesc();
	}

	@Override
	protected void initViewContent(Request request) {
		super.initViewContent(request);
		// Register Stores and Action Creators (for this user session)
		Dispatcher dispatcher = getDispatcher();
		for (CardType card : CardType.values()) {
			// Action Creator
			DataApiType api = card.getApiType();
			if (api.isActionCreator()) {
				ActionCreator creator = FluxFactory.getActionCreator(api.getActionCreatorKey(), api.getKey());
				dispatcher.registerActionCreator(creator);
			}
			// Store
			if (api.isEntityStore()) {
				Store store = FluxFactory.getStore(api.getEntityStoreKey(), api.getKey());
				dispatcher.registerStore(store);
			}
			// Store
			if (api.isSearchStore()) {
				Store store = FluxFactory.getStore(api.getSearchStoreKey(), api.getKey());
				dispatcher.registerStore(store);
			}
		}
	}

	@Override
	public void handleViewEvent(final String viewId, final ViewEventType event, final Object data) {
		super.handleViewEvent(viewId, event, data);
		if (isEvent(CardEventType.RESET, event)) {
			handleResetCard((CardType) data);
		} else if (isEvent(CardEventType.SHOW, event)) {
			handleShowCard((CardType) data);
		}
	}

	protected void handleShowCard(final CardType card) {
		cardView.showCard(card);
	}

	protected void handleResetCard(final CardType card) {
		cardView.resetCard(card);
	}

}
