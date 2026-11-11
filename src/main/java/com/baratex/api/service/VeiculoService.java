package com.baratex.api.service;

import com.baratex.api.dto.VeiculoRequestDTO;
import com.baratex.api.dto.VeiculoResponseDTO;
import com.baratex.api.model.ModeloVeiculo;
import com.baratex.api.model.Motorista;
import com.baratex.api.model.TipoUsuario;
import com.baratex.api.model.Usuario;
import com.baratex.api.model.Veiculo;
import com.baratex.api.repository.ModeloVeiculoRepository;
import com.baratex.api.repository.MotoristaRepository;
import com.baratex.api.repository.UsuarioRepository;
import com.baratex.api.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ModeloVeiculoRepository modeloVeiculoRepository;
    private final MotoristaRepository motoristaRepository;
    private final UsuarioRepository usuarioRepository;

    public VeiculoService(VeiculoRepository veiculoRepository,
                          ModeloVeiculoRepository modeloVeiculoRepository,
                          MotoristaRepository motoristaRepository,
                          UsuarioRepository usuarioRepository) {
        this.veiculoRepository = veiculoRepository;
        this.modeloVeiculoRepository = modeloVeiculoRepository;
        this.motoristaRepository = motoristaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public VeiculoResponseDTO cadastrarVeiculo(VeiculoRequestDTO request, String identificadorUsuario) {
        Motorista motorista = motoristaRepository.findByIdentificadorUsuario(identificadorUsuario)
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepository.findByIdentificador(identificadorUsuario)
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

                    if (usuario.getTipoUsuario() != TipoUsuario.MOTORISTA) {
                        throw new RuntimeException("Apenas usuários do tipo MOTORISTA podem cadastrar veículos.");
                    }

                    Motorista novoMotorista = new Motorista();
                    novoMotorista.setUsuario(usuario);
                    novoMotorista.setCpf("00000000000");
                    return motoristaRepository.save(novoMotorista);
                });

        ModeloVeiculo modelo = modeloVeiculoRepository.findById(request.modeloId())
                .orElseThrow(() -> new RuntimeException("Modelo de veículo não encontrado."));

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(request.placa().toUpperCase());
        veiculo.setAno(request.ano());
        veiculo.setCor(request.cor());
        veiculo.setModelo(modelo);
        veiculo.setMotorista(motorista);

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        return new VeiculoResponseDTO(
                veiculoSalvo.getId(),
                veiculoSalvo.getPlaca(),
                veiculoSalvo.getModelo().getMarca().getNome(),
                veiculoSalvo.getModelo().getNome(),
                veiculoSalvo.getAno(),
                veiculoSalvo.getCor()
        );
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarMeusVeiculos(String identificadorUsuario) {
        Motorista motorista = motoristaRepository.findByIdentificadorUsuario(identificadorUsuario)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado."));

        return veiculoRepository.findByMotoristaId(motorista.getId())
                .stream()
                .map(v -> new VeiculoResponseDTO(
                        v.getId(),
                        v.getPlaca(),
                        v.getModelo().getMarca().getNome(),
                        v.getModelo().getNome(),
                        v.getAno(),
                        v.getCor()
                ))
                .toList();
    }
}