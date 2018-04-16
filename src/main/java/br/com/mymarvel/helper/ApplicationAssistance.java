package br.com.mymarvel.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.mymarvel.character.CharacterService;
import br.com.mymarvel.comic.Comic;
import br.com.mymarvel.character.Character;


public class ApplicationAssistance {
	
		public static String base_url = "http://gateway.marvel.com/v1/public/";
		public static String auth_parameters = "ts=1&apikey=399252e317d7557e0a22b326084ab614&hash=bd81c0769851c3c7e91a30dbf738364d"; 
		private static int limit = 100; 
		
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
		
		public static List<Character> parseJsonCharacterNameStartWith(String strCharacterJson)
		{
			List<Character> characters = new ArrayList();
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(strCharacterJson).getAsJsonObject();
			JsonArray result = root.getAsJsonObject("data").getAsJsonArray("results");
			JsonObject character_obj;

			for(int i = 0; i < result.size(); i++ )
			{
				character_obj = result.get(i).getAsJsonObject();
				characters.add( new Character(character_obj.get("name").getAsString(), character_obj.get("id").getAsInt(), character_obj.getAsJsonObject("thumbnail").get("path").getAsString() + "." + character_obj.getAsJsonObject("thumbnail").get("extension").getAsString()));
			}
			return characters;
		}
		
		public static List<Comic> parseJsonComicsCharacterNameStartWith(String strCharacterJson)
		{
			List<Comic> comics = new LinkedList<>();
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(strCharacterJson).getAsJsonObject();
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
		
		public static String consumeComicAPI(String charactersIds, int offset) throws IOException
		{
			String requisition_url;
			InputStream inputStream;
			
			//constroi a URL da API para realizar a busca dos personagens
			requisition_url = ApplicationAssistance.buildComicApiUrl(charactersIds);
			requisition_url+= "&limit=" + ApplicationAssistance.limit + "&offset=" + offset;
			
			//consome o servico
			System.out.println("url= " + requisition_url);
			inputStream = ApplicationAssistance.doConsumeApi(requisition_url);
			
			return ApplicationAssistance.processForeignRequisitionResult(inputStream);
		}
		
		public static String consumeCharacterAPI(String nameStartsWith, int offset) throws IOException
		{
			String requisition_url;
			InputStream inputStream;
			
			//constroi a URL da API para realizar a busca dos personagens
			requisition_url = ApplicationAssistance.buildCharacterApiUrl(nameStartsWith);
			requisition_url+= "&limit=" + ApplicationAssistance.limit + "&offset=" + offset;
			
			//consome o servico
			System.out.println("url= " + requisition_url);
			inputStream = ApplicationAssistance.doConsumeApi(requisition_url);
			
			return ApplicationAssistance.processForeignRequisitionResult(inputStream);
		}
			
		
		public static int getTotalResults(int scenario, String nameStartsWith, String charactersIds) throws IOException
		{
			String strResponse = null;
			
			//constroi a URL da API para realizar a busca dos personagens
			//requisition_url = ApplicationAssistance.base_url + "comics?characters=" + charactersIds + "&" + ApplicationAssistance.auth_parameters+"&limit=100&offset="+ offset;
			
			switch (scenario) {
			
			//1 - character
			case 1:
				strResponse = ApplicationAssistance.consumeCharacterAPI(nameStartsWith, 0);
				break;
				
			//2 - comic
			case 2:
				strResponse = ApplicationAssistance.consumeComicAPI(charactersIds, 0);
				break;
				
			default:
				break;
			}
			
			//strResponse = ApplicationAssistance.processForeignRequisitionResult(inputStream);
			return ApplicationAssistance.parseTotalRecords(strResponse); 
		}
		
		
		public static int parseTotalRecords(String response)
		{
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(response).getAsJsonObject();
			return root.getAsJsonObject("data").get("total").getAsInt();
		}
		
		public static String buildComicApiUrl(String charactersIds)
		{
			return ApplicationAssistance.base_url + "comics?characters=" + charactersIds + "&" + ApplicationAssistance.auth_parameters; 
		}

		public static String buildCharacterApiUrl(String nameStartsWith)
		{
			return ApplicationAssistance.base_url + "characters?nameStartsWith=" + nameStartsWith + "&" + ApplicationAssistance.auth_parameters; 
		}
		
		public static InputStream doConsumeApi(String requisition_url) throws IOException
		{
			URL url;
			HttpURLConnection con;
			InputStream inputStream;
			
			url = new URL(requisition_url);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			con.disconnect();

			inputStream = con.getInputStream();
			return inputStream;
		}
		
}
