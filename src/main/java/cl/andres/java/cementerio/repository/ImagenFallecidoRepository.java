package cl.andres.java.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.ImagenFallecido;

@Repository
public interface ImagenFallecidoRepository extends JpaRepository<ImagenFallecido, Long> {

}
