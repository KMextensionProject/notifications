package user.domain;

import java.io.IOException;

import io.github.kmextensionproject.notification.base.Message;
import io.github.kmextensionproject.notification.base.Notification;
import io.github.kmextensionproject.notification.base.Recipient;

public class UserTestNotification implements Notification {

	@Override
	public void sendNotification(Message message, Recipient recipient) throws IOException {
		// intentionally empty
	}

}
