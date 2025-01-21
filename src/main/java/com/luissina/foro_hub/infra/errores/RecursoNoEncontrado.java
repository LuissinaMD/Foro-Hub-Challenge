package com.luissina.foro_hub.infra.errores;


public class RecursoNoEncontrado extends RuntimeException {
    public RecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
