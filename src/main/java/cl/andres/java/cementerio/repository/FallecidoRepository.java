package cl.andres.java.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.Fallecido;

@Repository
public interface FallecidoRepository extends JpaRepository<Fallecido, Long> {

}
