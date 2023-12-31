package io.github.kmextensionproject.notification.classloading;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.kmextensionproject.notification.base.Notification;
import io.github.kmextensionproject.notification.base.StandardOutputNotification;
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

		assertFalse(notifications.stream()
			.map(Object::getClass)
			.anyMatch(e -> e.equals(NotNotificationImplementor.class) || e.equals(StandardOutputNotification.class)));
	}

}
