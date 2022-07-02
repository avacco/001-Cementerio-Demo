package cl.andres.java.cementerio.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.model.ImagenFallecido;
import cl.andres.java.cementerio.repository.FallecidoRepository;
import cl.andres.java.cementerio.repository.ImagenFallecidoRepository;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	FallecidoRepository fRepo;
	
	@Autowired
	ImagenFallecidoRepository ifRepo;

	@GetMapping("/")
	public String Index(Fallecido fallecido, Model modelo) {
		// Trae los ultimos 3 registros del obituario
		List<Fallecido> fallecidos = fRepo.findLastThree();
		modelo.addAttribute("fallecidos",fallecidos);
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
	
	@GetMapping("/imgperfil/{id}")
	public ResponseEntity<byte[]> imagenPerfl(@PathVariable("id") Long id) throws SQLException, IOException {
		Optional<ImagenFallecido> imgfallecido = ifRepo.findByOtherId(id);
		byte[] imageBytes = null;
		if(imgfallecido.isPresent()) {
			imageBytes = imgfallecido.get().getImagen();
		}
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	
	// reemplazar
	
	@GetMapping("/mapa")
	public String Mapa() {
		return "redirect:/";
	}
	
	//
	
	
	
}
