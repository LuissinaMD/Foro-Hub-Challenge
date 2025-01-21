package com.luissina.foro_hub.infra.errores;


public class UsuarioNoAutorizado extends RuntimeException {
    public UsuarioNoAutorizado(String mensaje) {
        super(mensaje);
    }
}
