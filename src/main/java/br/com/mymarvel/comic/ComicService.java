package br.com.mymarvel.comic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mymarvel.character.CharacterDAO;
import br.com.mymarvel.character.Character;

@Service		
public class ComicService {
	
	@Autowired
	private ComicDAO comicDAO;
	
	@Autowired
	private CharacterDAO characterDAO;
	
	public List<Comic> getComicsByCharsNameStartsWith(String nameStartsWith) throws IOException
	{
		List<Comic> list = new ArrayList<>();
		List<Character> characters = new ArrayList<>();
		String strComicsJson="";
		String strCharactersJson="";
		String characterIds="";
		
		if(nameStartsWith.isEmpty())
		{
			return (new ArrayList<>());
		}
		
		//recupera o id dos personagens
		characters = characterDAO.get_characters_name_starts_with(nameStartsWith);
		
		//monta a lista com os ids dos usuarios
		for(Character c : characters)
		{
			characterIds = characterIds + "," + c.getId(); 
		}
		
		//remover a primeira virgula
		if(characterIds.isEmpty())
		{
			//caso nao tenha sido possivel montar esta lista, retornar json vazio
			return (new ArrayList<>());
		}
		characterIds = characterIds.substring(1);
		
		list = comicDAO.getComicsByCharsNameStartsWith(characterIds);
		return list;

	}
}
