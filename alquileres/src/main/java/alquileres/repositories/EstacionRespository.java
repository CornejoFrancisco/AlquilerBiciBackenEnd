package alquileres.repositories;

import alquileres.entities.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRespository extends JpaRepository<Estacion, Long> {
}
