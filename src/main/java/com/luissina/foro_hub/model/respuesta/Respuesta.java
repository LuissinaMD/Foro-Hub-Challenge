package com.luissina.foro_hub.model.respuesta;


import com.luissina.foro_hub.infra.dto.DatosActualizarRespuesta;
import com.luissina.foro_hub.infra.dto.DatosRegistroRespuesta;
import com.luissina.foro_hub.model.topico.Comentario;
import com.luissina.foro_hub.model.topico.Topico;
import com.luissina.foro_hub.model.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas", uniqueConstraints = @UniqueConstraint(columnNames = "mensaje"))
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta extends Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaDeCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private boolean solucion;
    @Enumerated(EnumType.STRING)
    private EstadoRespuesta estado;

    public Respuesta(@Valid DatosRegistroRespuesta datos, Usuario usuario, Topico topico) {
        this.estado = EstadoRespuesta.ACTIVO;
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.usuario = usuario;
        this.topico = topico;
        this.solucion = false;
    }


    public void actualizarRespuesta(@Valid DatosActualizarRespuesta datosNuevos) {
        if (datosNuevos.mensaje() != null){
            this.estado = EstadoRespuesta.ACTUALIZADO;
            this.mensaje = datosNuevos.mensaje();
        }

    }

    public void desactivarRespuesta() {
        this.estado = EstadoRespuesta.ELIMINADO;
    }

    public void solucionarRespuesta() {
        this.solucion = true;
    }
}