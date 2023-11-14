package alquileres.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ESTACIONES")
public class Estacion {
    @Id
    @GeneratedValue(generator = "ESTACIONES")
    @TableGenerator(name = "ESTACIONES", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="id",
            initialValue=1, allocationSize=1)
    private Long ID;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FECHA_HORA_CREACION")
    private String fecha_hora_creacion;

    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;
}
