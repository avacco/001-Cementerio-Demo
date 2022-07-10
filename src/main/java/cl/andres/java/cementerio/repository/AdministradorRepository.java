package cl.andres.java.cementerio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.andres.java.cementerio.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	Optional<Administrador> findByUsername(String username);

}
