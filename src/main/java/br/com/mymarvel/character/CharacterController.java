package br.com.mymarvel.character;

import java.util.List;
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

@RestController
public class CharacterController {

	@Autowired
	private CharacterService characterService;

	@RequestMapping(value = "/getChars", method = RequestMethod.GET)
	public List<Character> getChars(@RequestParam("name") String name) {
		return characterService.getChars(name);
	}

	@RequestMapping(value = "/teste", produces = "application/json")
	public String do_requisition() throws IOException{
		URL url = new URL("http://gateway.marvel.com/v1/public/characters?nameStartsWith=dead&ts=1&apikey=399252e317d7557e0a22b326084ab614&hash=bd81c0769851c3c7e91a30dbf738364d");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8080/getChars?name=oberdan");
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()) );
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		con.disconnect();

		//print result
		System.out.println(response.toString());
		return response.toString();
	}

}
