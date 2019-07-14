package br.com.estagio.testesgradle.contract.v1.friends.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendsResponse {
    private String idAmigo;
    private String nomeAmigo;
}
