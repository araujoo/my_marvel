package br.com.mymarvel.comic;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mymarvel.helper.ApplicationAssistance;

@Repository
public class ComicDAOImpl implements ComicDAO {

	@Override
	public List<Comic> getComicsByCharsNameStartsWith(String charactersIds) throws IOException {
		
		String response;
		int totalComics = 0;
		int offset = 0;
		List<Comic> comics = new LinkedList<>();
		List<Comic> buff = new LinkedList<>();
		totalComics = ApplicationAssistance.getTotalResults(2, null, charactersIds);
		
		if(totalComics > 0)
		{
			while( comics.size() < totalComics )
			{
				System.out.println("comics size: "+comics.size());
				response = ApplicationAssistance.consumeComicAPI(charactersIds, offset);
				buff = ApplicationAssistance.parseJsonComicsCharacterNameStartWith(response);
				comics.addAll(buff);
				offset += 100;
			}			
		}
		return comics;
	}

}
