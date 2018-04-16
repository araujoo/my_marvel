package br.com.mymarvel.character;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
		
		List<Character> characters = new LinkedList<>();
		List<Character> buff = new LinkedList<>();
		totalCharacters = ApplicationAssistance.getTotalResults(1, nameStartsWith,null);
		
		if(totalCharacters > 0)
		{
			while( characters.size() < totalCharacters )
			{
				System.out.println("comics size: "+characters.size());
				response = ApplicationAssistance.consumeComicAPI(nameStartsWith, offset);
				buff = ApplicationAssistance.parseJsonCharacterNameStartWith(response);
				characters.addAll(buff);
				offset += 100;
			}			
		}
		
		return characters;
		
		/*
		String nameStartsWithReqParam = "nameStartsWith";
		String requisition_url;
		URL url;
		HttpURLConnection con;
		InputStream inputStream;
		
		//constroi a URL da API para realizar a busca dos personagens
		requisition_url = ApplicationAssistance.base_url + "characters" + "?" + nameStartsWithReqParam + "=" + nameStartsWith + "&" + ApplicationAssistance.auth_parameters;
		url = new URL(requisition_url);
		
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		con.disconnect();

		inputStream = con.getInputStream();
		return ApplicationAssistance.processForeignRequisitionResult(inputStream);
		*/
	}
}
