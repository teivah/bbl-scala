package io.teivah;

public class UserA {
	private PersonA person;
	private PhoneA phoneNumber;

	public UserA(final PersonA person, final PhoneA phoneNumber) {
		this.person = person;
		this.phoneNumber = phoneNumber;
	}

	public PersonA getPerson() {
		return person;
	}

	public void setPerson(final PersonA person) {
		this.person = person;
	}

	public PhoneA getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final PhoneA phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
