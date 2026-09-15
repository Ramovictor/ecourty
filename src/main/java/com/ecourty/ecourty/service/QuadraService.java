package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.TipoQuadra;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.repository.QuadraRepository;
import com.ecourty.ecourty.repository.TipoQuadraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuadraService {

    private final QuadraRepository quadraRepository;
    private final TipoQuadraRepository tipoQuadraRepository;

    public QuadraService(
            QuadraRepository quadraRepository,
            TipoQuadraRepository tipoQuadraRepository) {

        this.quadraRepository = quadraRepository;
        this.tipoQuadraRepository = tipoQuadraRepository;
    }

    public List<Quadra> listarPorUsuario(Usuario usuario) {
        return quadraRepository.findByUsuario(usuario);
    }

    public void cadastrar(Quadra quadra, Usuario usuario) {

        TipoQuadra tipoQuadra = tipoQuadraRepository
                .findByTipo(quadra.getTipoQuadra().getTipo())
                .orElseGet(() -> tipoQuadraRepository.save(
                        new TipoQuadra(quadra.getTipoQuadra().getTipo())));

        quadra.setTipoQuadra(tipoQuadra);
        quadra.setUsuario(usuario);

        quadraRepository.save(quadra);
    }

    public Quadra buscarPorId(Long id) {
        return quadraRepository.findById(id).orElse(null);
    }

    public void editar(Quadra quadra, Usuario usuario) {

        Quadra existente = quadraRepository.findById(quadra.getId()).orElse(null);

        if (existente == null) {
            return;
        }

        if (existente.getUsuario() == null ||
                !existente.getUsuario().getEmail().equalsIgnoreCase(usuario.getEmail())) {
            return;
        }

        TipoQuadra tipoQuadra = tipoQuadraRepository
                .findByTipo(quadra.getTipoQuadra().getTipo())
                .orElseGet(() -> tipoQuadraRepository.save(
                        new TipoQuadra(quadra.getTipoQuadra().getTipo())));

        existente.setNome(quadra.getNome());
        existente.setTipoQuadra(tipoQuadra);
        existente.setValorPorHora(quadra.getValorPorHora());

        quadraRepository.save(existente);
    }

    public void excluir(Long id, Usuario usuario) {

        Quadra quadra = quadraRepository.findById(id).orElse(null);

        if (quadra == null) {
            return;
        }

        if (quadra.getUsuario() == null ||
                !quadra.getUsuario().getEmail().equalsIgnoreCase(usuario.getEmail())) {
            return;
        }

        quadraRepository.delete(quadra);
    }
}