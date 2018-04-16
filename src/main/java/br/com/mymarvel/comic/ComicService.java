package br.com.mymarvel.comic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mymarvel.character.CharacterDAOImpl;
import br.com.mymarvel.character.Character;
import br.com.mymarvel.helper.ApplicationAssistance;

@Service		
public class ComicService {
	
	@Autowired
	private ComicDAOImpl comicDAOImpl;
	
	@Autowired
	private CharacterDAOImpl characterDAOImpl;
	
	public List<Comic> getComicsByCharsNameStartsWith(String nameStartsWith) throws IOException
	{
		List<Comic> comics = new ArrayList<>();
		List<Character> characters = new ArrayList<>();
		String strComicsJson="";
		String strCharactersJson="";
		String characterIds="";
		
		//recupera o id dos personagens
		strCharactersJson = characterDAOImpl.get_characters_name_starts_with(nameStartsWith);
		characters = ApplicationAssistance.parseJsonCharacterNameStartWith(strCharactersJson);
		
		//monta a lista com os ids dos usuarios
		for(Character c : characters)
		{
			characterIds = characterIds + "," + c.getId(); 
		}
		
		//remover a primeira virgula
		characterIds = characterIds.substring(1);
		
		strComicsJson = comicDAOImpl.getComicsByCharsNameStartsWith(characterIds);		
		return ApplicationAssistance.parseJsonComicsCharacterNameStartWith(strComicsJson);
		
	}
}
