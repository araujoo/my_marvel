package br.com.mymarvel.character;

import java.io.IOException;

public interface CharacterDAO {
	public String get_characters_name_starts_with(String nameStartsWith) throws IOException;
}
