package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.Endereco.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.Endereco.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/enderecos")
@Tag(name = "Endereços", description = "API de gerenciamento de endereços dos clientes")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Criar novo endereço")
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> salvar(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(enderecoService.salvar(enderecoRequestDTO));
    }

    @Operation(summary = "Listar todos os endereços")
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> listar() {
        return ResponseEntity.ok(enderecoService.listar());
    }

    @Operation(summary = "Atualizar endereço")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(enderecoService.update(id, enderecoRequestDTO));
    }

    @Operation(summary = "Remover Endereco")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        enderecoService.remover(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Consultar Endereço")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> consultar(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.buscarPorId(id));
    }
}
