package estaciones.services;

import estaciones.entities.Estacion;
import estaciones.entities.EstacionDTO.EstacionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstacionService {
    List<EstacionDto> getAll();
    List<EstacionDto> getEstacionparametros(Double longitud , Double  latitud);
    void add(EstacionDto entity);


}
