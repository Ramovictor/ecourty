package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.Usuario;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuadraService {

    private final List<Quadra> quadras = new ArrayList<>();

    private Long proximoId = 1L;

    public List<Quadra> listarPorUsuario(Usuario usuario) {

        List<Quadra> quadrasDoUsuario = new ArrayList<>();

        for (Quadra quadra : quadras) {

            if (quadra.getUsuario() != null
                    && quadra.getUsuario().getEmail() != null
                    && quadra.getUsuario().getEmail()
                    .equalsIgnoreCase(usuario.getEmail())) {

                quadrasDoUsuario.add(quadra);
            }
        }

        return quadrasDoUsuario;
    }

    public void cadastrar(Quadra quadra, Usuario usuario) {

        quadra.setId(proximoId);

        proximoId++;

        quadra.setUsuario(usuario);

        quadras.add(quadra);
    }

    public Quadra buscarPorId(Long id) {

        for (Quadra quadra : quadras) {

            if (quadra.getId().equals(id)) {
                return quadra;
            }
        }

        return null;
    }

    public boolean atualizar(
            Long id,
            Quadra dados,
            Usuario usuario) {

        Quadra quadra = buscarPorId(id);

        if (quadra == null) {
            return false;
        }

        if (quadra.getUsuario() == null
                || !quadra.getUsuario().getEmail()
                .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        quadra.setNome(dados.getNome());
        quadra.setTipo(dados.getTipo());
        quadra.setValorPorHora(dados.getValorPorHora());

        return true;
    }

    public boolean excluir(Long id, Usuario usuario) {

        Quadra quadra = buscarPorId(id);

        if (quadra == null) {
            return false;
        }

        if (quadra.getUsuario() == null
                || !quadra.getUsuario().getEmail()
                .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        quadras.remove(quadra);

        return true;
    }
}