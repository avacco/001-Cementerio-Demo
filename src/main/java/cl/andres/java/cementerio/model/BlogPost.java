package cl.andres.java.cementerio.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String titulo;
	
	@Column
	private String subtitulo;
	
	// Utilizando Postgres, el tipo de columna TEXT no tiene limite de caracteres. Ideal para posts de blog.
	@Column(columnDefinition = "TEXT")
	private String cuerpo;
	
	@Column
	private LocalDate fechaPublicacion;
	
	@Column
	private LocalDate fechaModificacion;
	
}
