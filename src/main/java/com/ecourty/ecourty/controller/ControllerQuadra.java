package com.ecourty.ecourty.controller;

import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerQuadra {

    private final UsuarioService usuarioService;

    public ControllerQuadra(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(
            @ModelAttribute Usuario usuario,
            Model model) {

        boolean cadastrado = usuarioService.cadastrar(usuario);

        if (!cadastrado) {

            model.addAttribute(
                    "erro",
                    "Este e-mail já está cadastrado."
            );

            return "cadastro";
        }

        return "redirect:/";
    }

    @PostMapping("/login")
    public String fazerLogin(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            Model model) {

        Usuario usuario = usuarioService.login(email, senha);

        if (usuario == null) {

            model.addAttribute(
                    "erro",
                    "E-mail ou senha incorretos."
            );

            return "login";
        }

        session.setAttribute("usuarioLogado", usuario);

        return "redirect:/quadras";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }

    public Usuario getUsuarioLogado(HttpSession session) {

        return (Usuario) session.getAttribute("usuarioLogado");
    }
}