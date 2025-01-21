package com.luissina.foro_hub.infra.seguridad;

import com.luissina.foro_hub.model.repository.UsuarioRepository;
import com.luissina.foro_hub.model.topico.Comentario;
import com.luissina.foro_hub.model.usuario.PerfilUsuario;
import com.luissina.foro_hub.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPermisos {
    private PerfilUsuario perfil;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public boolean validarPerfilDeUsuario(PerfilUsuario perfil){
        var autenticacion = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("valor" + autenticacion.toString());
        Long usuarioId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usuarioId != null){
            Usuario usuarioActual = usuarioRepository.getReferenceById(usuarioId);
            return usuarioActual.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_"+ perfil));

        }
        throw new RuntimeException("El id en la base de datos no existe");

    }

    public boolean validarRolModerador(){
        if (validarPerfilDeUsuario(perfil.MODERADOR)) {
            return true;
        }
        return false;
    }

    public boolean validarRolAdministrador(){
        if (validarPerfilDeUsuario(perfil.ADMINISTRADOR)) {
            return true;
        }
        return false;
    }
    public boolean validarRolUsuarioRegistrado(){
        if (validarPerfilDeUsuario(perfil.USUARIO_REGISTRADO)) {
            return true;
        }
        return false;
    }

    public boolean validarUsuarioactualEsAutor(Comentario comentario){
        Long usuarioActual = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (comentario.getUsuario().getId().equals(usuarioActual)) {
            return true;
        }
        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return false;
    }





}