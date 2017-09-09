package com.github.bordertech.wcomponents.lib.app.combo;

import com.github.bordertech.wcomponents.WDialog;
import com.github.bordertech.wcomponents.WTemplate;
import com.github.bordertech.wcomponents.lib.WDiv;
import com.github.bordertech.wcomponents.lib.app.AddRemoveToolbar;
import com.github.bordertech.wcomponents.lib.app.ctrl.AddRemoveListCtrl;
import com.github.bordertech.wcomponents.lib.app.ctrl.TranslateEventCtrl;
import com.github.bordertech.wcomponents.lib.app.event.ListEventType;
import com.github.bordertech.wcomponents.lib.app.event.SearchEventType;
import com.github.bordertech.wcomponents.lib.app.view.SelectView;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultComboView;
import com.github.bordertech.wcomponents.lib.mvc.impl.DefaultView;

/**
 * ADD and REMOVE Toolbar.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class AddRemoveListView<S, T> extends DefaultComboView<T> {

	private final TranslateEventCtrl ctrl = new TranslateEventCtrl();

	private final DefaultView dialogView = new DefaultView() {
		@Override
		public void setContentVisible(final boolean visible) {
			super.setContentVisible(visible);
			if (visible) {
				showDialog();
			}
		}
	};
	private final WDiv dialogContent = new WDiv();
	private final WDialog dialog = new WDialog(dialogContent);

	public AddRemoveListView(final String qualifier, final SelectView<T> selectView, final SelectView findView) {
		super("wclib/hbs/layout/combo-add-rem.hbs");
		dialog.setMode(WDialog.MODAL);

		// Setup qualifier context
		setQualifier(qualifier);
		setQualifierContext(true);
		ctrl.setQualifier("X");
		findView.setQualifier("X");
		// Make all the "Events" in FIND View unique with "X"
		findView.setQualifierContext(true);

		AddRemoveToolbar toolbarView = new AddRemoveToolbar();
		AddRemoveListCtrl addCtrl = new AddRemoveListCtrl();
		addCtrl.setAddRemoveToolbar(toolbarView);
		addCtrl.setAddView(dialogView);
		addCtrl.setSelectView(selectView);

		dialogView.getContent().add(dialog);
		dialogContent.add(findView);
		dialog.setMode(WDialog.MODAL);

		WTemplate content = getContent();
		content.addTaggedComponent("vw-ctrl2", ctrl);
		content.addTaggedComponent("vw-ctrl3", addCtrl);
		content.addTaggedComponent("vw-select", selectView);
		content.addTaggedComponent("vw-toolbar", toolbarView);
		content.addTaggedComponent("vw-dialog", dialogView);
		setBlocking(true);

		// Bean Property
		setBeanProperty(".");
		setSearchAncestors(false);
	}

	@Override
	public void configViews() {
		super.configViews();
		// Translate the "SELECT" from the "FIND" to an "ADD"
		ctrl.translate(ListEventType.SELECT, SearchEventType.SEARCH_ADD, getFullQualifier());
	}

	protected void showDialog() {
		dialogView.reset();
		dialog.display();
	}

}
