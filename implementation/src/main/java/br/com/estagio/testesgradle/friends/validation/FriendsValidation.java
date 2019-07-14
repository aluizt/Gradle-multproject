package br.com.estagio.testesgradle.friends.validation;

import br.com.estagio.testesgradle.friends.model.Friends;
import br.com.estagio.testesgradle.user.exception.ValidateUserException;
import org.springframework.stereotype.Service;

@Service
public class FriendsValidation {

    public Friends validateFriend(Friends friend){
        if(friend.getIdAmigo().isBlank()){
            throw new ValidateUserException("O id não pode ser vazio");
        }
        if(friend.getNomeAmigo().isBlank()){
            throw new ValidateUserException("O nome do amigo não pode ser vazio");
        }
        return friend;
    }
}
