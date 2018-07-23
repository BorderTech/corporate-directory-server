package com.github.bordertech.corpdir.web.ui.view.smart.card;

import com.github.bordertech.corpdir.web.ui.CardType;
import com.github.bordertech.corpdir.web.ui.flux.view.impl.ImportSmartView;
import com.github.bordertech.flux.crud.actioncreator.CrudActionCreator;
import com.github.bordertech.flux.crud.view.consumer.CrudActionCreatorConsumer;
import com.github.bordertech.flux.store.StoreUtil;
import com.github.bordertech.flux.wc.common.TemplateConstants;
import com.github.bordertech.flux.wc.view.smart.secure.DefaultSecureCardView;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.addons.cardpath.impl.DefaultAppPath;

/**
 * Sync 'smart' view, to propagates the Action though a Dispatcher
 * 
 * @author exiqaj
 * @param <T>
 */
public class ImportCardView<T> extends DefaultSecureCardView implements CrudActionCreatorConsumer<T> {
	
	private final WSection wrapper;
	private final ImportSmartView smartView;
	
	public ImportCardView() {
		super("SY", new DefaultAppPath(CardType.IMPORT.getPath()));
		
		// Title
		getContent().addParameter(TemplateConstants.PARAM_TITLE, CardType.IMPORT.getDesc());
		
		this.wrapper = new WSection(CardType.IMPORT.getDesc());
		
		smartView = new ImportSmartView("SN");
		
		wrapper.getContent().add(smartView); // <-- inner panel
		
		addComponentToTemplate(TemplateConstants.TAG_VW_CONTENT, wrapper); // <-- outer panel
		
	}

	@Override
	public String getActionCreatorKey() {
		return getComponentModel().entityCreatorKey;
	}

	@Override
	public void setActionCreatorKey(final String entityCreatorKey) {
		getOrCreateComponentModel().entityCreatorKey = entityCreatorKey;
	}

	@Override
	public CrudActionCreator<T> getActionCreatorByKey() {
		return StoreUtil.getActionCreator(getActionCreatorKey());
	}

	@Override
	protected CrudFormModel newComponentModel() {
		return new CrudFormModel();
	}

	@Override
	protected CrudFormModel getComponentModel() {
		return (CrudFormModel) super.getComponentModel();
	}

	@Override
	protected CrudFormModel getOrCreateComponentModel() {
		return (CrudFormModel) super.getOrCreateComponentModel();
	}
	public static class CrudFormModel extends SmartViewModel {

		private String entityCreatorKey;
	}
}
