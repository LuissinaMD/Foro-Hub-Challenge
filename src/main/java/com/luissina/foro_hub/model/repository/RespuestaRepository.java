package com.luissina.foro_hub.model.repository;


import com.luissina.foro_hub.model.respuesta.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository  extends JpaRepository<Respuesta, Long> {

    @Query("SELECT r FROM Respuesta r WHERE r.estado IN ('ACTIVO','ACTUALIZADO')")
    Page<Respuesta> findByEstado(Pageable pageable);
}
