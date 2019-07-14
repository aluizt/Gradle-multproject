package br.com.estagio.testesgradle.contract.v1.friends.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendsSave {
    private String idAmigo;
    private String nomeAmigo;
}
