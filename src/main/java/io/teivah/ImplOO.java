package io.teivah;

import java.util.ArrayList;
import java.util.List;

public class ImplOO {
	public List<PersonA> parsePerson(final List<String> inputs) throws ParsingException {
		final List<PersonA> list = new ArrayList<>();

		for (final String input : inputs) {
			final String[] split = input.split(";");
			if (split.length != 2) {
				throw new ParsingException("Bad format");
			}

			final PersonA p = new PersonA();
			try {
				enrichNameAndLastName(p, split[0]);
				enrichPhone(p, split[1]);
				list.add(p);
			} catch (final Exception e) {
				// Log something
				throw e;
			}
		}

		return list;
	}

	public void enrichNameAndLastName(final PersonA p,
									  final String input) throws ParsingException {
		final String[] split = input.split(" ");
		if (split.length != 2) {
			throw new ParsingException("Bad format");
		}

		p.setName(split[0]);
		p.setLastName(split[1]);
	}

	public void enrichPhone(final PersonA p,
							final String input) throws ParsingException {
		if (input.matches("^0\\d{9}$")) {
			p.setPhone(input);
		} else {
			throw new ParsingException("Bad format");
		}

	}
}
