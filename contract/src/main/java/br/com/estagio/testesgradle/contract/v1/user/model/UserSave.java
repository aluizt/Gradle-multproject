package br.com.estagio.testesgradle.contract.v1.user.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSave {
    private String name;
    private String password;
    private String cpf;
}
