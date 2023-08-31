package com.mkrajcovic.notification.classloading;

import com.mkrajcovic.notification.base.Notification;

/**
 * 
 * @author mkrajcovic
 */
public interface NotificationRegistry {

	/**
	 *
	 * @param notificationClass
	 */
	public <T extends Notification> void register(Class<T> notificationClass);

	/**
	 *
	 * @param notificationClass
	 */
	public <T extends Notification> void unregister(Class<T> notificationClass);

}
