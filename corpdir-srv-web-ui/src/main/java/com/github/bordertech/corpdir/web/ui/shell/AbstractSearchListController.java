package com.github.bordertech.corpdir.web.ui.shell;

import com.github.bordertech.corpdir.web.ui.polling.PollingEvent;
import com.github.bordertech.corpdir.web.ui.polling.PollingView;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import java.util.List;

/**
 *
 * @author jonathan
 */
public abstract class AbstractSearchListController<S, T> extends AbstractBasicEventView implements MessageContainer {

	private final WMessages messages = new WMessages();

	private final CriteriaView<S> criteriaView;
	private final PollingView<S, List<T>> pollingView;
	private final ListView<T> listView;

	private final WPanel ajaxPanel = new WPanel() {
		@Override
		public boolean isHidden() {
			return !pollingView.isVisible() && !listView.isVisible();
		}
	};

	public AbstractSearchListController(final CriteriaView<S> criteriaView, final PollingView<S, List<T>> pollingView, final ListView<T> listView) {
		this.criteriaView = criteriaView;
		this.pollingView = pollingView;
		this.listView = listView;

		// Actions
		// Criteria action
		criteriaView.registerViewAction(CriteriaEvent.SEARCH, new ViewAction<CriteriaView<S>, CriteriaEvent>() {
			@Override
			public void execute(final CriteriaView<S> view, final CriteriaEvent viewEvent) {
				handleCriteria(view.getCriteria());
			}
		});

		// Polling action
		pollingView.registerViewAction(PollingEvent.COMPLETE, new ViewAction<PollingView<S, List<T>>, PollingEvent>() {
			@Override
			public void execute(final PollingView<S, List<T>> view, final PollingEvent viewEvent) {
				handleSearchResult(view.getPollingResult());
			}
		});
		// Selection action
		listView.registerViewAction(ListEvent.EDIT, new ViewAction<ListView<T>, ListEvent>() {
			@Override
			public void execute(final ListView<T> view, final ListEvent viewEvent) {
				handleSelection(viewEvent);
			}
		});

		add(criteriaView);
		add(ajaxPanel);
		ajaxPanel.add(pollingView);
		ajaxPanel.add(listView);

		// Default visibility
		pollingView.setVisible(false);
		listView.setVisible(false);

		// AJAX Targets
		criteriaView.addAjaxTarget(messages);
		pollingView.addAjaxTarget(messages);
		listView.addAjaxTarget(messages);

		criteriaView.addAjaxTarget(ajaxPanel);
		pollingView.addAjaxTarget(ajaxPanel);

	}

	public CriteriaView<S> getCriteriaView() {
		return criteriaView;
	}

	public PollingView<S, List<T>> getPollingView() {
		return pollingView;
	}

	public ListView<T> getListView() {
		return listView;
	}

	protected void handleCriteria(final S criteria) {

	}

	protected void handleSearchResult(final List<T> result) {

	}

	protected void handleSelection(final ListEvent event) {

	}

	@Override
	public WMessages getMessages() {
		return messages;
	}

}
