package estaciones.controllers;

import estaciones.entities.EstacionDTO.EstacionDto;
import estaciones.services.EstacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
public class EstacionesController {
    private final EstacionService estacionService;

    public EstacionesController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EstacionDto>> getAll(){
        List<EstacionDto> values = estacionService.getAll();
        return ResponseEntity.ok(values);
    }
    @GetMapping("/{longitud}&{latitud}")
    public ResponseEntity<List<EstacionDto>> getEstacionparametros(@PathVariable("longitud") double longitud, @PathVariable("latitud") double latitud){
        List<EstacionDto> values = estacionService.getEstacionparametros(longitud, latitud);
        return ResponseEntity.ok(values);
    }
    @PostMapping("/agregar")
    public ResponseEntity<Void> add(@RequestBody EstacionDto entity) {
        estacionService.add(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
