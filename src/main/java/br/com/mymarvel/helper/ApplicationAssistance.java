package br.com.mymarvel.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mymarvel.character.CharacterService;
import br.com.mymarvel.comic.Comic;
import br.com.mymarvel.character.Character;


public class ApplicationAssistance {
	
		public static String base_url = "http://gateway.marvel.com/v1/public/";
		public static String auth_parameters = "ts=1&apikey=399252e317d7557e0a22b326084ab614&hash=bd81c0769851c3c7e91a30dbf738364d"; 
	
		public static String processForeignRequisitionResult(InputStream stream) throws IOException
		{
			BufferedReader in;
			String inputLine;
			StringBuffer response = new StringBuffer();
			in = new BufferedReader( new InputStreamReader(stream) );
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		}
		
		public static List<Character> parseJsonCharacterNameStartWith(String str_character_json)
		{
			List<Character> characters = new ArrayList();
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(str_character_json).getAsJsonObject();
			JsonArray result = root.getAsJsonObject("data").getAsJsonArray("results");
			JsonObject character_obj;

			for(int i = 0; i < result.size(); i++ )
			{
				character_obj = result.get(i).getAsJsonObject();
				characters.add( new Character(character_obj.get("name").getAsString(), character_obj.get("id").getAsInt(), character_obj.getAsJsonObject("thumbnail").get("path").getAsString() + "." + character_obj.getAsJsonObject("thumbnail").get("extension").getAsString()));
			}
			return characters;
		}
		
		public static List<Comic> parseJsonComicsCharacterNameStartWith(String str_character_json)
		{
			List<Comic> comics = new ArrayList();
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(str_character_json).getAsJsonObject();
			JsonArray result = root.getAsJsonObject("data").getAsJsonArray("results");
			JsonObject comic_obj;

			System.out.println("count = " + root.getAsJsonObject("data").get("count").getAsString());
			for(int i = 0; i < result.size(); i++ )
			{
				comic_obj = result.get(i).getAsJsonObject();
				comics.add( new Comic(comic_obj.get("title").getAsString(), comic_obj.get("id").getAsInt(), comic_obj.getAsJsonObject("thumbnail").get("path").getAsString() + "." + comic_obj.getAsJsonObject("thumbnail").get("extension").getAsString()));
			}
			return comics;
		}
		
}
