package alquileres.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifaDto {
    private Long id;
    private Integer tipo_tarifa;
    private String definicion;
    private Integer diasemana;
    private Integer diaMes;
    private Integer mes;
    private LocalDate anio;
    private Double montoFijoAlquiler;
    private Double montoMinutoFraccion;
    private Double montoKm;
    private Double montoHora;
}
