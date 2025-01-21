package com.luissina.foro_hub.infra.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        @JsonAlias("nombreDelCurso")
        String curso

) {
}