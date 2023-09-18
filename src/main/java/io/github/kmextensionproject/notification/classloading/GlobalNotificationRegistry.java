package io.github.kmextensionproject.notification.classloading;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.kmextensionproject.notification.base.Notification;

/**
 * This class represents a single global registry for user defined direct
 * notification implementations.
 *
 * @author mkrajcovic
 */
public final class GlobalNotificationRegistry implements NotificationRegistry {

	private static final GlobalNotificationRegistry INSTANCE = new GlobalNotificationRegistry();
	private final Set<Class<? extends Notification>> classRegistry = new HashSet<>(3);

	private GlobalNotificationRegistry() { }

	/**
	 * @return global registry instance
	 */
	public static final GlobalNotificationRegistry getInstance() {
		return INSTANCE;
	}

	/**
	 * Registers {@link Notification} implementor for loading by the
	 * {@link NotificationLoader}
	 */
	@Override
	public <T extends Notification> void register(Class<T> notificationClass) {
		if (nonNull(notificationClass)) {
			classRegistry.add(notificationClass);
		}
	}

	/**
	 * Allows unregistering the {@link Notification} implementors from the
	 * global registry.<br>
	 * If this method is called after the
	 * {@link NotificationLoader#loadRegisteredNotifications()}, it must be
	 * called again for reload.<br>
	 */
	@Override
	public <T extends Notification> void unregister(Class<T> notificationClass) {
		classRegistry.remove(notificationClass);
	}

	/**
	 * This method was intended to be used internally by the
	 * {@link NotificationLoader} only.
	 *
	 * @return the list of registered {@link Notification} class objects.
	 */
	List<Class<? extends Notification>> getRegisteredClasses() {
		return new ArrayList<>(classRegistry);
	}

}
