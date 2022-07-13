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

import cl.andres.java.cementerio.model.Administrador;
import cl.andres.java.cementerio.model.BlogPost;
import cl.andres.java.cementerio.model.Fallecido;
import cl.andres.java.cementerio.model.ImagenFallecido;
import cl.andres.java.cementerio.repository.BlogPostRepository;
import cl.andres.java.cementerio.repository.FallecidoRepository;
import cl.andres.java.cementerio.service.AdministradorService;

@SpringBootApplication
public class CementerioMunicipalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CementerioMunicipalApplication.class, args);
	}
	
	// Crea datos de ejemplo para demostraciones
	@Bean
	public CommandLineRunner datosEjemplo(FallecidoRepository fRepo, BlogPostRepository postRepo, AdministradorService adminService) {
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
					.nombre("Beatriz Carolina Fuentes Ojeda")
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
					.nombre("Carlos Fabian Contreras Barría")
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
					.nombre("Marta Carina Villarroel Araya")
					.fechaNacimiento(LocalDate.of(1967, 9, 14))
					.fechaDefuncion(LocalDate.of(2022, 9, 30))
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
					.nombre("Pedro Pablo Hurtado Sanhueza")
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
		if(postRepo.count() == 0) {
			BlogPost post1 = BlogPost.builder()
					.titulo("Primer post utilizando TinyMCE")
					.subtitulo("Probando un editor de textos HTML para las noticias y tal vez los comentarios.")
					.cuerpo("<h1>Primer post utilizando TinyMCE</h1>\n"
							+ "<p>&nbsp;</p>\n"
							+ "<p>He utilizado un editor HTML Open Source muy curioso y personalizable llamado <a href=\"https://www.tiny.cloud\">TinyMCE</a> para hacer este primer post. Espero implementarlo pronto para crear nuevas noticias y comentarios.</p>\n"
							+ "<p>Lo siguiente en este post ser&aacute; un Lorem Ipsum com&uacute;n y corriente para seguir rellenando.</p>\n"
							+ "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc imperdiet mauris vel odio ultricies pharetra. Morbi elementum, dui quis tristique sagittis, nulla quam iaculis ante, id posuere orci purus ac sem.</p>\n"
							+ "<p>Aenean sit amet nulla vel augue aliquet laoreet vel sit amet est. Integer ac vulputate sem, ut pellentesque nunc. Vestibulum placerat risus ex, vitae sodales mi euismod at. Sed varius, nunc vitae molestie rhoncus, massa leo pharetra ipsum, nec lobortis arcu erat a libero. Donec velit tortor, finibus ac massa in, facilisis auctor sem.</p>\n"
							+ "<p>Curabitur eu lorem ac eros tristique semper et at metus. Sed lacinia ultricies leo vitae rhoncus. Vivamus accumsan pretium convallis.</p>")
					.fechaModificacion(LocalDate.of(2022, 7, 8))
					.fechaPublicacion(LocalDate.of(2022, 7, 8))
					.build();
			postRepo.saveAndFlush(post1);
		}
		if(adminService.contarAdmin() == 0) {
			Administrador admin = Administrador.builder()
					.username("admin")
					.password("123")
					.build()
					;
			adminService.crearAdmin(admin);
		}
		
	};
	}

}
