package com.baratex.api.controller;

import com.baratex.api.dto.MarcaDTO;
import com.baratex.api.dto.ModeloVeiculoDTO;
import com.baratex.api.repository.MarcaRepository;
import com.baratex.api.repository.ModeloVeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modelos-veiculos")
@CrossOrigin(origins = "*")
public class ModeloVeiculoController {

    private final MarcaRepository marcaRepository;
    private final ModeloVeiculoRepository modeloRepository;

    public ModeloVeiculoController(MarcaRepository marcaRepository, ModeloVeiculoRepository modeloRepository) {
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<MarcaDTO>> buscarMarcas(@RequestParam(defaultValue = "") String q) {
        List<MarcaDTO> marcas = marcaRepository.findByNomeContainingIgnoreCaseOrderByNome(q)
                .stream()
                .map(m -> new MarcaDTO(m.getId(), m.getNome()))
                .toList();

        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/modelos")
    public ResponseEntity<List<ModeloVeiculoDTO>> buscarModelos(@RequestParam UUID marcaId,
                                                                @RequestParam(defaultValue = "") String q) {
        List<ModeloVeiculoDTO> modelos = modeloRepository
                .findByMarcaIdAndNomeModeloContainingIgnoreCaseOrderByNomeModeloAsc(marcaId, q)
                .stream()
                .map(m -> new ModeloVeiculoDTO(
                        m.getId(),
                        m.getNomeModelo(),
                        m.getAnoInicio(),
                        m.getAnoFim()
                ))
                .toList();

        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/anos")
    public ResponseEntity<List<Integer>> buscarAnos(@RequestParam UUID modeloId) {
        return modeloRepository.findById(modeloId).map(veiculo -> {
            List<Integer> anos = new ArrayList<>();
            int anoFim = veiculo.getAnoFim() != null ? veiculo.getAnoFim() : Year.now().getValue();

            for (int ano = anoFim; ano >= veiculo.getAnoInicio(); ano--) {
                anos.add(ano);
            }

            return ResponseEntity.ok(anos);
        }).orElse(ResponseEntity.notFound().build());
    }
}