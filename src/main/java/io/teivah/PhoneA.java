package io.teivah;

public class PhoneA {
	private String indicative;
	private String phoneNumber;

	public PhoneA(final String indicative, final String phoneNumber) {
		this.indicative = indicative;
		this.phoneNumber = phoneNumber;
	}

	public String getIndicative() {
		return indicative;
	}

	public void setIndicative(final String indicative) {
		this.indicative = indicative;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
