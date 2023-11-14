package estaciones.respositories;

import estaciones.entities.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRespository extends JpaRepository<Estacion, Long> {
}
