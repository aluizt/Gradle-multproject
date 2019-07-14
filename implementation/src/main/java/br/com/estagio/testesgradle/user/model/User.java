package br.com.estagio.testesgradle.user.model;

import br.com.estagio.testesgradle.friends.model.Friends;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {
    @Id
    private String idUsuario;
    private String password;
    private String nomeUsuario;
    private String cpf;
    private List<Friends> amigos;

}
