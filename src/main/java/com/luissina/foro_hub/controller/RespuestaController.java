package com.luissina.foro_hub.controller;

import com.luissina.foro_hub.infra.dto.DatosActualizarRespuesta;
import com.luissina.foro_hub.infra.dto.DatosDetalleRespuesta;
import com.luissina.foro_hub.infra.dto.DatosRegistroRespuesta;
import com.luissina.foro_hub.infra.service.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;



    @PostMapping
    public ResponseEntity reistrarRespuesta(@Valid @RequestBody DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        return respuestaService.responderTopico(datosRegistroRespuesta, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> obtenerRespuestasDeTopicos(Pageable paginacion){
        return respuestaService.obtenerListadoDeRespuestas(paginacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> verRespuestaEspecifico(@PathVariable Long id){
        return respuestaService.obtenerComentarioEspecifico(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> actualizarRespuesta(@RequestBody DatosActualizarRespuesta datosActualizar, @PathVariable Long id){
        return respuestaService.actualizarRespuesta(datosActualizar,id);
    }

    @DeleteMapping(("/{id}"))
    public ResponseEntity<DatosDetalleRespuesta> eliminarRespuesta(@PathVariable Long id){
        return respuestaService.eliminarRespuesta(id);
    }

    //
    @PutMapping("/solucion/{id}")
    public ResponseEntity<DatosDetalleRespuesta> solucionarTopico(@PathVariable Long id){
        var respuesta = respuestaService.solucionarTopico(id);
        return ResponseEntity.ok(respuesta);
    }

}
