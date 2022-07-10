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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.andres.java.cementerio.model.BlogPost;
import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.model.ImagenFallecido;
import cl.andres.java.cementerio.repository.BlogPostRepository;
import cl.andres.java.cementerio.repository.FallecidoRepository;
import cl.andres.java.cementerio.repository.ImagenFallecidoRepository;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	FallecidoRepository fRepo;
	
	@Autowired
	ImagenFallecidoRepository ifRepo;

	@Autowired
	BlogPostRepository postRepo;
	
	@GetMapping("/")
	public String Index(Fallecido fallecido, BlogPost blogPost, Model modelo) {
		// Trae los ultimos 3 registros del obituario y de las noticias
		List<Fallecido> fallecidos = fRepo.findLastThree();
		List<BlogPost> posts = postRepo.findAll(); // TODO: cambiar a LastThree tambien
		modelo.addAttribute("fallecidos",fallecidos);
		modelo.addAttribute("posts",posts);
		return "index";
	}
	
	@GetMapping("/contacto")
	public String Contacto() {
		return "contact";
	}
	
	@GetMapping("/noticias")
	public String Noticia() {
		return "noticias";
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
	
	
	@RequestMapping("/buscar")
	public String Buscar(Model modelo, @RequestParam String nombre) {
		List<Fallecido> fallecidos = fRepo.findByNombre(nombre);
		modelo.addAttribute("fallecidos",fallecidos);
		System.err.println(fallecidos);
		return "listado";
	}
	
	@GetMapping("/noticias/{id}")
	public String getNoticia(@PathVariable("id") Long id, Model modelo, BlogPost blogPost) {
		Optional<BlogPost> post = postRepo.findById(id);
		if(post.isPresent()) {
			modelo.addAttribute("post",post.get());
			return "post";
		}
		return "redirect:/"; // Redirecciona a la raiz si no encuentra el post pedido
	}
	
	
	// TODO: Investigar sobre mapas y coordenadas
	
	@GetMapping("/mapa")
	public String Mapa() {
		return "redirect:/";
	}
	
	//
	
	
	
}
