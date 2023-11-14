package estaciones.services;

import estaciones.entities.Estacion;
import estaciones.entities.EstacionDTO.EstacionDto;
import estaciones.entities.EstacionDTO.EstacionDtoMapper;
import estaciones.respositories.EstacionRespository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionServiceImpl implements EstacionService {

    @PersistenceContext
    private final EntityManager entityManager;
    private final EstacionRespository estacionRespository;
    private final EstacionDtoMapper estacionDtoMapper;

    public EstacionServiceImpl(EntityManager entityManager, EstacionRespository estacionRespository, EstacionDtoMapper estacionDtoMapper) {
        this.entityManager = entityManager;
        this.estacionRespository = estacionRespository;
        this.estacionDtoMapper = estacionDtoMapper;
    }

    @Override
    public List<EstacionDto> getAll() {
        List<Estacion> values = estacionRespository.findAll();
        return values.stream().map(estacionDtoMapper).toList();
    }

    @Override
    public List<EstacionDto> getEstacionparametros(Double longitud, Double latitud) {
        List<Estacion> values = estacionRespository.findAll();
        Optional<Estacion> estacionCercana = encontrarEstacionCercana(values, longitud, latitud);

        return estacionCercana.map(estacion -> List.of(estacionDtoMapper.apply(estacion)))
                .orElse(List.of());
    }

    @Override
    @Transactional
    public void add(EstacionDto entity) {
        Estacion estacion = new Estacion();
        estacion.setNombre(entity.getNombre());
        estacion.setLatitud(entity.getLatitud());
        estacion.setLongitud(entity.getLongitud());
        estacion.setFecha_hora_creacion(entity.getFecha_hora_creacion());
        entityManager.persist(estacion);
    }

    private Optional<Estacion> encontrarEstacionCercana(List<Estacion> estaciones, Double longitud, Double latitud) {
        Estacion estacionCercana = null;
        double distanciaMinima = Double.MAX_VALUE;
        for (Estacion estacion : estaciones) {
            double distancia = calcularDistancia(estacion.getLongitud(), estacion.getLatitud(), longitud, latitud);

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                estacionCercana = estacion;
            }
        }
        return Optional.ofNullable(estacionCercana);
    }

    private double calcularDistancia(Double longitudEstacion, Double latitudEstacion, Double longitudCliente, Double latitudCliente) {

        double radioTierra = 6371;
        double dLat = Math.toRadians(latitudEstacion - latitudCliente);
        double dLon = Math.toRadians(longitudEstacion - longitudCliente);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latitudCliente)) * Math.cos(Math.toRadians(latitudEstacion)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radioTierra * c;
    }

}
