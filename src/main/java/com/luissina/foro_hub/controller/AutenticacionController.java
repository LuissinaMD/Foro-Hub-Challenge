package com.luissina.foro_hub.controller;

import com.luissina.foro_hub.infra.dto.DatosAutenticacionUsuario;
import com.luissina.foro_hub.infra.dto.DatosJWTToken;
import com.luissina.foro_hub.infra.service.JwtTokenService;
import com.luissina.foro_hub.model.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity generarTokenUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosUsuario) {
        Authentication tokenUsuarioAutorizacion = new UsernamePasswordAuthenticationToken(datosUsuario.usuario(), datosUsuario.clave());

        var usuarioAutenticado = authenticationManager.authenticate(tokenUsuarioAutorizacion);
        var JWTtoken = jwtTokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));

    }

}
