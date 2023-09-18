package io.github.mkrajcovic.notification.classloading;

import java.io.IOException;

import io.github.mkrajcovic.notification.base.Message;
import io.github.mkrajcovic.notification.base.Notification;
import io.github.mkrajcovic.notification.base.Recipient;

public class TestNotification implements Notification {

	@Override
	public void sendNotification(Message message, Recipient recipient) throws IOException {
		// intentionally empty
	}

}
