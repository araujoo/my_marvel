package br.com.mymarvel.character;

import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

@RestController
public class CharacterController {

	@Autowired
	private CharacterService characterService;

	@RequestMapping(value = "/thumbnails/characters", produces = "application/json" )
	public List<Character> getCharactersNameStartsWith(@RequestParam("nameStartsWith") String nameStartsWith) throws IOException{
		
		return characterService.getCharactersNameStartsWith(nameStartsWith);
	}
	
	

}
