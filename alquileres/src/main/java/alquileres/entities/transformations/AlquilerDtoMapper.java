package alquileres.entities.transformations;

import alquileres.entities.Alquiler;
import alquileres.entities.DTO.AlquilerDto;
import alquileres.repositories.EstacionRespository;
import alquileres.repositories.TarifaRespository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlquilerDtoMapper implements Function<Alquiler, AlquilerDto> {

    @Override
    public AlquilerDto apply(Alquiler alquiler) {
        return new AlquilerDto(
                alquiler.getId(),
                alquiler.getId_client(),
                alquiler.getEstado(),
                alquiler.getEstacionesRetiro().getID(),
                alquiler.getEstacionesDevolucion().getID(),
                alquiler.getFechaRetiro(),
                alquiler.getFechaDevolucion(),
                alquiler.getMonto(),
                alquiler.getId_tarifa().getId()
        );
    }
}
