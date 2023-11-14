package alquileres.entities.transformations;

import alquileres.entities.Alquiler;
import alquileres.entities.DTO.AlquilerDto;
import alquileres.entities.DTO.TarifaDto;
import alquileres.entities.Tarifa;
import alquileres.repositories.EstacionRespository;
import alquileres.repositories.TarifaRespository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TarifaMapper implements Function<TarifaDto, Tarifa> {
    @Override
    public Tarifa apply(TarifaDto tarifaDto) {
        return new Tarifa(
                tarifaDto.getId(),
                tarifaDto.getTipo_tarifa(),
                tarifaDto.getDefinicion(),
                tarifaDto.getDiasemana(),
                tarifaDto.getDiaMes(),
                tarifaDto.getMes(),
                tarifaDto.getAnio(),
                tarifaDto.getMontoFijoAlquiler(),
                tarifaDto.getMontoMinutoFraccion(),
                tarifaDto.getMontoKm(),
                tarifaDto.getMontoHora()
        );
    }
}
