package br.com.mymarvel.comic;

import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicController {
	
	@Autowired
	private ComicService comicService;
	
	@RequestMapping(value  = "/thumbnails/comics", produces = "application/json")
	public List<Comic> hello(@RequestParam("characterNameStartsWith") String characterNameStartsWith) throws IOException
	{
		return comicService.getComicsByCharsNameStartsWith(characterNameStartsWith);
	}
}
