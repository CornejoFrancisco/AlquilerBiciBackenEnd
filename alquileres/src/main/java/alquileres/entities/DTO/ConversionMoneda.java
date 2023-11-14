package alquileres.entities.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversionMoneda {

    @JsonProperty("moneda")
    String moneda;

    @JsonProperty("importe")
    double importe;
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
}