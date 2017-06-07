package com.github.bordertech.corpdir.web.ui.shell;

import com.github.bordertech.corpdir.web.ui.polling.AbstractPollingPanel;
import com.github.bordertech.corpdir.web.ui.polling.PollingController;
import com.github.bordertech.corpdir.web.ui.polling.PollingServiceException;
import com.github.bordertech.corpdir.web.ui.polling.PollingStatus;
import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WebUtilities;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract action view.
 *
 * @param <T> the entity type
 * @param <R> the entity id
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public abstract class AbstractActionView<T, R> extends WSection implements ActionView<T, R> {

	private static final Log LOG = LogFactory.getLog(AbstractActionView.class);

	private final WMessages messages = new WMessages();

	private final ActionMenu actionMenu;

	private final PollingController<T, R> pollingServicePanel = new AbstractPollingPanel<T, R>(173) {
		@Override
		public T doServiceCall(final R recordId) throws PollingServiceException {
			try {
				T bean = doRetrieveServiceCall(recordId);
				return bean;
			} catch (Exception e) {
				throw new PollingServiceException("Error retrieving record. " + e.getMessage(), e);
			}
		}

		@Override
		protected void handleStoppedPolling() {
			super.handleStoppedPolling();
			if (getPollingStatus() == PollingStatus.COMPLETE) {
				T bean = getServiceResponse();
				loadEntity(bean);
			}
		}
	};

	private final EntityView<T, R> detailView;

	/**
	 * @param title the view title
	 * @param detailView the detail panel
	 */
	public AbstractActionView(final String title, final EntityView<T, R> detailView) {
		super(title);
		this.detailView = detailView;
		this.actionMenu = new ActionMenu(this);

		WPanel content = getContent();
		content.add(actionMenu);
		content.add(messages);
		content.add(detailView);
		content.add(pollingServicePanel);

		// Default Visibility
		pollingServicePanel.setVisible(false);
		detailView.setVisible(false);
		detailView.setSearchAncestors(false);
		detailView.setBeanProperty(".");

		// AJAX Target for menu items
		actionMenu.addAjaxTarget(content);
	}

	@Override
	public void loadEntityById(final R id) {
		reset();
		pollingServicePanel.setVisible(true);
		pollingServicePanel.setRecordId(id);
	}

	@Override
	public void loadEntity(final T bean) {
		reset();
		detailView.setBean(bean);
		detailView.setVisible(true);
	}

	@Override
	public void createRecord() {
		T bean = newRecordInstance();
		loadEntity(bean);
		setActionMode(ActionMode.Create);
	}

	@Override
	public T getEntity() {
		return detailView.getEntity();
	}

	@Override
	public R getEntityId() {
		return detailView.getEntityId();
	}

	@Override
	public void addEventAjaxTarget(final AjaxTarget target) {
		actionMenu.addAjaxTarget(target);
	}

	protected void setActionMode(final ActionMode actionMode) {
		getOrCreateComponentModel().actionMode = actionMode;
	}

	@Override
	public ActionMode getActionMode() {
		return getComponentModel().actionMode;
	}

	@Override
	public void handleEvent(final RecordEvent event) {
		switch (event) {
			case Back:
				handleBack();
				break;
			case Cancel:
				handleCancel();
				break;
			case Delete:
				handleDelete();
				break;
			case Edit:
				handleEdit();
				break;
			case Refresh:
				handleRefresh();
				break;
			case Save:
				handleSave();
				break;
			default:
			// Nothing
		}
	}

	@Override
	public void setEventAction(final RecordEvent event, final Action action) {
		ActionViewModel model = getOrCreateComponentModel();
		if (model.eventActions == null) {
			model.eventActions = new HashMap<>();
		}
		if (action == null) {
			model.eventActions.remove(event);
		} else {
			model.eventActions.put(event, action);
		}
	}

	@Override
	public Action getEventAction(final RecordEvent event) {
		ActionViewModel model = getComponentModel();
		if (model.eventActions != null) {
			return model.eventActions.get(event);
		}
		return null;
	}

	protected void handleBack() {
		handleEventAction(RecordEvent.Back, null);
	}

	protected void handleRefresh() {
		R id = getEntityId();
		loadEntityById(id);
		handleEventAction(RecordEvent.Refresh, id);
	}

	protected void handleEdit() {
		detailView.setFormReadOnly(false);
		setActionMode(ActionMode.Edit);
		handleEventAction(RecordEvent.Edit, getEntity());
	}

	protected void handleCancel() {
		R id = getEntityId();
		loadEntityById(id);
		handleEventAction(RecordEvent.Cancel, id);
	}

	protected void handleSave() {
		doUpdateDetailBean();
		T bean = getEntity();
		T updated;
		try {
			updated = doSaveServiceCall(bean);
		} catch (Exception e) {
			String msg = "Error calling save service. " + e.getMessage();
			LOG.error(msg, e);
			getMessages().error(msg);
			return;
		}
		loadEntity(updated);
		handleEventAction(RecordEvent.Save, updated);
	}

	protected void handleDelete() {
		T bean = getEntity();
		try {
			doDeleteServiceCall(bean);
		} catch (Exception e) {
			String msg = "Error calling delete service. " + e.getMessage();
			LOG.error(msg, e);
			getMessages().error(msg);
			return;
		}
		detailView.reset();
		handleEventAction(RecordEvent.Delete, bean);
	}

	protected void handleEventAction(final RecordEvent action, final Object data) {
		Action eventAction = getEventAction(action);
		if (eventAction != null) {
			ActionEvent event = new ActionEvent(this, action.name(), data);
			eventAction.execute(event);
		}
	}

	protected void doUpdateDetailBean() {
		WebUtilities.updateBeanValue(detailView);
	}

	/**
	 * @return true if the bean has been loaded successfully
	 */
	@Override
	public boolean isLoaded() {
		return detailView.isVisible() && detailView.getEntity() != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WMessages getMessages() {
		return messages;
	}

	abstract T newRecordInstance();

	@Override
	protected ActionViewModel newComponentModel() {
		return new ActionViewModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ActionViewModel getComponentModel() {
		return (ActionViewModel) super.getComponentModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ActionViewModel getOrCreateComponentModel() {
		return (ActionViewModel) super.getOrCreateComponentModel();
	}

	/**
	 * Holds the extrinsic state information of the edit view.
	 */
	public static class ActionViewModel extends WSection.SectionModel {

		private ActionMode actionMode = ActionMode.View;
		private Map<RecordEvent, Action> eventActions;
	}

}
