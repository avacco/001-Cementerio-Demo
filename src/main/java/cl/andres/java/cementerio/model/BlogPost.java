package cl.andres.java.cementerio.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	
	// Utilizando Postgres, el tipo de columna TEXT no tiene limite de caracteres. Ideal para posts de blog.
	@Column(columnDefinition = "TEXT")
	private String cuerpo;
	
	private LocalDate fechaPublicacion;
	private LocalDate fechaModificacion;
	
}
