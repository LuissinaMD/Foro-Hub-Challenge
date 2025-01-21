package com.luissina.foro_hub.infra.dto;

import jakarta.validation.constraints.Email;

public record DatosActualizarUsuario(
        String nombre,
        @Email
        String email,
        String clave
) {
}