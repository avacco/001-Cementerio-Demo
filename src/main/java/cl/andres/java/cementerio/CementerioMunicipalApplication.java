package cl.andres.java.cementerio;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.repository.FallecidoRepository;

@SpringBootApplication
public class CementerioMunicipalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CementerioMunicipalApplication.class, args);
	}
	
	// Crea datos de ejemplo para demostraciones
	@Bean
	public CommandLineRunner datosEjemplo(FallecidoRepository fRepo) {
	return args ->{
		if(fRepo.count() == 0) {
			Fallecido fallecido = Fallecido.builder()
					.rut("12.345.678-9")
					.nombre1("Beatriz")
					.nombre2("Carolina")
					.apellidoPat("Fuentes")
					.apellidoMat("Ojeda")
					.fechaNacimiento(LocalDate.of(1971, 3, 30))
					.fechaDefuncion(LocalDate.of(2020, 5, 10))
					.fechaEntierro(LocalDateTime.of(2020, 5, 10, 14, 30))
					.fechaRelocalizacion(null)
					.ubicacion("No disponible")
					.build();
			fRepo.saveAndFlush(fallecido);
		}
	};
	}

}
