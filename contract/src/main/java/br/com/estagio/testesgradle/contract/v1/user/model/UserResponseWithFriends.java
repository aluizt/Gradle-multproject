package br.com.estagio.testesgradle.contract.v1.user.model;

import br.com.estagio.testesgradle.friends.model.Friends;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseWithFriends {
    private String idUser;
    private String name;
    private String cpf;

    private List<Friends> listFriends;

}
