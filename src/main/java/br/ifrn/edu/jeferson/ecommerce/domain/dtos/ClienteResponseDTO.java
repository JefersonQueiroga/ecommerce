package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "DTO para resposta de cliente")
public class ClienteResponseDTO {

    @Schema(description = "ID do Cliente", example = "1")
    private final Long id;

    @Schema(description = "Nome do Cliente", example = "Jeferson Queiroga")
    private final String nome;

    @Schema(description = "E-mail do cliente", example = "jefersonqueiroga@gmail.com")
    private final String email;

    @Schema(description = "Telefone do cliente", example = "(84)99930-6223")
    private final String telefone;
}
