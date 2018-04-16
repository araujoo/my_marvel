package br.com.mymarvel.character;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mymarvel.helper.ApplicationAssistance;

@Service
public class CharacterService {
	
	@Autowired
	private CharacterDAOImpl characterDAOImpl;
	
	public List<Character> getCharactersNameStartsWith(String nameStartsWith) throws IOException
	{
		String str_charactersJSON;
		
		if(nameStartsWith.isEmpty())
		{
			return (new ArrayList<>());
		}
		
		str_charactersJSON = characterDAOImpl.get_characters_name_starts_with(nameStartsWith);
		return ApplicationAssistance.parseJsonCharacterNameStartWith(str_charactersJSON);
	}
}