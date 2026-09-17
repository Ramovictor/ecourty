
package com.ecourty.ecourty.repository;

import com.ecourty.ecourty.model.Agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository
                extends JpaRepository<Agendamento, Long> {

        List<Agendamento> findByQuadraUsuarioId(Long usuarioId);

        List<Agendamento> findByQuadraIdAndData(
                        Long quadraId,
                        LocalDate data);

        List<Agendamento> findByQuadraUsuarioIdAndDataBefore(
                        Long usuarioId,
                        LocalDate data);
}
