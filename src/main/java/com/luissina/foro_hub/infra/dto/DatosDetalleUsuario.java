package com.luissina.foro_hub.infra.dto;

import com.luissina.foro_hub.model.usuario.EstadoUsuario;
import com.luissina.foro_hub.model.usuario.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombreDelUsuario,
        String email,
        EstadoUsuario estado

) {
    public DatosDetalleUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getEstado());
    }
}