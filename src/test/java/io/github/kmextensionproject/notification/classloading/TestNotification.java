package io.github.kmextensionproject.notification.classloading;

import java.io.IOException;

import io.github.kmextensionproject.notification.base.Message;
import io.github.kmextensionproject.notification.base.Notification;
import io.github.kmextensionproject.notification.base.Recipient;

public class TestNotification implements Notification {

	@Override
	public void sendNotification(Message message, Recipient recipient) throws IOException {
		// intentionally empty
	}

}
