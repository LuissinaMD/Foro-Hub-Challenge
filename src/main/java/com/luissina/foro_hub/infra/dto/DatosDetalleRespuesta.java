package com.luissina.foro_hub.infra.dto;

import com.luissina.foro_hub.model.respuesta.Respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String autor,
        Long idDelTopico,
        String tituloDelTopico,
        String mensaje,
        LocalDateTime fechaDeCreacion

) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getUsuario().getNombre(), respuesta.getTopico().getId(),
                respuesta.getTopico().getTitulo(), respuesta.getMensaje(),
                respuesta.getFechaDeCreacion());
    }
}
