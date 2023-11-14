package alquileres.services.implementations;
import alquileres.entities.DTO.ConversionMoneda;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;

@Service
public class ConversosMonedaService {
    private final RestTemplate restTemplate;
    private final String API_URL = "http://34.82.105.125:8080/convertir";
    private final List<String> monedas = Arrays.asList("CLP", "USD", "EUR", "BRL", "COP", "PEN", "GBP");

    public ConversosMonedaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double convertirMoneda(String moneda, Double monto) {
        String codMonedaHasta = moneda;


        if (!monedas.contains(codMonedaHasta)) {
            codMonedaHasta = "ARS";
            return monto;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construir el cuerpo de la solicitud
        String requestBody = "{\"moneda_destino\":\"" + codMonedaHasta + "\", \"importe\":" + monto + "}";

        // Crear la entidad HTTP con las cabeceras y el cuerpo de la solicitud
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Hacer la solicitud POST a la API de conversión de monedas
        ResponseEntity<ConversionMoneda> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                entity,
                ConversionMoneda.class
        );

        // Verificar si la solicitud fue exitosa
        if (response.getStatusCode().is2xxSuccessful()) {
            ConversionMoneda conversionMoneda = response.getBody();
            if (conversionMoneda != null) {
                // Devolver el importe convertido
                return conversionMoneda.getImporte();
            }
        }
        return 0.0;
    }
}
