package user.domain;

import io.github.kmextensionproject.notification.base.Message;
import io.github.kmextensionproject.notification.base.Notification;
import io.github.kmextensionproject.notification.base.NotificationResult;
import io.github.kmextensionproject.notification.base.Recipient;

public class UserTestNotification implements Notification {

	@Override
	public NotificationResult sendNotification(Message message, Recipient recipient) {
		// intentionally empty
		return null;
	}

}
