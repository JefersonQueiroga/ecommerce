package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.Endereco.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.Endereco.EnderecoResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target="id", ignore=true)
    @Mapping(target="cliente", ignore = true)
    Endereco toEntity(EnderecoRequestDTO enderecoDTO);

    @Mapping(target = "cliente", source = "cliente.nome")
    EnderecoResponseDTO toResponseDTO(Endereco endereco);

    List<EnderecoResponseDTO> toEnderecosDTOList(List<Endereco> enderecos);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    void updateEntityFromDTO(EnderecoRequestDTO enderecoDTO, @MappingTarget Endereco endereco);

}
