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
	private Exception failureCause;

	private NotificationResult(Status status, String message, Exception cause) {
		this.status = status;
		this.message = message;
		this.failureCause = cause;
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

	/**
	 * 
	 * @return
	 */
	public Exception getFailureCause() {
		return this.failureCause;
	}

	/**
	 *
	 * @return
	 */
	public static NotificationResult success() {
		return success("");
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	public static NotificationResult success(String message) {
		return new NotificationResult(Status.SUCCESS, message, null);
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	public static NotificationResult failure(String message) {
		return failure(message, null);
	}

	/**
	 * 
	 * @param message
	 * @param causeOfFailure
	 * @return
	 */
	public static NotificationResult failure(String message, Exception causeOfFailure) {
		return new NotificationResult(Status.FAILURE, message, causeOfFailure);
	}
}
