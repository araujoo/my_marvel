package br.com.mymarvel.character;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.events.Characters;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Repository;

import br.com.mymarvel.comic.Comic;
import br.com.mymarvel.helper.ApplicationAssistance;

@Repository
public class CharacterDAOImpl implements CharacterDAO{
	
	@Override
	public List<Character> get_characters_name_starts_with(String nameStartsWith) throws IOException {
		
		
		String response;
		int totalCharacters = 0;
		int offset = 0;		
		
		List<Character> characters = new ArrayList<>();
		List<Character> buff = new ArrayList<>();
		totalCharacters = ApplicationAssistance.getTotalResults(1, nameStartsWith,null);
		
		if(totalCharacters > 0)
		{
			while( characters.size() < totalCharacters )
			{
				response = ApplicationAssistance.consumeCharacterAPI(nameStartsWith, offset);
				buff = ApplicationAssistance.parseJsonCharacterNameStartWith(response);
				characters.addAll(buff);
				offset += 100;
			}			
		}
		
		return characters;
	}
}
