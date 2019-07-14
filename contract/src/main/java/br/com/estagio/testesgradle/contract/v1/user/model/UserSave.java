package br.com.estagio.testesgradle.contract.v1.user.model;

import lombok.*;

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
