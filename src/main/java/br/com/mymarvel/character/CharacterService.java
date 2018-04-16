package br.com.mymarvel.character;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mymarvel.helper.ApplicationAssistance;

@Service
public class CharacterService {
	
	private List<Character> characters = new ArrayList<>();
	public List<Character> getChars(String name) {
		return Arrays.asList(new Character(name, 00, "123123"));
	}
	
	public void addCharacter(String name, int id, String thumbnail_url)
	{
		Character e = new Character(thumbnail_url, id, name);
		characters.add(e);
	}
	
	public List<Character> getCharacters(String nameStartsWith) throws IOException
	{
		CharacterDAOImpl characterDAOimpl = new CharacterDAOImpl();
		List<Character> characters = new ArrayList<>();
		String str_charactersJSON;
		str_charactersJSON = characterDAOimpl.get_characters_name_starts_with(nameStartsWith);
		System.out.println("getCharacters: "+str_charactersJSON);
		return ApplicationAssistance.parse_json_character(str_charactersJSON);
	}	

}