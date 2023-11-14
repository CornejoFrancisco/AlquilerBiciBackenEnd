package alquileres.repositories;

import alquileres.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRespository extends JpaRepository<Tarifa, Long> {
}
