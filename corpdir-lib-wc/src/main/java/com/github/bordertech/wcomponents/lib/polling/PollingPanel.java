package com.github.bordertech.wcomponents.lib.polling;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxHelper;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.Margin;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WContainer;
import com.github.bordertech.wcomponents.WDiv;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WProgressBar;
import com.github.bordertech.wcomponents.WText;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This panel is used to poll via AJAX and also do a Reload of Views via AJAX.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class PollingPanel extends WDiv implements Pollable {

	/**
	 * Start polling manually button.
	 */
	private final WButton startButton = new WButton("Start");

	private final WDiv holder = new WDiv() {
		@Override
		protected void preparePaintComponent(final Request request) {
			super.preparePaintComponent(request);
			if (!isInitialised()) {
				handleInitPollingPanel(request);
				setInitialised(true);
			}
		}
	};
	/**
	 * Messages.
	 */
	private final WMessages messages = new WMessages(true);

	/**
	 * Retry load.
	 */
	private final WButton retryButton = new WButton("Retry");

	/**
	 * The container that holds the AJAX poller.
	 */
	private final WContainer pollingContainer = new WContainer();

	/**
	 * The container that holds the AJAX progress text and bar.
	 */
	private final WContainer pollingProgressContainer = new WContainer();

	private final WText pollingText = new WText() {
		@Override
		public String getText() {
			return getPollingText();
		}
	};

	private final WProgressBar pollingProgressBar = new WProgressBar(100);

	private final WText progressBarScript = new WText() {
		@Override
		protected void preparePaintComponent(final Request request) {
			if (!isInitialised()) {
				setText(buildProgressBarScript());
				setInitialised(true);
			}
		}
	};

	/**
	 * The container that holds the AJAX control.
	 */
	private final WDiv ajaxPollingPanel = new WDiv() {
		@Override
		public boolean isHidden() {
			return true;
		}
	};

	/**
	 * AJAX poller.
	 */
	private final WAjaxControl ajaxPolling = new WAjaxControl(null, ajaxPollingPanel) {
		@Override
		public int getDelay() {
			return getPollingInterval();
		}

		@Override
		public void handleRequest(final Request request) {
			super.handleRequest(request);
			// Check if Polling
			if (isPollingTrigger() && checkForStopPolling()) {
				doPanelReload();
			}
		}

	};

	/**
	 * AJAX control to reload whole view and any other views.
	 */
	private final WAjaxControl ajaxReload = new WAjaxControl(null, this) {
		@Override
		public void handleRequest(Request request) {
			super.handleRequest(request);
			// Reloading
			if (AjaxHelper.isCurrentAjaxTrigger(this)) {
				pollingContainer.reset();
				handleStoppedPolling();
			}
		}
	};

	public PollingPanel() {
		this(174);
	}

	/**
	 * Construct polling panel.
	 *
	 * @param delay the delay for polling
	 */
	public PollingPanel(final int delay) {

		add(holder);
		holder.setSearchAncestors(false);

		messages.setMargin(new Margin(0, 0, 3, 0));
		holder.add(messages);
		holder.add(startButton);
		holder.add(retryButton);

		// Manual Start load
		startButton.setAjaxTarget(this);
		startButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				handleStartButton();
			}
		});

		// Retry load
		retryButton.setAjaxTarget(this);
		retryButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				handleRetryButton();
			}
		});

		// Set default visibility
		// AJAX polling details
		setPollingInterval(delay);
		ajaxPolling.setLoadOnce(true);
		ajaxReload.setLoadOnce(true);
		ajaxReload.setDelay(10);
		progressBarScript.setEncodeText(false);

		// Progress Detials
		pollingProgressContainer.add(pollingText);
		pollingProgressContainer.add(pollingProgressBar);
		pollingProgressContainer.add(progressBarScript);

		// AJAX Details
		ajaxPollingPanel.add(ajaxPolling);
		ajaxPollingPanel.add(ajaxReload);

		// Polling container is outside AJAX panel so it does not pulse)
		holder.add(pollingContainer);
		pollingContainer.add(pollingProgressContainer);
		pollingContainer.add(ajaxPollingPanel);

		// Set default visibility
		pollingContainer.setVisible(false);
		ajaxPolling.setVisible(false);
		ajaxReload.setVisible(false);
		retryButton.setVisible(false);
		startButton.setVisible(false);
	}

	public final WDiv getHolder() {
		return holder;
	}

	protected void handleInitPollingPanel(final Request request) {
		getStartButton().setVisible(getStartType() == PollingStartType.BUTTON);
		// AUTO Start Polling
		if (getStartType() == PollingStartType.AUTOMATIC) {
			doStartPolling();
		}
	}

	protected void handleStartButton() {
		doStartPolling();
		startButton.setVisible(false);
	}

	protected void handleRetryButton() {
		doRetry();
	}

	/**
	 * @return the AJAX polling interval in milli seconds
	 */
	@Override
	public int getPollingInterval() {
		return getComponentModel().pollingInterval;
	}

	/**
	 *
	 * @param interval the AJAX polling interval in milli seconds
	 */
	@Override
	public final void setPollingInterval(final int interval) {
		getOrCreateComponentModel().pollingInterval = interval;
	}

	/**
	 * @param text the text displayed while polling
	 */
	@Override
	public void setPollingText(final String text) {
		getOrCreateComponentModel().pollingText = text;
	}

	/**
	 * @return the text displayed while polling
	 */
	@Override
	public String getPollingText() {
		return getComponentModel().pollingText;
	}

	/**
	 * @param pollingStatus the panel status
	 */
	@Override
	public void setPollingStatus(final PollingStatus pollingStatus) {
		getOrCreateComponentModel().serviceStatus = pollingStatus == null ? PollingStatus.STOPPED : pollingStatus;
	}

	/**
	 * @return the panel status
	 */
	@Override
	public PollingStatus getPollingStatus() {
		return getComponentModel().serviceStatus;
	}

	@Override
	public void doManualStart() {
		doStartPolling();
	}

	@Override
	public void doShowRetry() {
		getStartButton().setVisible(false);
		getRetryButton().setVisible(true);
		pollingProgressContainer.setVisible(false);
	}

	/**
	 * Retry the polling action.
	 */
	@Override
	public void doRetry() {
		doRefreshContent();
		doStartPolling();
	}

	/**
	 * Reset to start load again.
	 */
	@Override
	public void doRefreshContent() {
		getHolder().reset();
		setPollingStatus(PollingStatus.STOPPED);
	}

	@Override
	public List<AjaxTarget> getAjaxTargets() {
		return getComponentModel().extraTargets;
	}

	@Override
	public void addAjaxTarget(final AjaxTarget target) {
		PollingModel model = getOrCreateComponentModel();
		if (model.extraTargets == null) {
			model.extraTargets = new ArrayList();
		}
		if (!model.extraTargets.contains(target)) {
			model.extraTargets.add(target);
		}
	}

	@Override
	public PollingStartType getStartType() {
		return getComponentModel().startType;
	}

	@Override
	public void setStartType(final PollingStartType startType) {
		getOrCreateComponentModel().startType = startType == null ? PollingStartType.MANUAL : startType;
	}

	/**
	 * Start AJAX polling.
	 */
	protected void doStartPolling() {
		// Make sure start button is not visible
		getStartButton().setVisible(false);

		// Start AJAX polling
		setPollingStatus(PollingStatus.PROCESSING);
		pollingContainer.reset();
		pollingContainer.setVisible(true);
		ajaxPolling.setVisible(true);
		handleStartedPolling();
	}

	/**
	 * Do AJAX Reload.
	 */
	protected void doPanelReload() {
		boolean alreadyPolling = isPolling();
		pollingContainer.reset();
		List<AjaxTarget> targets = getAjaxTargets();
		if (targets != null && !targets.isEmpty()) {
			ajaxReload.addTargets(targets);
		}
		pollingContainer.setVisible(true);
		ajaxReload.setVisible(true);
		if (!alreadyPolling) {
			handleStartedPolling();
		}
	}

	/**
	 * The messages for the panel.
	 *
	 * @return the messages for the panel
	 */
	protected WMessages getMessages() {
		return messages;
	}

	protected void handleErrorMessage(final String msg) {
		handleErrorMessage(Arrays.asList(msg));
	}

	protected void handleErrorMessage(final List<String> msgs) {
		for (String msg : msgs) {
			getMessages().error(msg);
		}
	}

	/**
	 * @return the retry button.
	 */
	@Override
	public WButton getRetryButton() {
		return retryButton;
	}

	/**
	 * @return the start button
	 */
	@Override
	public WButton getStartButton() {
		return startButton;
	}

	/**
	 *
	 * @return true if need to stop polling
	 */
	protected boolean checkForStopPolling() {
		PollingStatus status = getPollingStatus();
		return status != PollingStatus.PROCESSING;
	}

	/**
	 * Start polling.
	 */
	protected void handleStartedPolling() {
		// Do Nothing
	}

	/**
	 * Stopped polling and panel has been reloaded.
	 */
	protected void handleStoppedPolling() {
		// Do Nothing
	}

	/**
	 * @return true if polling and is the current AJAX trigger.
	 */
	protected boolean isPollingTrigger() {
		return isPolling() && AjaxHelper.isCurrentAjaxTrigger(ajaxPolling);
	}

	/**
	 * @return true if currently polling
	 */
	protected boolean isPolling() {
		return pollingContainer.isVisible();
	}

	/**
	 * @return the script to step the progress bar
	 */
	protected String buildProgressBarScript() {
		StringBuilder script = new StringBuilder();
		script.append("<script type='text/javascript'>");
		script.append("function startStepProgressBar() {");
		script.append("  var elem = document.getElementById('").append(pollingProgressBar.getId()).append("');");
		script.append("  window.setInterval(stepProgressBar, 250, elem);");
		script.append("}");
		script.append("function stepProgressBar(bar) {");
		script.append("   if (bar.value > 99) { bar.value = 0; }");
		script.append("   bar.value++;");
		script.append("}");
		script.append("window.setTimeout(startStepProgressBar, 1000);");
		script.append("</script>");
		return script.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PollingModel newComponentModel() {
		return new PollingModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PollingModel getOrCreateComponentModel() {
		return (PollingModel) super.getOrCreateComponentModel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PollingModel getComponentModel() {
		return (PollingModel) super.getComponentModel();
	}

	/**
	 * This model holds the state information.
	 */
	public static class PollingModel extends DivModel {

		/**
		 * Service status.
		 */
		private PollingStatus serviceStatus = PollingStatus.STOPPED;

		/**
		 * Polling text.
		 */
		private String pollingText = "Loading....";

		/**
		 * The polling interval in milli seconds.
		 */
		private int pollingInterval;

		/**
		 * Extra AJAX targets when polling stops.
		 */
		private List<AjaxTarget> extraTargets;

		/**
		 * Start type.
		 */
		private PollingStartType startType = PollingStartType.MANUAL;

	}

}