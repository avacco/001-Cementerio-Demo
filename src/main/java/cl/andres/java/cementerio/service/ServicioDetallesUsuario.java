package cl.andres.java.cementerio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.andres.java.cementerio.model.Administrador;
import cl.andres.java.cementerio.repository.AdministradorRepository;
import cl.andres.java.cementerio.security.UserSeguridad;

@Service
public class ServicioDetallesUsuario implements UserDetailsService {

	
	@Autowired
	AdministradorRepository administradorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Administrador> adminOpt = administradorRepository.findByUsername(username);
		if(adminOpt.isPresent()) {
			return new UserSeguridad(adminOpt.get());
		}
		throw new UsernameNotFoundException("Usuario no encontrado");
	}
	
	
}
