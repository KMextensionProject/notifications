package io.github.kmextensionproject.notification.base;

/**
 *
 * @author mkrajcovic
 */
public class NotificationResult {

	public static enum Status {
		SUCCESS,
		FAILURE
	}

	private Status status;
	private String message;

	public NotificationResult(Status status) {
		this(status, "");
	}

	public NotificationResult(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * 
	 * @return
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
}
