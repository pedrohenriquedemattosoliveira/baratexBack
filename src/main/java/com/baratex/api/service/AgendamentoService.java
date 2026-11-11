package com.baratex.api.service;

import com.baratex.api.model.Agendamento;
import com.baratex.api.model.ItemAgendamento;
import com.baratex.api.model.Servico;
import com.baratex.api.model.StatusAgendamento;
import com.baratex.api.repository.AgendamentoRepository;
import com.baratex.api.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final MensagemChatService mensagemChatService;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ServicoRepository servicoRepository,
                              MensagemChatService mensagemChatService) {
        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
        this.mensagemChatService = mensagemChatService;
    }

    @Transactional
    public Agendamento criarAgendamento(Agendamento agendamento) {
        BigDecimal valorTotal = BigDecimal.ZERO;

        if (agendamento.getItens() != null) {
            for (ItemAgendamento item : agendamento.getItens()) {
                item.setAgendamento(agendamento);

                if (item.getPrecoPraticado() == null) {
                    Servico servico = servicoRepository.findById(item.getServico().getId())
                            .orElseThrow(() -> new RuntimeException("Serviço não encontrado: " + item.getServico().getId()));
                    item.setPrecoPraticado(servico.getPrecoBase());
                }

                valorTotal = valorTotal.add(item.getPrecoPraticado());
            }
        }

        agendamento.setValorTotal(valorTotal);
        if (agendamento.getStatus() == null) {
            agendamento.setStatus(StatusAgendamento.PENDENTE);
        }

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento buscarPorId(UUID id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o ID: " + id));
    }

    public List<Agendamento> listarPorOficina(UUID oficinaId) {
        return agendamentoRepository.findByOficinaId(oficinaId);
    }

    public List<Agendamento> listarPorMotorista(UUID motoristaId) {
        return agendamentoRepository.findByVeiculoMotoristaId(motoristaId);
    }

    @Transactional
    public Agendamento atualizarStatus(UUID id, StatusAgendamento status) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(status);

        if (status == StatusAgendamento.CONCLUIDO || status == StatusAgendamento.CANCELADO) {
            mensagemChatService.apagarHistoricoPorAgendamento(id);
        }

        return agendamentoRepository.save(agendamento);
    }
}