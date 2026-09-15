package com.ecourty.ecourty.repository;

import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuadraRepository extends JpaRepository<Quadra, Long> {

    List<Quadra> findByUsuario(Usuario usuario);
}