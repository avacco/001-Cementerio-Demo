package cl.andres.java.cementerio.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cl.andres.java.cementerio.model.BlogPost;
import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.repository.BlogPostRepository;
import cl.andres.java.cementerio.repository.FallecidoRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	FallecidoRepository fRepo;
	
	@Autowired
	BlogPostRepository bpRepo;

	@GetMapping("/index")
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return "admin/login";
        }
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/listanoticias")
	public String listaNoticias(BlogPost noticia, Model modelo) {
		List<BlogPost> noticias = bpRepo.findAll();
		modelo.addAttribute("noticias",noticias);
		return "admin/listanoticias";
	}
	
	@GetMapping("/listafallecidos")
	public String listaFallecidos(Fallecido fallecido, Model modelo) {
		List<Fallecido> fallecidos = fRepo.findAll();
		modelo.addAttribute("fallecidos",fallecidos);
		return "admin/listafallecidos";
	}
	
	@GetMapping("/registrarfallecido")
	public String registrarFallecido(Fallecido fallecido) {
		return "admin/registrarfallecido";
	}
	
	@GetMapping("/nuevanoticia")
	public String nuevaNoticia() {
		return "admin/nuevanoticia";
	}
	
	@GetMapping("/editarFallecido/{fallecidoId}")
	public String editarFallecido(@PathVariable(name = "fallecidoId") Fallecido fallecido, Model modelo) {
		modelo.addAttribute("fallecido",fallecido);
		return "admin/registrarfallecido";
	}
	
	@GetMapping("/eliminarFallecido/{fallecidoId}")
	public String eliminarFallecido(@PathVariable(name = "fallecidoId") Long id) {
		fRepo.deleteById(id);
		return "redirect:/admin/index";
	}
	
	@PostMapping("/procesarFallecido")
	public String procesarFallecido(@Valid Fallecido fallecido, BindingResult validacion,
			@RequestParam("image") MultipartFile imagen,
			@RequestParam(value = "fechaNacimiento", defaultValue = "9999-12-30") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento , 
			@RequestParam(value = "fechaDefuncion", defaultValue = "9999-12-30") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDefuncion,
			@RequestParam(value = "fechaRelocalizacion", defaultValue = "9999-12-30") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaRelocalizacion,
			@RequestParam(value = "fechaEntierro", defaultValue = "9999-12-30T23:59") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaEntierro	
			) throws IOException {
		if (validacion.hasErrors()) return "admin/registrarfallecido";
		
		// Toma la imagen entregada, extrae los bytes.
		if(imagen.getSize() != 0) {
			byte[] contenidoImagen = imagen.getBytes();
			
		// Asigna los bytes de la imagen y guarda el objeto
			fallecido.setImagen(contenidoImagen);
			
		} else if(fallecido.getId() != null) {
		// En casos de update, si no se cambia la imagen, toma la que tenia puesta predeterminada.
			fallecido.setImagen(fRepo.findById(fallecido.getId()).get().getImagen());
			
		} else {
		// Devuelve a la pagina de registro si no hay imagen, y no se trata de un update.
			System.err.println("No se añadió imagen");
			return "admin/registrarfallecido";
		}
	
		// Guarda el objeto a la BD
		fRepo.saveAndFlush(fallecido);

		return "redirect:/admin/index";
	}
	
}
