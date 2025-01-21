package com.luissina.foro_hub.infra.dto;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotNull
        String idTopico,
        @NotNull
        String mensaje
) {


}