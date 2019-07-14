package br.com.estagio.testesgradle.contract.v1.user.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdate {
    private String idUser;
    private String password;
    private String name;
    private String cpf;
}
