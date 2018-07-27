package io.teivah;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplOO {
	public UserA parseUser(final String input) throws ParsingException {
		final String[] split = input.split(";");
		if (split.length != 2) {
			throw new ParsingException("Bad format");
		}

		try {
			final PersonA person = parsePerson(split[0]);
			final PhoneA phone = parsePhone(split[1]);
			return new UserA(person, phone);
		} catch (final Exception e) {
			// Log something
			throw e;
		}
	}

	private PersonA parsePerson(final String input) throws ParsingException {
		final String[] split = input.split(" ");
		if (split.length != 2) {
			throw new ParsingException("Bad format");
		}

		return new PersonA(split[0], split[1]);
	}

	private PhoneA parsePhone(final String input) throws ParsingException {
		final String regex = "^\\+(\\d{2})(\\d{9})$";
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(input);

		if (!matcher.find()) {
			throw new ParsingException("Bad format");
		}

		return new PhoneA(matcher.group(0), matcher.group(1));
	}

	public static void main(final String[] args) {
		try {
			final UserA user = new ImplOO().parseUser("bob ross;+33620220221");
			System.out.println(user.getPhoneNumber().getPhoneNumber());
		} catch (final ParsingException e) {
			// Do something
			e.printStackTrace();
		}
	}
}
