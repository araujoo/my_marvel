package br.com.mymarvel.comic;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ComicDAO {
	
	public String getComicsByCharsNameStartsWith(String csvCharactersIds) throws IOException;

}
