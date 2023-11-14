package alquileres.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
public enum Moneda {
    USD("USD"),
    ARS("ARS"),
    EUR("EUR"),
    CLP("CLP"),
    BRL("BRL"),
    COP("COP"),
    PEN("PEN"),
    GBP("GBP")
    ;

    private final String valor;

}