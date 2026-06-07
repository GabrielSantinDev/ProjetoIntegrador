package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.mappers.AvaliacaoMapper;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.repositories.AvaliacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    public List<AvaliacaoResponseDTO> listar() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(avaliacaoMapper::toDto)
                .toList();
    }

    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = avaliacaoMapper.toEntity(dto);
        return avaliacaoMapper.toDto(avaliacaoRepository.save(avaliacao));
    }

    public AvaliacaoResponseDTO atualizar(Long codigo, AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        avaliacaoMapper.partialUpdate(dto, avaliacao);

        return avaliacaoMapper.toDto(avaliacaoRepository.save(avaliacao));
    }

    @Transactional
    public void excluir(Long codigo) {
        avaliacaoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        avaliacaoRepository.deleteById(codigo);
    }

    public AvaliacaoResponseDTO buscarPorId(Long codigo) {
        Avaliacao avaliacao = avaliacaoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));

        return avaliacaoMapper.toDto(avaliacao);
    }
}
