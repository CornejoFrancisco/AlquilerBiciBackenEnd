package alquileres.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerDto {
    private long id;
    private Integer id_client;
    private Integer estado;
    private Long estacionesRetiro;
    private Long estacionesDevolucion;
    private String fechaRetiro;
    private String fechaDevolucion;
    private Double monto;
    private Long id_tarifa;

}
