package alquileres.entities.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquierConvertidaDto {
    private Double monto;
    @Nullable
    private String moneda;
}
