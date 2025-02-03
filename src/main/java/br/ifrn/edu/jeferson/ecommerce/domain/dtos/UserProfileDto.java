package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.Data;

@Data
public class UserProfileDto {
    private String nomeDaMae;
    private String cidade;
    private String estado;
    private String celular;
}
