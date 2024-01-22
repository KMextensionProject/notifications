package io.github.kmextensionproject.notification.base;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

public class Recipient {

	private String name;
	private List<String> email;
	private List<String> phoneNumber;
	private String other;

	public Recipient() {
		email = new ArrayList<>(3);
		phoneNumber = new ArrayList<>(3);
	}

	public Recipient withName(String name) {
		this.name = name;
		return this;
	}

	public Recipient withEmail(String email, String... more) {
		this.email.add(email);
		addProperties(this.email, more);
		return this;
	}

	public Recipient withPhoneNumber(String phoneNumber, String... more) {
		this.phoneNumber.add(phoneNumber);
		addProperties(this.phoneNumber, more);
		return this;
	}

	private void addProperties(List<String> list, String[] values) {
		if (nonNull(values) && values.length != 0) {
			for (String otherPhone : values) {
				list.add(otherPhone);
			}
		}
	}

	/**
	 * @param customAddress
	 *            - may be chat identifier or similar binding property
	 */
	public Recipient withOtherAddress(String customAddress) {
		this.other = customAddress;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getEmail() {
		return unmodifiableList(this.email);
	}

	public List<String> getPhoneNumber() {
		return unmodifiableList(this.phoneNumber);
	}

	public String getOtherAddress() {
		return this.other;
	}

	@Override
	public String toString() {
		return "name: " + this.name
			+ ", email: " + this.email
			+ ", phone: " + this.phoneNumber
			+ ", other contact: " + this.other;
	}
}
