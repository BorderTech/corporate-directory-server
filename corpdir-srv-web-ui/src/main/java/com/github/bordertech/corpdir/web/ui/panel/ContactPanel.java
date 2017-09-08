package com.github.bordertech.corpdir.web.ui.panel;

import com.github.bordertech.corpdir.api.v1.model.Contact;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.web.ui.model.ContactPositionsModel;
import com.github.bordertech.corpdir.web.ui.model.PositionModel;
import com.github.bordertech.corpdir.web.ui.model.SearchVersionKey;
import com.github.bordertech.wcomponents.HeadingLevel;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WHeading;
import com.github.bordertech.wcomponents.lib.app.SelectMenuView;
import com.github.bordertech.wcomponents.lib.app.combo.AddRemoveListView;
import com.github.bordertech.wcomponents.lib.app.combo.PollingSelectView;
import com.github.bordertech.wcomponents.lib.app.combo.SelectWithCriteriaTextView;
import com.github.bordertech.wcomponents.lib.app.combo.SelectWithCriteriaView;
import com.github.bordertech.wcomponents.lib.app.event.ListEventType;
import java.util.ArrayList;
import java.util.List;

/**
 * Contact detail Form.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class ContactPanel extends BasicApiKeyPanel<Contact> {

	private final PollingSelectView selectView;

// TODO
//	private String locationId;
//	private boolean hasImage;
//	private List<Channel> channels;
//	private List<String> positionIds;
	/**
	 * Construct basic detail panel. \
	 */
	public ContactPanel(final String qualifier) {
		super(qualifier, false);
		// Contact Details
		addTextField("Business key", "businessKey", true);
		addTextField("First name", "firstName", true);
		addTextField("Last name", "lastName", true);
		addTextField("Company", "companyTitle", false);
		addTextField("Description", "description", true);
		addCheckBox("Active", "active", false);

		getFormPanel().add(new WHeading(HeadingLevel.H2, "Address"));
		AddressPanel addressPanel = new AddressPanel(qualifier);
		addressPanel.setBeanProperty("address");
		getFormPanel().add(addressPanel);

		getFormPanel().add(new WHeading(HeadingLevel.H2, "Positions"));

		String qual = qualifier + "-pos";
		selectView = new PollingSelectView(new SelectMenuView(qual), qual);
		SelectWithCriteriaView findView = new SelectWithCriteriaTextView(qual + "-2");
		findView.addDispatcherOverride(qual, ListEventType.SELECT);

		AddRemoveListView posView = new AddRemoveListView(selectView, findView, qual);
		posView.addModel(qual + "search", new ContactPositionsModel());
		posView.addModel(qual + "-2search", new PositionModel());

		getFormPanel().add(posView);
	}

	@Override
	protected void initViewContent(final Request request) {
		Contact bean = getViewBean();
		if (bean.getPositionIds() != null && !bean.getPositionIds().isEmpty()) {
			selectView.getPollingView().setPollingCriteria(new SearchVersionKey(null, bean.getId()));
			selectView.getPollingView().setContentVisible(true);
		}
		super.initViewContent(request);
	}

	@Override
	public void updateBeanValue() {
		super.updateBeanValue();
		Contact bean = getViewBean();
		List<Position> positions = (List<Position>) selectView.getListView().getBeanValue();
		List<String> posKeys = new ArrayList<>();
		for (Position pos : positions) {
			posKeys.add(pos.getId());
		}
		bean.setPositionIds(posKeys);

	}

}
