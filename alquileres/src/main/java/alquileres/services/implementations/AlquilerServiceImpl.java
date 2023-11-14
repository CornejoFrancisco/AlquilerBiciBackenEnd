package alquileres.services.implementations;

import alquileres.entities.*;
import alquileres.entities.DTO.AlquilerClientDto;
import alquileres.entities.DTO.AlquilerDto;
import alquileres.entities.DTO.AlquilerFinDto;
import alquileres.entities.DTO.AlquilerIniDto;
import alquileres.entities.transformations.AlquilerDtoMapper;
import alquileres.entities.transformations.AlquilerMapper;
import alquileres.repositories.AlquilerRespository;
import alquileres.repositories.EstacionRespository;
import alquileres.repositories.TarifaRespository;
import alquileres.services.AlquilerService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AlquilerServiceImpl implements AlquilerService {
    private final AlquilerRespository alquilerRespository;
    private final AlquilerMapper alquilerMapper;
    private final TarifaRespository tarifaRespository;
    private final AlquilerDtoMapper alquilerDtoMapper;
    private final EstacionRespository estacionRespository;
    private RestTemplate template;
    public AlquilerServiceImpl(AlquilerRespository alquilerRespository, AlquilerMapper alquilerMapper, TarifaRespository tarifaRespository, AlquilerDtoMapper alquilerDtoMapper, EstacionRespository estacionRespository) {
        this.alquilerRespository = alquilerRespository;
        this.alquilerMapper = alquilerMapper;
        this.tarifaRespository = tarifaRespository;
        this.alquilerDtoMapper = alquilerDtoMapper;
        this.estacionRespository = estacionRespository;
    }

    @Override
    public void add(AlquilerDto entity) {
        Alquiler alquiler = Optional.of(entity).map(alquilerMapper).orElseThrow();

        alquilerRespository.save(alquiler);
    }


    public AlquilerDto getById(Long id) {
        Optional<Alquiler> alquiler = alquilerRespository.findById(id);
        return alquiler.map(alquilerDtoMapper).orElseThrow();

    }


    @Override
    public List<AlquilerClientDto> getAll() {
        List<Alquiler> alquileres = alquilerRespository.findAll();

        List<AlquilerClientDto> alquilerClientDtos = alquileres.stream()
                .map(alquiler -> {
                    AlquilerClientDto alquilerClientDto = new AlquilerClientDto();
                    alquilerClientDto.setId(alquiler.getId());
                    alquilerClientDto.setId_client(alquiler.getId_client());
                    alquilerClientDto.setEstado(alquiler.getEstado());
                    return alquilerClientDto;
                })
                .toList();

        return alquilerClientDtos;
    }

    @Override
    public List<Alquiler> getAllAlquiler() {
        List<Alquiler> alquilers = alquilerRespository.findAll();
        return alquilers;
    }


    public void update(AlquilerDto entity) {
        Optional<Alquiler> alquiler = Stream.of(entity)
                .map(alquilerMapper)
                .findAny();
        alquiler.ifPresent(alquilerRespository:: save);

    }

    @Override
    public void montoAlquiler(Alquiler alquiler) {

        LocalDateTime diaRetiro = LocalDateTime.parse(alquiler.getFechaRetiro());
        LocalDateTime diaDevolucion = LocalDateTime.parse(alquiler.getFechaDevolucion());
        Optional<Tarifa> tarifa = tarifaRespository.findById(alquiler.getId_tarifa().getId());
        Double montoBase = tarifa.get().getMontoFijoAlquiler();


        /// Calculo por el tiempo realizado el alquiler

        Integer tiempohora = 0;

        Double precioTiempo;
        Integer tiempoMinutos = 0;
        if (diaDevolucion.getMinute() > 31) {
            tiempohora = (int) LocalDateTime.now().until(diaRetiro.plusHours(-1), ChronoUnit.HOURS);
            tiempoMinutos = 0;
            System.out.println(tiempoMinutos);
        } else {
            tiempohora = (int) LocalDateTime.now().until(diaRetiro, ChronoUnit.HOURS);
            tiempoMinutos = diaDevolucion.getMinute() - diaRetiro.getMinute();
        }
        precioTiempo = precioTiempo(tiempohora, tiempoMinutos, alquiler);

        // Distancia entre las estaciones que se retirar y la estancion que se devolvio;
        Double distanciaEntreEstaciones = distanciaEntreEstaciones(alquiler.getEstacionesRetiro(), alquiler.getEstacionesDevolucion());

        Double precioKilometro = distanciaEntreEstaciones * alquiler.getId_tarifa().getMontoKm();

        Double precioTotatl = precioKilometro + precioTiempo + montoBase;
        System.out.println("precio total");
        System.out.println(precioTotatl);
        alquiler.setMonto(precioTotatl);
        alquilerRespository.save(alquiler);

    }


    @Override
    public Double precioTiempo (Integer tiempoHora, Integer tiempoMinutos, Alquiler alquiler){
        return tiempoHora * alquiler.getId_tarifa().getMontoHora() + tiempoMinutos * alquiler.getId_tarifa().getMontoMinutoFraccion();

    }
    @Override
    public Double distanciaEntreEstaciones(Estacion estacion1, Estacion estacion2) {
        Double latitud1 = estacion1.getLatitud();
        Double longitud1 = estacion1.getLongitud();
        Double latitud2 = estacion2.getLatitud();
        Double longitud2 = estacion2.getLongitud();
        return distanciaEuclidea(latitud1, longitud1, latitud2, longitud2);
    }

    @Override
    public Double distanciaEuclidea(Double latitud1, Double longitud1, Double latitud2, Double longitud2) {
        double LatitudMetros = Math.abs(latitud1 - latitud2) * 110;
        double LongitudMetros = Math.abs(longitud1 - longitud2) * 110;

        // Distancia euclídea en kilometros
        return Math.sqrt((Math.pow(LatitudMetros, 2) + Math.pow(LongitudMetros, 2)));
    }
    @Override
    public void iniciarAlquiler(AlquilerIniDto alquilerIniDto) {

        Assert.notNull(alquilerIniDto.getId_client(), "El ID del cliente no puede ser nulo");
        Assert.notNull(alquilerIniDto.getEstacionesRetiro(), "El ID de la estación de retiro no puede ser nulo");
        Assert.notNull(alquilerIniDto.getTarifa(), "El ID de la tarifa no puede ser nulo");

        Alquiler alquiler = new Alquiler();
        alquiler.setId_client(alquilerIniDto.getId_client());
        alquiler.setEstado(1);

        // Obtener referencias por ID
        Estacion estacionRetiro = estacionRespository.getReferenceById(alquilerIniDto.getEstacionesRetiro());
        Tarifa tarifa = tarifaRespository.getReferenceById(alquilerIniDto.getTarifa());

        Assert.notNull(estacionRetiro, "La estación de retiro no puede ser nula");
        Assert.notNull(tarifa, "La tarifa no puede ser nula");

        alquiler.setEstacionesRetiro(estacionRetiro);
        alquiler.setFechaRetiro(LocalDateTime.now().toString());
        alquiler.setId_tarifa(tarifa);
        alquilerRespository.save(alquiler);
    }


    @Override
    public Alquiler finAlquiler(AlquilerFinDto entity) {

        Optional<Alquiler> optionalAlquiler = alquilerRespository.findById(entity.getId());
        if (optionalAlquiler.isPresent()) {
            Alquiler alquiler = optionalAlquiler.get();

            // Verificar y asignar valores
            if (entity.getEstacionesDevolucion() != null) {
                alquiler.setEstacionesDevolucion(estacionRespository.getReferenceById(entity.getEstacionesDevolucion()));
            }


            alquiler.setEstado(2);
            alquiler.setFechaDevolucion(LocalDateTime.now().toString());
            alquilerRespository.save(alquiler);
            montoAlquiler(alquiler);
            return alquiler;
        } else {
            // Manejo de caso cuando no se encuentra el alquiler
            throw new IllegalArgumentException("No se encontró ningún alquiler con el ID proporcionado: " + entity.getId());
        }
    }

    @Override
    public void guardarAlquiler(Alquiler alquiler) {
           alquilerRespository.save(alquiler);
    }



}
