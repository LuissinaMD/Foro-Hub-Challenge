package com.luissina.foro_hub.controller;

import com.luissina.foro_hub.infra.dto.DatosActualizarUsuario;
import com.luissina.foro_hub.infra.dto.DatosCambioDeUsuario;
import com.luissina.foro_hub.infra.dto.DatosDetalleUsuario;
import com.luissina.foro_hub.infra.dto.DatosRegistroUsuario;
import com.luissina.foro_hub.infra.seguridad.ValidadorPermisos;
import com.luissina.foro_hub.infra.service.UsuarioService;
import com.luissina.foro_hub.model.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidadorPermisos validadorPermisos;

    @PostMapping
    public ResponseEntity registrarUsuario(@Valid @RequestBody DatosRegistroUsuario datosRegistroUsuario,
                                           UriComponentsBuilder uriComponentsBuilder){
        return usuarioService.registrarUsuario(datosRegistroUsuario, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity obtenerUsuarios(Pageable paginacion){
        return usuarioService.obtenerListadoUsuarios(paginacion);
    }

    @GetMapping("/{id}")
    public DatosDetalleUsuario obtenerUsuarioEspecifico(@PathVariable Long id){
        return usuarioService.obtenerUsuarioEspecifico(id);
    }

    @PutMapping
    public ResponseEntity<DatosDetalleUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizar){
        var usuarioModificado = usuarioService.actualizarInformacionDelUsuario(datosActualizar);
        return ResponseEntity.ok(usuarioModificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        return usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity cambiarRolUsuario(@PathVariable Long id,
                                            @Valid @RequestBody DatosCambioDeUsuario datosCambioDeUsuario){
        var usuario = usuarioService.cambiarPerfilUsuario(id, datosCambioDeUsuario);
        return ResponseEntity.ok(usuario);
    }

}
