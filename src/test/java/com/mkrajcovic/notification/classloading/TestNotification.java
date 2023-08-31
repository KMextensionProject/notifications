package com.mkrajcovic.notification.classloading;

import java.io.IOException;

import com.mkrajcovic.notification.base.Message;
import com.mkrajcovic.notification.base.Notification;
import com.mkrajcovic.notification.base.Recipient;

public class TestNotification implements Notification {

	@Override
	public void sendNotification(Message message, Recipient recipient) throws IOException {
		// intentionally empty
	}

}
