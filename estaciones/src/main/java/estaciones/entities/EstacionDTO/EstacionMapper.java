package estaciones.entities.EstacionDTO;

import estaciones.entities.Estacion;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EstacionMapper implements Function<EstacionDto, Estacion> {
    @Override
    public Estacion apply(EstacionDto estacionDto) {
        return new Estacion(
                estacionDto.getID(),
                estacionDto.getNombre(),
                estacionDto.getLatitud(),
                estacionDto.getLongitud(),
                estacionDto.getFecha_hora_creacion()
                );
    }
}
