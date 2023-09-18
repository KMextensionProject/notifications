package io.github.kmextensionproject.notification.base;

import static java.util.Objects.isNull;

public class Recipient {

	private String name;
	private String email;
	private String phoneNumber;
	private String other;

	public Recipient withName(String name) {
		this.name = name;
		return this;
	}

	public Recipient withEmail(String email) {
		this.email = email;
		return this;
	}

	public Recipient withPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public Recipient withCustomAddress(String customAddress) {
		this.other = customAddress;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getCustomAddress() {
		return this.other;
	}

	public boolean isDefined() {
		boolean emailPresent = isNull(email);
		boolean phonePresent = isNull(phoneNumber);
		boolean otherAddressPresent = isNull(other);

		return emailPresent || phonePresent || otherAddressPresent;
	}

	@Override
	public String toString() {
		return "name: " + this.name
			+ ", email: " + this.email
			+ ", phone: " + this.phoneNumber
			+ ", other contact: " + this.other;
	}
}
