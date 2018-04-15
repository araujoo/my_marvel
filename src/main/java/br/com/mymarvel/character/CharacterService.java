package br.com.mymarvel.character;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CharacterService {
	
	private List<Character> characters;

	public List<Character> getChars(String name) {
		return Arrays.asList(new Character(name, 00, "123123"));
	}
	
	public void addCharacter(String name, int id, String thumbnail_url)
	{
		this.characters.add( new Character(name, id, thumbnail_url));
	}

}
