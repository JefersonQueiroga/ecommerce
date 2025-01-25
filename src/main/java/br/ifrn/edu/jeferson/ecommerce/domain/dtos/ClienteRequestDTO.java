package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "DTO para requisição de cliente")
public class ClienteRequestDTO {

    @Schema(description = "Nome do Cliente", example = "Jeferson Queiroga")
    @NotBlank(message = "Nome é obrigatório")
    private final String nome;

    @Schema(description = "E-mail do cliente", example = "jefersonqueiroga@gmail.com")
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private final String email;

    @Schema(description = "CPF do cliente", example = "060.988.194-74")
    @NotBlank(message = "CPF é obrigatório")
    private final String cpf;

    @Schema(description = "Telefone do cliente", example = "(84)99930-6223")
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}", message = "Formato de telefone inválido. Exemplo: (84)99930-6223")
    private final String telefone;
}
