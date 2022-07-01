package cl.andres.java.cementerio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.repository.FallecidoRepository;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	FallecidoRepository fRepo;

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
	
	@GetMapping("/obituario")
	public String Listado(Fallecido fallecido, Model modelo) {
		List<Fallecido> fallecidos = fRepo.findAll();
		modelo.addAttribute("fallecidos",fallecidos);
		return "listado";
	}
	
	// reemplazar
	
	@GetMapping("/mapa")
	public String Mapa() {
		return "index";
	}
	
	//
	
	
	
}
