package com.baratex.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "garagem_virtual")
public class GaragemVirtual {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "agendamento_id", nullable = false)
    @JsonIgnoreProperties({"itens", "veiculo", "oficina"})
    private Agendamento agendamento;

    @Column(name = "data_servico", nullable = false)
    private LocalDate dataServico;

    @Column(name = "quilometragem_registro", nullable = false)
    private Integer quilometragemRegistro;

    @Column(name = "descricao_servico", nullable = false, columnDefinition = "TEXT")
    private String descricaoServico;

    @Column(name = "validado_oficina", nullable = false)
    private Boolean validadoOficina = false;

    @Column(name = "data_validacao")
    private LocalDateTime dataValidacao;

    public GaragemVirtual() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }

    public Integer getQuilometragemRegistro() {
        return quilometragemRegistro;
    }

    public void setQuilometragemRegistro(Integer quilometragemRegistro) {
        this.quilometragemRegistro = quilometragemRegistro;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Boolean getValidadoOficina() {
        return validadoOficina;
    }

    public void setValidadoOficina(Boolean validadoOficina) {
        this.validadoOficina = validadoOficina;
    }

    public LocalDateTime getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(LocalDateTime dataValidacao) {
        this.dataValidacao = dataValidacao;
    }
}