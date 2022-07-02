package cl.andres.java.cementerio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.ImagenFallecido;

@Repository
public interface ImagenFallecidoRepository extends JpaRepository<ImagenFallecido, Long> {

	@Query(value = "SELECT * FROM imagen_fallecido WHERE fallecido_id = ?1", nativeQuery = true)
	Optional<ImagenFallecido> findByOtherId(Long fallecido_id);

}
