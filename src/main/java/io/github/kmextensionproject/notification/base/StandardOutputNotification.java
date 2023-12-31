package io.github.kmextensionproject.notification.base;

import static io.github.kmextensionproject.notification.base.NotificationResult.success;

/**
 * This class is automatically loaded by NotificationLoader when no other
 * Notification implementations are available.
 *
 * @author mkrajcovic
 */
public class StandardOutputNotification implements Notification {

	/**
	 * Prints the message subject and body to the standard output.
	 * @return successful notification result with empty description
	 */
	@Override
	public NotificationResult sendNotification(Message message, Recipient recipient) {
		System.out.println("Console Notification: " + message.getSubject() + " - " + message.getBody()); // NOSONAR
		return success();
	}
}
