package io.github.kmextensionproject.notification.base;

import java.io.IOException;
import java.util.Collection;

import io.github.kmextensionproject.notification.classloading.GlobalNotificationRegistry;
import io.github.kmextensionproject.notification.classloading.NotificationLoader;

/**
 * Implementing this common interface for sending multi-platform simple messages
 * allows implementors to be automatically registered for harvest by the
 * {@link NotificationLoader} class if they come from
 * {@code io.github.kmextensionproject.notification} domain, otherwise if the user implements
 * this interface and provides own platform notifications, it should be manually
 * put to registry using the {@link GlobalNotificationRegistry}.
 *
 * @author mkrajcovic
 */
public interface Notification {

	/**
	 * Sends the notification message to a given recipient.
	 *
	 * @param message   - what to be sent
	 * @param recipient - which recipients to send the message to
	 * @throws IOException - if some communication failure occurs
	 */
	public void sendNotification(Message message, Recipient recipient) throws IOException;

	/**
	 * Sends one notification message to multiple recipients.
	 *
	 * @param message       - what to be sent
	 * @param recipientList - which recipients to send the message to
	 * @throws IOException - if some communication failure occurs
	 */
	public default void sendNotification(Message message, Collection<Recipient> recipientList) throws IOException {
		for (Recipient recipient : recipientList) {
			sendNotification(message, recipient);
		}
	}
}
