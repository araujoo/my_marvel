package br.com.mymarvel.character;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CharacterService {

	public List<Character> getChars(String name) {
		return Arrays.asList(new Character(name, 00));
	}

}
