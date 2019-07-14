package br.com.estagio.testesgradle.user.validation;

import br.com.estagio.testesgradle.user.exception.ValidateUserException;
import br.com.estagio.testesgradle.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidation {

    public User validateUser(User user){
        if(user.getNomeUsuario().isBlank()){
            throw new ValidateUserException("O nome não pode ser vazio");
        }
        if(user.getCpf().isBlank()){
            throw new ValidateUserException("O cpf não pode ficar vazio");
        }
        if(user.getPassword().isBlank()){
            throw new ValidateUserException("O password tem de ser informado");
        }
        return user;
    }
}
