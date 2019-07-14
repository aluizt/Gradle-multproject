package br.com.estagio.testesgradle.contract.v1.user.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String idUser;
    private String name;
    private String cpf;
}
