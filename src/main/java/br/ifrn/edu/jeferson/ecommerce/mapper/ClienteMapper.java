package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponseDTO toResponseDTO(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteRequestDTO dto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ClienteRequestDTO dto, @MappingTarget Cliente cliente);


}
