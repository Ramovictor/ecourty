package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Cliente;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarPorUsuario(Usuario usuario) {
        return clienteRepository.findByUsuario(usuario);
    }

    public void cadastrar(Cliente cliente, Usuario usuario) {
        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public boolean excluir(Long id, Usuario usuario) {

        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente == null) {
            return false;
        }

        if (cliente.getUsuario() == null ||
                !cliente.getUsuario().getEmail().equalsIgnoreCase(usuario.getEmail())) {
            return false;
        }

        clienteRepository.delete(cliente);

        return true;
    }
}