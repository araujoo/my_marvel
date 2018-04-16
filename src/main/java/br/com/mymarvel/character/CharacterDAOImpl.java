package br.com.mymarvel.character;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Repository;

import br.com.mymarvel.helper.ApplicationAssistance;

@Repository
public class CharacterDAOImpl implements CharacterDAO{
	
	@Override
	public String get_characters_name_starts_with(String nameStartsWith) throws IOException {
		
		String nameStartsWithReqParam = "nameStartsWith";
		String requisition_url;
		URL url;
		HttpURLConnection con;
		
		//constroi a URL da API para realizar a busca dos personagens
		requisition_url = ApplicationAssistance.base_url + "characters" + "?" + nameStartsWithReqParam + "=" + nameStartsWith + "&" + ApplicationAssistance.auth_parameters;
		url = new URL(requisition_url);

		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		con.disconnect();	
				
		return ApplicationAssistance.processForeignRequisitionResult(con.getInputStream());	
	}

}
