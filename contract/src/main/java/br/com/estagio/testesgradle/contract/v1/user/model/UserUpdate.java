package br.com.estagio.testesgradle.contract.v1.user.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdate {

    private String password;
    private String name;
    private String cpf;
}
