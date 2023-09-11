package com.mkrajcovic.notification.classloading;

import com.mkrajcovic.notification.base.Notification;

public interface NotificationRegistry {

	public <T extends Notification> void register(Class<T> notificationClass);

	public <T extends Notification> void unregister(Class<T> notificationClass);

}
