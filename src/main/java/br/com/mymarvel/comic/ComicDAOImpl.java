package br.com.mymarvel.comic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Repository;

import br.com.mymarvel.helper.ApplicationAssistance;

@Repository
public class ComicDAOImpl implements ComicDAO {

	@Override
	public String getComicsByCharsNameStartsWith(String charactersIds) throws IOException {

		String charactersParam = "characters";
		String requisition_url;
		URL url;
		HttpURLConnection con;
		
		//constroi a URL da API para realizar a busca dos personagens
		requisition_url = ApplicationAssistance.base_url + "comics" + "?" + charactersParam + "=" + charactersIds + "&" +ApplicationAssistance.auth_parameters;
		url = new URL(requisition_url);
	
		//url = new URL("https://gateway.marvel.com/v1/public/comics?characters=1011087,1009268&ts=1&apikey=399252e317d7557e0a22b326084ab614&hash=bd81c0769851c3c7e91a30dbf738364d&limit=999");
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		con.disconnect();
		return ApplicationAssistance.processForeignRequisitionResult(con.getInputStream());
	}

}
