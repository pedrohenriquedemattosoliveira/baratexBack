package com.baratex.api.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "modelos_veiculos")
public class ModeloVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_modelo", nullable = false)
    private String nomeModelo;

    @Column(name = "ano_inicio")
    private Integer anoInicio;

    @Column(name = "ano_fim")
    private Integer anoFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    public ModeloVeiculo() {}

    public ModeloVeiculo(UUID id, String nomeModelo, Integer anoInicio, Integer anoFim, Marca marca) {
        this.id = id;
        this.nomeModelo = nomeModelo;
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
        this.marca = marca;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    // Alias para manter compatibilidade com serviços que invoquem getNome()
    public String getNome() {
        return nomeModelo;
    }

    public Integer getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(Integer anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Integer getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(Integer anoFim) {
        this.anoFim = anoFim;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}