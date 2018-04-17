package br.com.mymarvel.character;

import java.util.List;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CharacterController {

	@Autowired
	private CharacterService characterService;

	@RequestMapping(value = "/thumbnails/characters", produces = "application/json")
	public List<Character> getCharactersNameStartsWith(@RequestParam("nameStartsWith") String nameStartsWith) throws IOException{
		return characterService.getCharactersNameStartsWith(nameStartsWith);
	}
}
