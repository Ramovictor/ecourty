package com.ecourty.ecourty.controller;

import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.service.QuadraService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuadraController {

    private final QuadraService quadraService;

    public QuadraController(QuadraService quadraService) {
        this.quadraService = quadraService;
    }

    @GetMapping("/quadras")
    public String listarQuadras(
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        model.addAttribute(
                "quadras",
                quadraService.listarPorUsuario(usuario)
        );

        model.addAttribute(
                "usuario",
                usuario
        );

        return "quadras";
    }

    @GetMapping("/quadras/cadastro")
    public String formularioCadastro(
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        return "cadastro-quadra";
    }

    @PostMapping("/quadras/cadastro")
    public String cadastrar(
            Quadra quadra,
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        quadraService.cadastrar(quadra, usuario);

        return "redirect:/quadras";
    }

    @GetMapping("/quadras/editar/{id}")
    public String formularioEditar(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        Quadra quadra = quadraService.buscarPorId(id);

        if (quadra == null) {
            return "redirect:/quadras";
        }

        if (quadra.getUsuario() == null
                || !quadra.getUsuario().getEmail()
                .equalsIgnoreCase(usuario.getEmail())) {

            return "redirect:/quadras";
        }

        model.addAttribute("quadra", quadra);

        return "editar-quadra";
    }

    @PostMapping("/quadras/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Quadra quadra,
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        quadraService.atualizar(
                id,
                quadra,
                usuario
        );

        return "redirect:/quadras";
    }

    @GetMapping("/quadras/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            HttpSession session) {

        Usuario usuario =
                (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        quadraService.excluir(id, usuario);

        return "redirect:/quadras";
    }
}