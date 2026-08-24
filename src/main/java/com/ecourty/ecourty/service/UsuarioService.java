package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public boolean cadastrar(Usuario usuario) {

        for (Usuario u : usuarios) {

            if (u.getEmail() != null
                    && u.getEmail().equalsIgnoreCase(usuario.getEmail())) {

                return false;
            }
        }

        usuarios.add(usuario);

        return true;
    }

    public Usuario login(String email, String senha) {

        for (Usuario usuario : usuarios) {

            if (usuario.getEmail() != null
                    && usuario.getSenha() != null
                    && usuario.getEmail().equalsIgnoreCase(email)
                    && usuario.getSenha().equals(senha)) {

                return usuario;
            }
        }

        return null;
    }
}