package com.github.bordertech.wcomponents.lib.app.combo;

import com.github.bordertech.wcomponents.WTemplate;
import com.github.bordertech.wcomponents.lib.app.ctrl.FormAndSelectCtrl;
import com.github.bordertech.wcomponents.lib.app.ctrl.ResetViewCtrl;
import com.github.bordertech.wcomponents.lib.app.view.FormView;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;
import com.github.bordertech.wcomponents.lib.mvc.msg.DefaultMessageComboView;

/**
 * Form View with a Select View.
 *
 * @author jonathan
 * @param <T> the entity type
 */
public class FormWithSelectView<T> extends DefaultMessageComboView<T> {

	public FormWithSelectView(final FormView<T> formView, final SelectView<T> selectView) {
		this(formView, selectView, null);
	}

	public FormWithSelectView(final FormView<T> formView, final SelectView<T> selectView, final String qualifier) {
		super("wclib/hbs/layout/combo-ent-select.hbs", qualifier);

		// Ctrl
		FormAndSelectCtrl<T> ctrl = new FormAndSelectCtrl<>(qualifier);
		ctrl.setFormView(formView);
		ctrl.setSelectView(selectView);
		ctrl.addView(getMessageView());

		// Reset
		ResetViewCtrl resetCtrl = new ResetViewCtrl(qualifier);

		WTemplate content = getContent();
		content.addTaggedComponent("vw-ctrl-res", resetCtrl);
		content.addTaggedComponent("vw-ctrl", ctrl);
		content.addTaggedComponent("vw-select", selectView);
		content.addTaggedComponent("vw-entity", formView);

		selectView.addHtmlClass("wc-panel-type-box");
		formView.addHtmlClass("wc-panel-type-box");

		// Default visibility
		formView.setContentVisible(false);
	}

}
