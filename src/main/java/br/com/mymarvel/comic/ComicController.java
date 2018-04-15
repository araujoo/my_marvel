package br.com.mymarvel.comic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicController {
	
	@RequestMapping(value  = "/comic", method = RequestMethod.GET)
	public String hello(@RequestParam("name") String name)
	{
		return "hello " + name;
	}
}
