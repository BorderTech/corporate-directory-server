package com.github.bordertech.flux.wc.app.common.table;

import com.github.bordertech.flux.view.DumbView;
import com.github.bordertech.flux.view.ViewUtil;
import com.github.bordertech.flux.wc.app.view.event.base.SelectableBaseViewEvent;
import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WDiv;
import com.github.bordertech.wcomponents.lib.icons.IconConstants;

/**
 * Panel with a Button that can be used in a table column.
 *
 * @author jonathan
 */
public class ViewButtonPanel extends WDiv {

	private final WButton button = new WButton("View");

	public ViewButtonPanel() {
		add(button);
		button.setImageUrl(IconConstants.VIEW_IMAGE);
		button.setRenderAsLink(false);
		button.setAction(new Action() {
			@Override
			public void execute(ActionEvent event) {
				doDefaultAction();
			}
		});
		setBeanProperty(".");
	}

	public WButton getButton() {
		return button;
	}

	protected void doDefaultAction() {
		DumbView view = ViewUtil.findParentView(this);
		if (view != null) {
			view.dispatchViewEvent(SelectableBaseViewEvent.SELECT, getBean());
		}
	}

}
