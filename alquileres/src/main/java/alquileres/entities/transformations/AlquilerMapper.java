package alquileres.entities.transformations;

import alquileres.entities.Alquiler;
import alquileres.entities.DTO.AlquilerDto;
import alquileres.repositories.EstacionRespository;
import alquileres.repositories.TarifaRespository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlquilerMapper implements Function<AlquilerDto, Alquiler>{
    private final EstacionRespository estacionRespository;
    private final TarifaRespository tarifaRespository;

    public AlquilerMapper(EstacionRespository estacionRespository, TarifaRespository tarifaRespository) {
        this.estacionRespository = estacionRespository;
        this.tarifaRespository = tarifaRespository;
    }

    @Override
    public Alquiler apply(AlquilerDto alquilerDto) {
        return new Alquiler(
            alquilerDto.getId(),
            alquilerDto.getId_client(),
                alquilerDto.getEstado(),
                estacionRespository.getReferenceById(alquilerDto.getEstacionesRetiro()),
                estacionRespository.getReferenceById(alquilerDto.getEstacionesDevolucion()),
                alquilerDto.getFechaRetiro(),
                alquilerDto.getFechaDevolucion(),
                alquilerDto.getMonto(),
                tarifaRespository.getReferenceById(alquilerDto.getId_tarifa())
        );
    }
}
