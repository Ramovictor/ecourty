package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean cadastrar(Usuario usuario) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return false;
        }

        usuarioRepository.save(usuario);

        return true;
    }

    public Usuario login(String email, String senha) {

        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha))
                .orElse(null);
    }
}