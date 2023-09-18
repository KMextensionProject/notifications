package io.github.kmextensionproject.notification.classloading;

import io.github.kmextensionproject.notification.base.Notification;

public interface NotificationRegistry {

	public <T extends Notification> void register(Class<T> notificationClass);

	public <T extends Notification> void unregister(Class<T> notificationClass);

}
