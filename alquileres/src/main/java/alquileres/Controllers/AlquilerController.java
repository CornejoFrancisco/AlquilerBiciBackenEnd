package alquileres.Controllers;

import alquileres.entities.Alquiler;
import alquileres.entities.DTO.*;
import alquileres.services.AlquilerService;
import alquileres.services.implementations.ConversosMonedaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alquiler")
public class AlquilerController {

    private final AlquilerService alquilerService;
    private final ConversosMonedaService conversosMonedaService;

    public AlquilerController(AlquilerService alquilerService, ConversosMonedaService conversosMonedaService) {
        this.alquilerService = alquilerService;
        this.conversosMonedaService = conversosMonedaService;
    }
    @GetMapping("/")
    public ResponseEntity<List<Alquiler>> getAll(){
        List<Alquiler> alquilers = alquilerService.getAllAlquiler();
        List<Alquiler> alquilersConDevolucion = alquilers.stream()
                .filter(objeto -> objeto.getFechaDevolucion() != null)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alquilersConDevolucion);
    }

    @GetMapping("/cliente/{client}")
    public ResponseEntity<List<AlquilerClientDto>> getAll(@PathVariable Integer client){
        List<AlquilerClientDto> alquileres = alquilerService.getAll();
        List<AlquilerClientDto> alquilerFiltro = new ArrayList<>();
        for (AlquilerClientDto objeto : alquileres) {
            if(objeto.getId_client() == client){
                alquilerFiltro.add(objeto);
            }
        }
        return ResponseEntity.ok(alquilerFiltro);

    }

    @PostMapping
    public ResponseEntity<Void> iniciarAlquiler(@RequestBody AlquilerIniDto entity){
        alquilerService.iniciarAlquiler(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<AlquilerFinDto> finAlquiler(@RequestBody AlquilerFinDto entity) {
        Alquiler alquiler = alquilerService.finAlquiler(entity);

        if (entity.getMoneda() != null) {
            String moneda = entity.getMoneda();
            double montoEntity = alquiler.getMonto().doubleValue(); // Obtener el monto como double
            double montoConvertido = conversosMonedaService.convertirMoneda(moneda, montoEntity);
            alquiler.setMonto(montoConvertido); // Actualizar el monto del alquiler con el convertido
        }

        // Guardar el alquiler con el monto actualizado
        alquilerService.guardarAlquiler(alquiler);

        // Puedes devolver algún tipo de respuesta según la lógica de tu aplicación
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
