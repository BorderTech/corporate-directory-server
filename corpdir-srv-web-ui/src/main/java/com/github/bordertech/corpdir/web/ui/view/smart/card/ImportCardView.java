package com.github.bordertech.corpdir.web.ui.view.smart.card;

import com.github.bordertech.corpdir.web.ui.CardType;
import com.github.bordertech.corpdir.web.ui.flux.view.impl.ImportSmartView;
import com.github.bordertech.flux.view.consumer.StoreConsumer;
import com.github.bordertech.flux.wc.common.TemplateConstants;
import com.github.bordertech.flux.wc.view.smart.secure.DefaultSecureCardView;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.addons.cardpath.impl.DefaultAppPath;

/**
 * Import secure card with a WSection Wrapper.
 *
 * @author aswinkandula
 */
public class ImportCardView extends DefaultSecureCardView implements StoreConsumer {

	public ImportCardView() {
		super("SY", new DefaultAppPath(CardType.IMPORT.getPath()));

		// Title
		getContent().addParameter(TemplateConstants.PARAM_TITLE, CardType.IMPORT.getDesc());

		WSection wrapper = new WSection(CardType.IMPORT.getDesc());

		ImportSmartView smartView = new ImportSmartView("SN");

		wrapper.getContent().add(smartView); // <-- inner panel

		addComponentToTemplate(TemplateConstants.TAG_VW_CONTENT, wrapper); // <-- outer panel

	}
}
