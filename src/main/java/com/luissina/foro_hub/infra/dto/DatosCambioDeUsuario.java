package com.luissina.foro_hub.infra.dto;

import com.luissina.foro_hub.model.usuario.PerfilUsuario;
import jakarta.validation.constraints.NotNull;

public record DatosCambioDeUsuario(
//        @NotNull
//        String idDeUsuario,
        @NotNull
        PerfilUsuario perfil) {

}