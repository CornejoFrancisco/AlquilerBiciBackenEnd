package alquileres.entities.DTO;

import alquileres.entities.Moneda;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class AlquilerFinDto {
    private Long id;
    private Long estacionesDevolucion;
    private Double monto;
    @Nullable
    private String moneda;
}
