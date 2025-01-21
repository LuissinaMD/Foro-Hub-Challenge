package com.luissina.foro_hub.infra.dto;

import com.luissina.foro_hub.model.usuario.Usuario;

public record DatosCompletoUsuarioAdministrador(
        Long id,
        String nombreDelUsuario,
        String email,
        String perfil,
        Integer cantidadDeTopicos,
        Integer cantidadDeRespuestas
) {
    public DatosCompletoUsuarioAdministrador(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail()
                ,usuario.getPerfil().name(), usuario.getTopicos().size(),
                usuario.getRespuestas().size());

    }
}