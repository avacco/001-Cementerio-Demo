package cl.andres.java.cementerio;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.model.ImagenFallecido;
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
			
			// Inicializa lista de imagenes para el album del fallecido
			List<ImagenFallecido> set1 = new ArrayList<>();
			List<ImagenFallecido> set2 = new ArrayList<>();
			List<ImagenFallecido> set3 = new ArrayList<>();
			List<ImagenFallecido> set4 = new ArrayList<>();
			
			// Crea un perfil de ejemplo
			Fallecido fallecido1 = Fallecido.builder()
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
			
			// Crea el objeto Imagen para el album
			ImagenFallecido perfil1 = ImagenFallecido.builder()
					.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/assets/img/persona2.jpg")))
					.descripcion("Imagen de perfil")
					.fallecido(fallecido1)
					.build();
			
			// Añade la imagen al album
			set1.add(perfil1);
			
			// Relaciona el album con el perfil
			fallecido1.setFotos(set1);
			
			
			// Repite el proceso
			Fallecido fallecido2 = Fallecido.builder()
					.rut("9.876.543-2")
					.nombre1("Carlos")
					.nombre2("Fabian")
					.apellidoPat("Contreras")
					.apellidoMat("Barría")
					.fechaNacimiento(LocalDate.of(1969, 3, 24))
					.fechaDefuncion(LocalDate.of(2022, 1, 12))
					.fechaEntierro(LocalDateTime.of(2022, 1, 14, 11, 00))
					.fechaRelocalizacion(null)
					.ubicacion("No disponible")
					.build();
			
			ImagenFallecido perfil2 = ImagenFallecido.builder()
					.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/assets/img/persona1.jpg")))
					.descripcion("Imagen de perfil")
					.fallecido(fallecido2)
					.build();
			
			set2.add(perfil2);
			fallecido2.setFotos(set2);
			
			// Repite el proceso
			Fallecido fallecido3 = Fallecido.builder()
					.rut("8.531.883-K")
					.nombre1("Marta")
					.nombre2("Carina")
					.apellidoPat("Villarroel")
					.apellidoMat("Araya")
					.fechaNacimiento(LocalDate.of(1967, 9, 14))
					.fechaDefuncion(LocalDate.of(2021, 9, 30))
					.fechaEntierro(LocalDateTime.of(2021, 10, 2, 13, 00))
					.fechaRelocalizacion(null)
					.ubicacion("No disponible")
					.build();
			
			ImagenFallecido perfil3 = ImagenFallecido.builder()
					.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/assets/img/persona3.jpg")))
					.descripcion("Imagen de perfil")
					.fallecido(fallecido3)
					.build();
			
			set3.add(perfil3);
			fallecido3.setFotos(set3);
			
			// Repite el proceso
			Fallecido fallecido4 = Fallecido.builder()
					.rut("7.972.123-3")
					.nombre1("Pedro")
					.nombre2("Pablo")
					.apellidoPat("Hurtado")
					.apellidoMat("Sanhueza")
					.fechaNacimiento(LocalDate.of(1957, 6, 11))
					.fechaDefuncion(LocalDate.of(2022, 2, 22))
					.fechaEntierro(LocalDateTime.of(2022, 2, 27, 9, 00))
					.fechaRelocalizacion(null)
					.ubicacion("No disponible")
					.build();
			
			ImagenFallecido perfil4 = ImagenFallecido.builder()
					.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/assets/img/persona4.jpg")))
					.descripcion("Imagen de perfil")
					.fallecido(fallecido4)
					.build();
			
			set4.add(perfil4);
			fallecido4.setFotos(set4);

			
			// Una vez terminados los albumes y perfiles, los guarda a la bd
			fRepo.save(fallecido1);
			fRepo.save(fallecido2);
			fRepo.save(fallecido3);
			fRepo.saveAndFlush(fallecido4);
		}
	};
	}

}
