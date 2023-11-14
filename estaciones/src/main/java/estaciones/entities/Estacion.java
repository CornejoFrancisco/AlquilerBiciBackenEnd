package estaciones.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ESTACIONES")
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "NOMBRE")
    private String nombre;


    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;

    @CreationTimestamp
    @Column(name = "FECHA_HORA_CREACION")
    private LocalDateTime fecha_hora_creacion;
}
