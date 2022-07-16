package cl.andres.java.cementerio.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Fallecido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
/* Testeo pendiente
	@Pattern(regexp = "^(\\d{0,2})\\.?(\\d{3})\\.?(\\d{3})-?(\\d|k)$", message = "RUT invalido")
*/
	private String rut;
	private String nombre; // incluye nombres y apellidos
	private String ubicacion;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaNacimiento;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaDefuncion;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaEntierro;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaRelocalizacion;
	
	private byte[] imagen;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ImagenFallecido> fotos;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Condolencia> condolencias; 
}
