package io.github.mkrajcovic.notification.base;

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
	public void sendNotification(Message message, Recipient recipient) throws IOException {
		System.out.println("Console Notification: " + message.getSubject() + " - " + message.getBody()); // NOSONAR
	}

}
