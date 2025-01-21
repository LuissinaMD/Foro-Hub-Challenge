package com.luissina.foro_hub.infra.dto;

import com.luissina.foro_hub.model.topico.Estado;
import com.luissina.foro_hub.model.topico.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DatosTopicoConRespuestas(
        Long id,
        String autor,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Estado estado,
        String curso,
        List<DatosDetalleRespuesta> respuestas

) {

    public DatosTopicoConRespuestas (Topico topico){
        this(topico.getId(), topico.getUsuario().getNombre(),topico.getTitulo(),
                topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstado(),
                topico.getCurso(), topico.getRespuestas()
                        .stream()
                        .map(respuesta -> new DatosDetalleRespuesta(respuesta))
                        .collect(Collectors.toList()));
    }
}