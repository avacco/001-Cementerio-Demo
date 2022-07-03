package cl.andres.java.cementerio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.Fallecido;

@Repository
public interface FallecidoRepository extends JpaRepository<Fallecido, Long> {

	@Query(value = "SELECT * FROM fallecido ORDER BY id DESC LIMIT 3", nativeQuery = true)
	List<Fallecido> findLastThree();

	@Query(value = "SELECT DISTINCT * FROM fallecido where nombre iLIKE ?1%", nativeQuery = true)
	List<Fallecido> findByNombre(String nombre);

}
