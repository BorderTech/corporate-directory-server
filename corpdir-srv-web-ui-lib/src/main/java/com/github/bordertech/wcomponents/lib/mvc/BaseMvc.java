package com.github.bordertech.wcomponents.lib.mvc;

import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.lib.flux.Dispatcher;
import com.github.bordertech.wcomponents.lib.flux.Event;
import com.github.bordertech.wcomponents.lib.flux.EventType;
import com.github.bordertech.wcomponents.lib.mvc.msg.MessageEventType;
import com.github.bordertech.wcomponents.validation.Diagnostic;
import java.util.List;

/**
 * @author Jonathan Austin
 * @since 1.0.0
 */
public interface BaseMvc extends WComponent {

	/**
	 * The default qualifier separator character.
	 */
	String QUALIFIER_CONTEXT_SEPERATOR = "_";

	/**
	 * A qualifier must start with a letter and followed by letters, digits or dash.
	 */
	String QUALIFIER_VALIDATION_PATTERN = "[a-zA-Z][0-9a-zA-Z-]*";

	/**
	 *
	 * @return the dispatcher attached to this view.
	 */
	Dispatcher getDispatcher();

	/**
	 * @return the qualifier to be used on listeners or events (if needed)
	 */
	String getQualifier();

	/**
	 *
	 * @param qualifier the qualifier to be used on events
	 */
	void setQualifier(final String qualifier);

	/**
	 * @return the context qualifier
	 */
	String getFullQualifier();

	/**
	 * Sometimes an event has to be given a more specific qualifier. The same event might happen more then once in a
	 * Combo View.
	 *
	 * @param qualifier the qualifier value
	 * @param types the event type to override the qualifier
	 */
	void addDispatcherOverride(final String qualifier, final EventType... types);

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param eventType the event type
	 */
	void dispatchEvent(final EventType eventType);

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param eventType the event type
	 * @param data the event data
	 */
	void dispatchEvent(final EventType eventType, final Object data);

	/**
	 * Helper method to dispatch an event for this view with the view qualifier automatically added.
	 *
	 * @param eventType the event type
	 * @param data the event data
	 * @param exception an exception
	 */
	void dispatchEvent(final EventType eventType, final Object data, final Exception exception);

	void dispatchEvent(final Event event);

	void dispatchMessageReset();

	void dispatchValidationMessages(final List<Diagnostic> diags);

	void dispatchMessage(final MessageEventType type, final String text);

	void dispatchMessage(final MessageEventType type, final List<String> texts);

	/**
	 * @return the qualifier to be used on message listeners or message events (if needed)
	 */
	String getMessageQualifier();

	/**
	 *
	 * @param qualifier the message qualifier to be used on message events
	 */
	void setMessageQualifier(final String qualifier);

	/**
	 * @return the message context qualifier
	 */
	String getMessageFullQualifier();

	/**
	 * Helper method to set both Qualifier and MessageQualifer.
	 *
	 * @param qualifier the qualifier and message qualifier
	 */
	void setQualifierAndMessageQualifier(final String qualifier);

}
