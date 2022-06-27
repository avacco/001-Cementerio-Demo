package cl.andres.java.cementerio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class ConfiguracionSecurity {

	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filtro(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(authorize -> authorize
					.mvcMatchers("/").permitAll()
					.mvcMatchers("/mapa").permitAll()
					.mvcMatchers("/contacto").permitAll()
					.mvcMatchers("/obituario").permitAll()
					.mvcMatchers("/noticias").permitAll()
					.anyRequest().authenticated()
					
			)
			.formLogin(form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/",true)
						.permitAll()
			)
			.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
					
			)
		;
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { 
		return (web) -> web.ignoring().antMatchers("/img/**","/css/**","/js/**","/assets/**","/imagen/**");
	}
	
}
