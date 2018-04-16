package br.com.mymarvel.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mymarvel.character.CharacterService;
import br.com.mymarvel.character.Character;


public class ApplicationAssistance {

		public static String process_foreign_requisition_result(InputStream stream) throws IOException
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
		
		public static List<Character> parse_json_character(String str_character_json)
		{
			List<Character> characters = new ArrayList();
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(str_character_json).getAsJsonObject();
			JsonArray result = root.getAsJsonObject("data").getAsJsonArray("results");
			JsonObject character_obj;

			for(int i = 0; i < result.size(); i++ )
			{
				character_obj = result.get(i).getAsJsonObject();
				System.out.println("nome do personagem: " + character_obj.get("name").getAsString());
				
				characters.add( new Character(character_obj.get("name").getAsString(), character_obj.get("id").getAsInt(), character_obj.getAsJsonObject("thumbnail").get("path").getAsString() + "." + character_obj.getAsJsonObject("thumbnail").get("extension").getAsString()));
			}
			
			return characters;
		}
		
		
		
		
}
