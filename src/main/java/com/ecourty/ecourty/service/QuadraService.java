package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.TipoQuadra;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.repository.AgendamentoRepository;
import com.ecourty.ecourty.repository.QuadraRepository;
import com.ecourty.ecourty.repository.TipoQuadraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuadraService {

    private final QuadraRepository quadraRepository;
    private final TipoQuadraRepository tipoQuadraRepository;
    private final AgendamentoRepository agendamentoRepository;

    public QuadraService(
            QuadraRepository quadraRepository,
            TipoQuadraRepository tipoQuadraRepository,
            AgendamentoRepository agendamentoRepository) {

        this.quadraRepository = quadraRepository;
        this.tipoQuadraRepository = tipoQuadraRepository;
        this.agendamentoRepository = agendamentoRepository;
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

    public boolean excluir(Long id, Usuario usuario) {

        Quadra quadra = quadraRepository.findById(id).orElse(null);

        if (quadra == null) {
            return false;
        }

        if (quadra.getUsuario() == null ||
                !quadra.getUsuario().getEmail().equalsIgnoreCase(usuario.getEmail())) {
            return false;
        }

        if (agendamentoRepository.existsByQuadraId(id)) {
            return false;
        }

        quadraRepository.delete(quadra);
        return true;
    }
}