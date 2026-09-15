package com.ecourty.ecourty.repository;

import com.ecourty.ecourty.model.Cliente;
import com.ecourty.ecourty.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByUsuario(Usuario usuario);
}