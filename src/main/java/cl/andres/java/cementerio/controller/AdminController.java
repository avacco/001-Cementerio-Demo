package cl.andres.java.cementerio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String registrarFallecido() {
		return "admin/registro";
	}
	
	@GetMapping("/nuevanoticia")
	public String nuevaNoticia() {
		return "admin/nuevanoticia";
	}
	
}
