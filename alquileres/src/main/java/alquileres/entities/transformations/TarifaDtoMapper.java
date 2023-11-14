package alquileres.entities.transformations;

import alquileres.entities.DTO.TarifaDto;
import alquileres.entities.Tarifa;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TarifaDtoMapper implements Function<Tarifa, TarifaDto> {
    @Override
    public  TarifaDto apply(Tarifa tarifa){
        return new TarifaDto(
                tarifa.getId(),
                tarifa.getTipo_tarifa(),
                tarifa.getDefinicion(),
                tarifa.getDiasemana(),
                tarifa.getDiaMes(),
                tarifa.getMes(),
                tarifa.getAnio(),
                tarifa.getMontoFijoAlquiler(),
                tarifa.getMontoMinutoFraccion(),
                tarifa.getMontoKm(),
                tarifa.getMontoHora()
        );
    }
}
