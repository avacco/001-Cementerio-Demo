package cl.andres.java.cementerio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {

	@GetMapping("/")
	public String Index() {
		return "index";
	}
	
	@GetMapping("/contacto")
	public String Contacto() {
		return "contact";
	}
	
	@GetMapping("/noticias")
	public String Noticia() {
		return "post";
	}
	
	// reemplazar
	
	@GetMapping("/mapa")
	public String Mapa() {
		return "index";
	}
	
	@GetMapping("/obituario")
	public String Obituario() {
		return "index";
	}
	
	//
	
	
	
}
