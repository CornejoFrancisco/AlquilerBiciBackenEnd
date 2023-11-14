package alquileres.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerIniDto {
    private Integer id_client;
    private Long estacionesRetiro;
    private Long tarifa;

}
