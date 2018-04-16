package br.com.mymarvel.character;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Repository;

import br.com.mymarvel.helper.ApplicationAssistance;

@Repository
public class CharacterDAOImpl implements CharacterDAO{
	
	private final String base_url = "http://gateway.marvel.com/v1/public/characters";
	private final String auth_parameters = "ts=1&apikey=399252e317d7557e0a22b326084ab614&hash=bd81c0769851c3c7e91a30dbf738364d"; 
	
	
	@Override
	public String get_characters_name_starts_with(String nameStartsWith) throws IOException {
		
		String nameStartsWithReqParam = "nameStartsWith";
		String requisition_url;
		URL url;
		HttpURLConnection con;
		
		//constroi a URL da API para realizar a busca dos personagens
		requisition_url = this.base_url + "?" + nameStartsWithReqParam + "=" + nameStartsWith + "&" + this.auth_parameters;
		url = new URL(requisition_url);

		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		con.disconnect();	
				
		return ApplicationAssistance.process_foreign_requisition_result(con.getInputStream());
		
	}



}
