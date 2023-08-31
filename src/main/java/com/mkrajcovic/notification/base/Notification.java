package com.mkrajcovic.notification.base;

import java.io.IOException;
import java.util.Collection;

/**
 * 
 * @author mkrajcovic
 */
public interface Notification {

	/**
	 *
	 * @param message
	 * @param recipient
	 * @throws IOException
	 */
	public void sendNotification(Message message, Recipient recipient) throws IOException;

	/**
	 *
	 * @param message
	 * @param recipientList
	 * @throws IOException
	 */
	public default void sendNotification(Message message, Collection<Recipient> recipientList) throws IOException {
		for (Recipient recipient : recipientList) {
			sendNotification(message, recipient);
		}
	}
}
