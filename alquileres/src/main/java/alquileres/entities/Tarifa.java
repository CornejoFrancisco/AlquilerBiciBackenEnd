package alquileres.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TARIFAS")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TIPO_TARIFA")
    private Integer tipo_tarifa;
    @Column(name = "DEFINICION")
    private String definicion;
    @Column(name = "DIA_SEMANA")
    private Integer diasemana;

    @Column(name = "DIA_MES")
    private Integer diaMes;

    @Column(name = "MES")
    private Integer mes;

    @Column(name = "ANIO")
    private LocalDate anio;

    @Column(name = "MONTO_FIJO_ALQUILER")
    private Double montoFijoAlquiler;

    @Column(name = "MONTO_MINUTO_FRACCION")
    private Double montoMinutoFraccion;

    @Column(name = "MONTO_KM")
    private Double montoKm;

    @Column(name = "MONTO_HORA")
    private Double montoHora;


}
