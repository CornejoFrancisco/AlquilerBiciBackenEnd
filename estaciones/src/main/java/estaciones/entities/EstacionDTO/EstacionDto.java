package estaciones.entities.EstacionDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionDto {
    private Long ID;

    private String nombre;

    private Double latitud;

    private Double longitud;

    private LocalDateTime fecha_hora_creacion;

}