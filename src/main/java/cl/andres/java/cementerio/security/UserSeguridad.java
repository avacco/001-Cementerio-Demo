package cl.andres.java.cementerio.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cl.andres.java.cementerio.model.Administrador;

public class UserSeguridad implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Administrador administrador;
	
	public UserSeguridad(Administrador administrador) { this.administrador = administrador; }
	

	@Override
	public String getUsername() {
		if(administrador != null) return administrador.getUsername();
		return null;
	}

	@Override
	public String getPassword() {
		if(administrador != null) return administrador.getPassword();
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(administrador != null) return List.of(new SimpleGrantedAuthority("ADMINISTRADOR"));
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

}
