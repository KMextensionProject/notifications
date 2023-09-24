package io.github.kmextensionproject.notification.base;

/**
 * Represents basic result after processing the notification.
 * @author mkrajcovic
 */
public class NotificationResult {

	public static enum Status {
		/**
		 * Indicates that a notification has been successfully processed
		 */
		SUCCESS,
		/**
		 * Indicates that a notification has not been processed properly and has
		 * probably ended with some unexpected failure.
		 */
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
	 * @return one of {@link Status#SUCCESS} or {@link Status#FAILURE}
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @return description message of this result
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the cause of the failure if there is one, otherwise {@code null}
	 */
	public Exception getFailureCause() {
		return this.failureCause;
	}

	/**
	 * @return result of success with empty description message
	 */
	public static NotificationResult success() {
		return success("");
	}

	/**
	 * @param message description of success
	 * @return result of success
	 */
	public static NotificationResult success(String message) {
		return new NotificationResult(Status.SUCCESS, message, null);
	}

	/**
	 * @param message description of the failure
	 * @return result of failure
	 */
	public static NotificationResult failure(String message) {
		return failure(message, null);
	}

	/**
	 * @param message
	 * @param causeOfFailure
	 * @return result of failure with its cause
	 */
	public static NotificationResult failure(String message, Exception causeOfFailure) {
		return new NotificationResult(Status.FAILURE, message, causeOfFailure);
	}
}
