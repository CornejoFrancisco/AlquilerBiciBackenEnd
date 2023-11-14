package alquileres.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerClientDto {
    private long id;
    private Integer id_client;
    private Integer estado;
}
