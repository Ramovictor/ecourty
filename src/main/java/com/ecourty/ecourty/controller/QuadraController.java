package com.ecourty.ecourty.controller;

import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.TipoQuadra;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.repository.TipoQuadraRepository;
import com.ecourty.ecourty.service.QuadraService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuadraController {

    private final QuadraService quadraService;
    private final TipoQuadraRepository tipoQuadraRepository;

    public QuadraController(
            QuadraService quadraService,
            TipoQuadraRepository tipoQuadraRepository) {

        this.quadraService = quadraService;
        this.tipoQuadraRepository = tipoQuadraRepository;
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
                quadraService.listarPorUsuario(usuario));

        model.addAttribute(
                "usuario",
                usuario);

        return "quadras";
    }

    @GetMapping("/quadras/cadastro")
    public String formularioCadastro(
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        return "cadastro-quadra";
    }

    @PostMapping("/quadras/cadastro")
    public String cadastrar(
            @RequestParam String nome,
            @RequestParam String tipo,
            @RequestParam Double valorPorHora,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        TipoQuadra tipoQuadra = tipoQuadraRepository.findByTipo(tipo)
                .orElseGet(() -> tipoQuadraRepository.save(
                        new TipoQuadra(tipo)));

        Quadra quadra = new Quadra();

        quadra.setNome(nome);
        quadra.setTipoQuadra(tipoQuadra);
        quadra.setValorPorHora(valorPorHora);

        quadraService.cadastrar(quadra, usuario);

        return "redirect:/quadras";
    }

    @GetMapping("/quadras/editar/{id}")
    public String formularioEditar(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

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
            @RequestParam String nome,
            @RequestParam String tipo,
            @RequestParam Double valorPorHora,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        TipoQuadra tipoQuadra = tipoQuadraRepository.findByTipo(tipo)
                .orElseGet(() -> tipoQuadraRepository.save(
                        new TipoQuadra(tipo)));

        Quadra quadra = new Quadra();

        quadra.setId(id);
        quadra.setNome(nome);
        quadra.setTipoQuadra(tipoQuadra);
        quadra.setValorPorHora(valorPorHora);

        quadraService.editar(quadra, usuario);

        return "redirect:/quadras";
    }

    @GetMapping("/quadras/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        quadraService.excluir(id, usuario);

        return "redirect:/quadras";
    }
}