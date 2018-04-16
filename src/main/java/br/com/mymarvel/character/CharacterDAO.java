package br.com.mymarvel.character;

import java.io.IOException;
import java.util.List;

public interface CharacterDAO {
	public List<Character> get_characters_name_starts_with(String nameStartsWith) throws IOException;
}
