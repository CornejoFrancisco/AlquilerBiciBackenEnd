package alquileres.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonedaResponse {

    private String moneda;
    private Double montoConvertido;

}

