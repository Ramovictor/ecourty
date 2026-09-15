package com.ecourty.ecourty.repository;

import com.ecourty.ecourty.model.TipoQuadra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoQuadraRepository extends JpaRepository<TipoQuadra, Long> {

    Optional<TipoQuadra> findByTipo(String tipo);
}