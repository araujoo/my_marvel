package br.com.mymarvel.comic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ComicDAO {
	
	public List<Comic> getComicsByCharsNameStartsWith(String csvCharactersIds) throws IOException;

}
