package com.mkrajcovic.notification.classloading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.mkrajcovic.notification.base.Notification;

import user.domain.UserTestNotification;

class NotificationLoaderTest {

	@Test
	void findRegisteredImplementators() {

		NotificationRegistry registry = GlobalNotificationRegistry.getInstance();
		registry.register(UserTestNotification.class);

		List<Notification> notifications = NotificationLoader.loadRegisteredNotifications();

		/*
		 * TestNotification is a Registered Notification implementor and should
		 * be loaded first while the UserTestNotification is user defined and
		 * explicitly put into the registry, it should be loaded as last
		 *
		 * the NotNotificationImplementor is neither, so it should not get loaded
		 */
		assertEquals(2, notifications.size());
		assertEquals(TestNotification.class, notifications.get(0).getClass());
		assertEquals(UserTestNotification.class, notifications.get(1).getClass());
		assertFalse(notifications.stream().anyMatch(e -> e.getClass().equals(NotNotificationImplementor.class)));

	}

}
