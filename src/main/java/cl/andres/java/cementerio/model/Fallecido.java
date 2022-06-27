package cl.andres.java.cementerio.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Fallecido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Size(min = 9, max = 9)
	@Pattern(regexp = "^(\\d{0,2})\\.?(\\d{3})\\.?(\\d{3})-?(\\d|k)$", message = "RUT invalido")
	private String rut;
	private String nombre1;
	private String nombre2;
	private String apellidoPat;
	private String apellidoMat;
	private String ubicacion;
	private LocalDate fechaNacimiento;
	private LocalDate fechaDefuncion;
	private LocalDate fechaEntierro;
	private LocalDate fechaRelocalizacion;
	
	@OneToMany
	private Set<ImagenFallecido> fotos;
	
	@OneToMany
	private Set<Condolencia> condolencias; 
}
