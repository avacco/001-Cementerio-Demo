package cl.andres.java.cementerio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.Condolencia;

@Repository
public interface CondolenciaRepository extends JpaRepository<Condolencia, Long> {

}
