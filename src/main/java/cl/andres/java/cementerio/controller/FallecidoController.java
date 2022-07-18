package cl.andres.java.cementerio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.repository.FallecidoRepository;

@Controller
@RequestMapping("/fallecido")
public class FallecidoController {

	@Autowired
	FallecidoRepository fRepo;
	
	@RequestMapping("/modal/{id}")
	public String modal(@PathVariable("id") Long id, Model modelo) {
		Optional<Fallecido> fallecido = fRepo.findById(id);
		modelo.addAttribute("fallecido",fallecido.get());
		return "include/modal :: modalContents";
	}
}
