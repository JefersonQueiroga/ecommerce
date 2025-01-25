package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente.")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteDto) {
        return ResponseEntity.ok( clienteService.salvar(clienteDto) );
    }

    @Operation(summary = "Listar clientes com paginação", description = "Retorna uma lista paginada de clientes.")
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> listar(
            @Parameter(description = "Número da página: ", schema = @Schema(defaultValue = "0"))
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Parameter(description = "Tamanho da página", schema = @Schema(defaultValue = "10"))
            @RequestParam(value = "size", defaultValue = "10") int size
            ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ClienteResponseDTO> clientes = clienteService.findAllClient(pageable);
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Atualizar um cliente", description = "Atualiza um cliente existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO clienteDto) {
        return ResponseEntity.ok(clienteService.atualizar(id, clienteDto));
    }


}
