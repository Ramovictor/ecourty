package com.ecourty.ecourty.controller;

import com.ecourty.ecourty.model.Cliente;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.service.ClienteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public String listarClientes(
            HttpSession session,
            Model model) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute(
                "clientes",
                clienteService.listarPorUsuario(usuario)
        );

        model.addAttribute(
                "usuario",
                usuario
        );

        return "clientes";
    }

    @GetMapping("/clientes/cadastro")
    public String formularioCadastro(
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        return "cadastro-cliente";
    }

    @PostMapping("/clientes/cadastro")
    public String cadastrar(
            Cliente cliente,
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        clienteService.cadastrar(cliente, usuario);

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        clienteService.excluir(id, usuario);

        return "redirect:/clientes";
    }
}