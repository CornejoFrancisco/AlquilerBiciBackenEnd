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
@Table(name = "ALQUILERES")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "ALQUILERES", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq",
            pkColumnValue="id",
            initialValue=1, allocationSize=1)
    @Column(name = "ID")
    private long id;

    @Column(name = "ID_CLIENTE")
    private Integer id_client;

    @Column(name = "ESTADO")
    private Integer estado;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionesRetiro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ESTACION_DEVOLUCION")
    private Estacion estacionesDevolucion;


    @Column(name = "FECHA_HORA_RETIRO")
    private String fechaRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private String fechaDevolucion;

    @Column(name = "MONTO")
    private Double monto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa id_tarifa;


}
