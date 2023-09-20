package io.github.kmextensionproject.notification.base;

import static io.github.kmextensionproject.notification.base.NotificationResult.Status.SUCCESS;

import java.io.IOException;

/**
 * This class is automatically loaded by NotificationLoader when no other
 * Notification implementations are available.
 *
 * @author mkrajcovic
 */
public class StandardOutputNotification implements Notification {

	/**
	 * Prints the message subject and body to the standard output.
	 */
	@Override
	public NotificationResult sendNotification(Message message, Recipient recipient) throws IOException {
		System.out.println("Console Notification: " + message.getSubject() + " - " + message.getBody()); // NOSONAR
		return new NotificationResult(SUCCESS);
	}
}
