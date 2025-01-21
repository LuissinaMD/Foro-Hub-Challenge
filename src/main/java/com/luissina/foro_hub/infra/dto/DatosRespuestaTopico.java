package com.luissina.foro_hub.infra.dto;


import com.luissina.foro_hub.model.topico.Estado;
import com.luissina.foro_hub.model.topico.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String autor,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Estado estado,
        String curso,
        Integer respuestas
) {

    public DatosRespuestaTopico (Topico topico){
        this(topico.getId(), topico.getUsuario().getNombre(),topico.getTitulo(),
                topico.getMensaje(), topico.getFechaDeCreacion(), topico.getEstado(),
                topico.getCurso(), topico.getRespuestas().size());
    }

}