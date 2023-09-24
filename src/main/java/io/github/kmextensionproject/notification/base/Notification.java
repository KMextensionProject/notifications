package io.github.kmextensionproject.notification.base;

import static io.github.kmextensionproject.notification.base.NotificationResult.success;
import static io.github.kmextensionproject.notification.base.NotificationResult.Status.FAILURE;

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
	 * @return result of processing this notification
	 */
	public NotificationResult sendNotification(Message message, Recipient recipient);

	/**
	 * Sends one notification message to multiple recipients.
	 * <p>
	 * This method returns successful result object if the message is successfully
	 * sent to every recipient, otherwise the failure object is returned containing
	 * first occurence of the failure.
	 *
	 * @param message       - what to be sent
	 * @param recipientList - which recipients to send the message to
	 * @return result of processing this notification
	 */
	public default NotificationResult sendNotification(Message message, Collection<Recipient> recipientList) {
		NotificationResult result;
		for (Recipient recipient : recipientList) {
			result = sendNotification(message, recipient);
			if (FAILURE.equals(result.getStatus())) {
				return result;
			}
		}
		return success();
	}
}
