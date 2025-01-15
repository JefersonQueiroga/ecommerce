package br.ifrn.edu.jeferson.ecommerce.domain.dtos.Endereco;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO de Resposta de Endereço")
public class EnderecoResponseDTO {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "CEP")
    private String cep;

    @Schema(description = "Estado")
    private String estado;

    @Schema(description = "Cidade")
    private String cidade;

    @Schema(description = "Bairro")
    private String bairro;

    @Schema(description = "Rua")
    private String rua;

    @Schema(description = "Número")
    private Integer numero;

    @Schema(description = "ID de Cliente")
    private String cliente;

}