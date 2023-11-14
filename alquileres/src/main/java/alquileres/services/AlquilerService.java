package alquileres.services;

import alquileres.entities.Alquiler;
import alquileres.entities.DTO.AlquilerClientDto;
import alquileres.entities.DTO.AlquilerDto;
import alquileres.entities.DTO.AlquilerFinDto;
import alquileres.entities.DTO.AlquilerIniDto;
import alquileres.entities.Estacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlquilerService{
    List<AlquilerClientDto> getAll();
    List<Alquiler> getAllAlquiler();
    void add(AlquilerDto entity);
    void update(AlquilerDto entity);
    AlquilerDto getById(Long id);
    void iniciarAlquiler(AlquilerIniDto entity);

    public void montoAlquiler(Alquiler alquiler);
    public Double distanciaEntreEstaciones(Estacion estacion1, Estacion estacion2);

    public Double distanciaEuclidea(Double latitud1, Double longitud1, Double latitud2, Double longitud2);
    public Double precioTiempo (Integer tiempo, Integer tiempoMinutos, Alquiler alquiler);

    Alquiler finAlquiler(AlquilerFinDto alquilerFinDto);

    void guardarAlquiler(Alquiler alquiler);
}
