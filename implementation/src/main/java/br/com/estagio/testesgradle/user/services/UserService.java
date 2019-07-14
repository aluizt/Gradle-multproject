package br.com.estagio.testesgradle.user.services;

import br.com.estagio.testesgradle.user.exception.UserNotFoundException;
import org.springframework.data.domain.*;
import br.com.estagio.testesgradle.user.model.User;
import br.com.estagio.testesgradle.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService( UserRepository repo) {
        this.repo = repo;
    }

    public List<User> listarTodos() {
        List<User> lista = repo.findAll();
        if(lista.isEmpty()){
            throw new UserNotFoundException("O arquivo não possui usuarios ");
        }
        return lista;
    }


    public Page<User> listAllUserWithPagination(Integer count, Integer page) {
        PageRequest pageRequest = new PageRequest(page,count);

        List<User> lista = this.repo.findAll();

        int max = (count*(page+1)>lista.size())? lista.size():count*(page+1);

        Page<User> pageUsers =
                new PageImpl<User>(lista.subList(page*count,max),
                        pageRequest,lista.size());

        return  pageUsers;
    }


    public User consultarId(String id)  {

        Optional<User> optional = this.repo.findById(id);

        return optional.orElseThrow(() -> new UserNotFoundException("Usuario não esta cadastrado !!"));

    }


    public User incluirUsuario(User usuario) {

        return this.repo.save(usuario);
    }

    public User alterarUsuario(User u)  {

        this.consultarId(u.getIdUsuario());

        return this.repo.save(u);

    }

    public void deletarUsuario(String id) {
        this.consultarId(id);
        this.repo.deleteById(id);
    }

    public void deletarTodos() {
        this.repo.deleteAll();
    }


}
