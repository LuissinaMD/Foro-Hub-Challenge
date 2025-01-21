package com.luissina.foro_hub.model.topico;

import com.luissina.foro_hub.infra.dto.DatosActualizarTopico;
import com.luissina.foro_hub.infra.dto.DatosRegistroTopico;
import com.luissina.foro_hub.model.respuesta.Respuesta;
import com.luissina.foro_hub.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos", uniqueConstraints = @UniqueConstraint(columnNames = {"titulo", "mensaje"}))
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico extends Comentario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    private boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String curso = "Sin curso";
    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY,  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Estado estado;


    public Topico(DatosRegistroTopico datos, Usuario usuario) {
        this.status = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.curso = datos.nombreDelCurso();
        this.usuario =  usuario;
        this.estado = Estado.ACTIVO;
    }


    public void actualizarDatos(DatosActualizarTopico datosActualizaTopico){
        if (datosActualizaTopico.titulo() != null){
            this.titulo = datosActualizaTopico.titulo();
            actualizarEstado();
        }
        if (datosActualizaTopico.mensaje() != null){
            this.mensaje = datosActualizaTopico.mensaje();
            actualizarEstado();
        }
        if (datosActualizaTopico.curso() != null){
            this.curso = datosActualizaTopico.curso();
            actualizarEstado();
        }

    }

    public void actualizarEstado(){
        this.estado = Estado.ACTUALIZADO;
    }


    public void desativarTopico() {
        this.estado = estado.ELIMINADO;
    }
}