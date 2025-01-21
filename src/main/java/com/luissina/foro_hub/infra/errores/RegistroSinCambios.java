package com.luissina.foro_hub.infra.errores;


public class RegistroSinCambios extends RuntimeException {
    public RegistroSinCambios(String mensaje) {
        super(mensaje);
    }
}