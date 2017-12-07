package com.github.bordertech.corpdir.web.ui.dumb.panel;

import com.github.bordertech.corpdir.api.v1.model.Location;
import com.github.bordertech.corpdir.web.ui.config.CardType;
import com.github.bordertech.corpdir.web.ui.dumb.BasicApiTreeablePanel;
import com.github.bordertech.wcomponents.WCollapsible;

/**
 * Location detail Form.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class LocationPanel extends BasicApiTreeablePanel<Location> {

	/**
	 * Construct basic location panel.
	 *
	 * @param viewId the viewId
	 */
	public LocationPanel(final String viewId) {
		super("Location", viewId, CardType.LOCATION_CARD);

		// Address
		AddressPanel addressPanel = new AddressPanel("ADDR");
		addressPanel.setBeanProperty("address");
		WCollapsible addrColl = new WCollapsible(addressPanel, "Addresss", WCollapsible.CollapsibleMode.CLIENT);
		getFormPanel().add(addrColl);

	}
}
