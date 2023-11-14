package alquileres.repositories;

import alquileres.entities.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRespository extends JpaRepository<Alquiler, Long> {
}
