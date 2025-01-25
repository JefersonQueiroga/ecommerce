package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteResponseDTO salvar(ClienteRequestDTO clienteDto) {
        var cliente =  clienteMapper.toEntity(clienteDto);

        if ( clienteRepository.existsByCpf(cliente.getCpf()) ) {
            throw new BusinessException("Já existe uma pessoal com esse CPF cadastrado");
        }

        if ( clienteRepository.existsByEmail(cliente.getEmail()) ) {
            throw new BusinessException("Já existe uma pessoal com esse email cadastrado");
        }

        clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(cliente);
    }

    public Page<ClienteResponseDTO>  findAllClient(Pageable pageable) {
           return clienteRepository.findAll(pageable)
                .map(clienteMapper::toResponseDTO);

    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteAtualizadoDto) {
        Cliente clienteExistente  = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado"));


        if (clienteRepository.existsByCpf(clienteAtualizadoDto.getCpf()) &&
                !clienteExistente.getCpf().equals(clienteAtualizadoDto.getCpf())) {
            throw new BusinessException("CPF já cadastrado: " + clienteAtualizadoDto.getCpf());
        }

        // Verificar se o email já pertence a outro cliente
        if (clienteRepository.existsByEmail(clienteAtualizadoDto.getEmail()) &&
                !clienteExistente.getEmail().equals(clienteAtualizadoDto.getEmail())) {
            throw new BusinessException("Email já cadastrado: " + clienteAtualizadoDto.getEmail());
        }

        clienteMapper.updateEntityFromDTO(clienteAtualizadoDto, clienteExistente);

        var clienteAlterado = clienteRepository.save(clienteExistente);
        return clienteMapper.toResponseDTO(clienteAlterado);
    }

}
