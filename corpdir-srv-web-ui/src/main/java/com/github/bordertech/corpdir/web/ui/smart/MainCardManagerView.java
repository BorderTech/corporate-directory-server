package com.github.bordertech.corpdir.web.ui.smart;

import com.github.bordertech.corpdir.web.ui.config.CardType;
import com.github.bordertech.corpdir.web.ui.config.DataApiType;
import com.github.bordertech.flux.wc.view.FluxSmartView;
import com.github.bordertech.flux.wc.view.smart.CrudSmartView;
import com.github.bordertech.flux.wc.view.smart.FormSmartView;
import com.github.bordertech.flux.wc.view.smart.cardmgr.SecureCardManagerView;
import java.util.HashMap;
import java.util.Map;

/**
 * The Card Manager that creates all the Cards via the Card Types.
 *
 * @author jonathan
 */
public class MainCardManagerView extends SecureCardManagerView {

	public MainCardManagerView(final String viewId) {
		super(viewId);

		int idx = 1;
		for (CardType card : CardType.values()) {
			FluxSmartView view = card.createCardViewInstance();
			view.setQualifier("M-E" + idx++);
			view.setQualifierContext(true);
			if (view instanceof FormSmartView) {
				CrudSmartView form = (CrudSmartView) view;
				DataApiType api = card.getApiType();
				form.setEntityActionCreatorKey(api.getActionCreatorKey());
				form.setEntityStoreKey(api.getEntityStoreKey());
				form.setSearchStoreKey(api.getSearchStoreKey());
			}
			setupCard(card, view);
		}
		// Default
		showCard(CardType.CONTACT_CARD);
	}

	private void setupCard(final CardType type, final FluxSmartView view) {
		addCardByType(type, view);
	}

	public final void showCard(final CardType card) {
		FluxSmartView view = getCardByType(card);
		if (view != null) {
			setCurrentCard(view);
			getOrCreateComponentModel().current = card;
		}
	}

	public void resetCard(final CardType card) {
		FluxSmartView view = getCardByType(card);
		if (view != null) {
			view.reset();
		}
	}

	public final CardType getCurrentType() {
		return getComponentModel().current;
	}

	protected final void addCardByType(final CardType card, final FluxSmartView view) {
		// Add to Mgr
		addCard(view, card.getPath());
		// App to Map by type
		MainCardModel model = getOrCreateComponentModel();
		if (model.cards == null) {
			model.cards = new HashMap<>();
		}
		model.cards.put(card, view);
	}

	protected final FluxSmartView getCardByType(final CardType card) {
		MainCardModel model = getComponentModel();
		if (model.cards != null) {
			return model.cards.get(card);
		}
		return null;
	}

	@Override
	protected MainCardModel newComponentModel() {
		return new MainCardModel();
	}

	@Override
	protected MainCardModel getComponentModel() {
		return (MainCardModel) super.getComponentModel();
	}

	@Override
	protected MainCardModel getOrCreateComponentModel() {
		return (MainCardModel) super.getOrCreateComponentModel();
	}

	/**
	 * Just here as a place holder and easier for other Views to extend.
	 */
	public static class MainCardModel extends SmartViewModel {

		private Map<CardType, FluxSmartView> cards;

		private CardType current;
	}

}
