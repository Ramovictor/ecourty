package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Cliente;
import com.ecourty.ecourty.model.Usuario;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();

    private Long proximoId = 1L;

    public List<Cliente> listarPorUsuario(Usuario usuario) {

        List<Cliente> clientesDoUsuario = new ArrayList<>();

        for (Cliente cliente : clientes) {

            if (cliente.getUsuario() != null
                    && cliente.getUsuario().getEmail() != null
                    && cliente.getUsuario().getEmail()
                    .equalsIgnoreCase(usuario.getEmail())) {

                clientesDoUsuario.add(cliente);
            }
        }

        return clientesDoUsuario;
    }

    public void cadastrar(Cliente cliente, Usuario usuario) {

        cliente.setId(proximoId);

        proximoId++;

        cliente.setUsuario(usuario);

        clientes.add(cliente);
    }

    public Cliente buscarPorId(Long id) {

        for (Cliente cliente : clientes) {

            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        return null;
    }

    public boolean excluir(Long id, Usuario usuario) {

        Cliente cliente = buscarPorId(id);

        if (cliente == null) {
            return false;
        }

        if (cliente.getUsuario() == null
                || !cliente.getUsuario().getEmail()
                .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        clientes.remove(cliente);

        return true;
    }
}