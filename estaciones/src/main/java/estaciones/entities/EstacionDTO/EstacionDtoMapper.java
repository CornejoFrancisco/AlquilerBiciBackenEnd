package estaciones.entities.EstacionDTO;

import estaciones.entities.Estacion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EstacionDtoMapper implements Function<Estacion, EstacionDto> {
    @Override
    public EstacionDto apply(Estacion estacion){
        return new EstacionDto(
            estacion.getID(),
            estacion.getNombre(),
                estacion.getLatitud(),
                estacion.getLongitud(),
                estacion.getFecha_hora_creacion()
                );
    }
}
