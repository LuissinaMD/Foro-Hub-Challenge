package com.luissina.foro_hub.model.topico;

import com.luissina.foro_hub.model.usuario.Usuario;
import lombok.Getter;

@Getter
public abstract class Comentario {
    private Usuario usuario;
}