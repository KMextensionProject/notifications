package io.github.mkrajcovic.notification.classloading;

import io.github.mkrajcovic.notification.base.Notification;

public interface NotificationRegistry {

	public <T extends Notification> void register(Class<T> notificationClass);

	public <T extends Notification> void unregister(Class<T> notificationClass);

}
