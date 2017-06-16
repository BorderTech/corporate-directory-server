package com.github.bordertech.corpdir.web.ui.view;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.web.ui.shell.EntityMode;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.WCheckBox;
import com.github.bordertech.wcomponents.WDefinitionList;
import com.github.bordertech.wcomponents.WFieldLayout;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WText;
import com.github.bordertech.wcomponents.WTextField;

/**
 * Basic Detail Form View.
 *
 * @param <T> the API object type
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class BasicEntityView<T extends ApiKeyIdObject> extends AbstractEntityView<T> {

	private final WPanel formPanel = new WPanel();

	private final WFieldLayout formLayout = new WFieldLayout();

	private final WPanel versionPanel = new WPanel() {
		@Override
		public boolean isVisible() {
			return isExistingEntity();
		}
	};

	/**
	 * Construct basic detail panel.
	 */
	public BasicEntityView() {
		add(formPanel);
		add(versionPanel);

		setupFormDefaults();
		setupVersionDetails();
	}

	/**
	 * @return the panel that hold the form details
	 */
	protected WPanel getFormPanel() {
		return formPanel;
	}

	/**
	 * @return the field layout holding the basic input fields
	 */
	protected WFieldLayout getFormLayout() {
		return formLayout;
	}

	/**
	 * @return the panel that holds the record id and version details
	 */
	protected WPanel getVersionPanel() {
		return versionPanel;
	}

	/**
	 * Setup the form input details.
	 */
	private void setupFormDefaults() {
		WFieldLayout layout = getFormLayout();

		WPanel panel = getFormPanel();
		panel.add(layout);

		// Business Key
		WTextField txtBusKey = new WTextField();
		txtBusKey.setMandatory(true);
		txtBusKey.setBeanProperty("businessKey");
		layout.addField("Business key", txtBusKey);

		// Description
		WTextField txtDesc = new WTextField();
		txtDesc.setMandatory(true);
		txtDesc.setBeanProperty("description");
		layout.addField("Description", txtDesc);

		// Active
		WCheckBox chbActive = new WCheckBox();
		chbActive.setBeanProperty("active");
		layout.addField("Active", chbActive);
	}

	/**
	 * Setup the record id and version details.
	 */
	private void setupVersionDetails() {

		WPanel panel = getVersionPanel();

		WDefinitionList def = new WDefinitionList();
		panel.add(def);

		// Record Details
		// Custom
		WText txtCustom = new WText();
		txtCustom.setBeanProperty("custom");
		def.addTerm("Custom", txtCustom);

		// Version
		WText txtVersion = new WText();
		txtVersion.setBeanProperty("version");
		def.addTerm("Version", txtVersion);

		// ID
		WText txtId = new WText();
		txtId.setBeanProperty("id");
		def.addTerm("ID", txtId);
	}

	private boolean isExistingEntity() {
		return true;
	}

	@Override
	public void setEntity(T entity) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void setEntityMode(EntityMode mode) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public EntityMode getEntityMode() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void addAjaxTarget(AjaxTarget target) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
