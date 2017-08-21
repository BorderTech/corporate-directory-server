package com.github.bordertech.wcomponents.lib.app.combo;

import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.lib.app.DefaultPollingView;
import com.github.bordertech.wcomponents.lib.app.ctrl.ListWithCriteriaCtrl;
import com.github.bordertech.wcomponents.lib.app.view.CriteriaView;
import com.github.bordertech.wcomponents.lib.app.view.ListView;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.flux.ViewCombo;
import com.github.bordertech.wcomponents.lib.flux.wc.DefaultView;
import com.github.bordertech.wcomponents.lib.flux.wc.WViewContent;
import com.github.bordertech.wcomponents.lib.model.RequiresServiceModel;
import com.github.bordertech.wcomponents.lib.model.ServiceModel;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class ListWithCriteriaView<S, T> extends DefaultView implements MessageContainer, ViewCombo, ListView<T>,
		RequiresServiceModel<S, List<T>> {

	private final WMessages messages = new WMessages();

	private final CriteriaView<S> criteriaView;

	private final ListView<T> listView;

	private final DefaultPollingView<S, List<T>> pollingView;

	private final ListWithCriteriaCtrl<S, T> ctrl;

	public ListWithCriteriaView(final Dispatcher dispatcher, final String qualifier, final CriteriaView<S> criteriaView, final ListView<T> listView) {
		super(dispatcher, qualifier);

		this.criteriaView = criteriaView;
		this.listView = listView;
		this.pollingView = new DefaultPollingView<>(dispatcher, qualifier);

		// Create controller
		this.ctrl = new ListWithCriteriaCtrl<>(dispatcher, qualifier);

		// Set views on Controller
		ctrl.setCriteriaView(criteriaView);
		ctrl.setPollingView(pollingView);
		ctrl.setListView(listView);

		// Add views to holder
		WViewContent holder = getContent();
		holder.add(ctrl);
		holder.add(messages);
		holder.add(criteriaView);
		holder.add(pollingView);
		holder.add(listView);
	}

	public CriteriaView<S> getCriteriaView() {
		return criteriaView;
	}

	public DefaultPollingView<S, List<T>> getPollingView() {
		return pollingView;
	}

	public ListView<T> getListView() {
		return listView;
	}

	@Override
	public List<T> getViewBean() {
		return listView.getViewBean();
	}

	@Override
	public void setViewBean(final List<T> viewBean) {
		listView.setViewBean(viewBean);
	}

	@Override
	public void updateViewBean() {
		listView.updateViewBean();
	}

	@Override
	public boolean validateView() {
		return listView.validateView();
	}

	@Override
	public ServiceModel<S, List<T>> getServiceModel() {
		return ctrl.getServiceModel();
	}

	@Override
	public void setServiceModel(final ServiceModel<S, List<T>> serviceModel) {
		ctrl.setServiceModel(serviceModel);
	}

	@Override
	public void configViews() {
		ctrl.configViews();
	}

	@Override
	public WMessages getMessages() {
		return messages;
	}

	@Override
	public void addItem(final T entity) {
		listView.addItem(entity);
	}

	@Override
	public void removeItem(final T entity) {
		listView.removeItem(entity);
	}

	@Override
	public void updateItem(final T entity) {
		listView.updateItem(entity);
	}

}
