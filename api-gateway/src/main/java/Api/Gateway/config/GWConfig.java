package Api.Gateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class GWConfig {
    @Value("${microservicio.estaciones.url:http://localhost:8081}") String estacionesUrl;

    @Value("${microservicio.alquileres.url:http://localhost:8082}") String alquileresUrl;
    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder){

        return builder.routes()

                .route(p -> p.path("/api/estaciones/all").uri(estacionesUrl))
                .route(p -> p.path("/api/estaciones/{longitud}&{latitud}").uri(estacionesUrl))

                .route(p -> p.path("/api/estaciones/agregar").uri(estacionesUrl))
                .route(p -> p.path("/api/alquiler/**").uri(alquileresUrl))
                .build();


    }

}
