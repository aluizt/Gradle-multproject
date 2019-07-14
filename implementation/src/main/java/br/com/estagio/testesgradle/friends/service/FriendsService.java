package br.com.estagio.testesgradle.friends.service;

import br.com.estagio.testesgradle.friends.exception.FrindsNotFoundException;
import br.com.estagio.testesgradle.friends.model.Friends;
import br.com.estagio.testesgradle.user.exception.UserNotFoundException;
import br.com.estagio.testesgradle.user.model.User;
import br.com.estagio.testesgradle.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    private final UserRepository repo;

    @Autowired
    public FriendsService(UserRepository userRepository) {
        this.repo=userRepository;
    }

    public List<Friends> listAllFrinds(String id){
        Optional optional=this.repo.findById(id);
        if(optional.isPresent()){
            User user= (User) optional.get();
            if(user.getAmigos()==null){
                throw new FrindsNotFoundException("Não existe amigos cadastrados para o usuario "+id);
            }
            return user.getAmigos();
        }
        else{
            throw new UserNotFoundException("Usuario "+id+" não cadastrado");
        }
    }

    public List<Friends> includeFrinds(String id, Friends amigo)  {

        Optional optionalUsuario =this.repo.findById(id);
        Optional optionalAmigo   =this.repo.findById(amigo.getIdAmigo());

        if(optionalUsuario.isPresent()) {
            User usuario  = (User) optionalUsuario.get();
            if(optionalAmigo.isPresent()){

                User usuario2 = (User) optionalAmigo.get();

                List<Friends> listaAmigos = verificaNull(usuario);

                listaAmigos.add(amigo);
                usuario.setAmigos(listaAmigos);

                this.repo.save(usuario);

                Friends novoAmigo = new Friends();

                novoAmigo.setIdAmigo(usuario.getIdUsuario());
                novoAmigo.setNomeAmigo(usuario.getNomeUsuario());

                List<Friends> listaAmigos2 = verificaNull(usuario2);

                listaAmigos2.add(novoAmigo);
                usuario2.setAmigos(listaAmigos2);
                this.repo.save(usuario2);

                return listaAmigos;

            }else{
                throw new FrindsNotFoundException("Amigo "+id+" não foi localizado");
            }

        }else{
            throw new FrindsNotFoundException("Usuario "+id+" não foi localizado");
        }

    }

    public List<Friends> remove(String idUsuario, String idAmigo){

        List<Friends> lista = removeFrind(idUsuario,idAmigo);
        this.removeFrind(idAmigo,idUsuario);

        return lista;

    }

    private List<Friends> removeFrind(String id01, String id02){

        Optional optional=this.repo.findById(id01);
        User usuario = new User();

        if(optional.isPresent()){
            usuario= (User) optional.get();
        }else{
            throw new FrindsNotFoundException("Usuario não localizado");
        }

        if(usuario.getAmigos()==null){
            throw new FrindsNotFoundException("Usuario não possui amigas compartilhados");
        }

        List<Friends> listaAmigos = usuario.getAmigos();

        int tamanho = listaAmigos.size();

        listaAmigos.removeIf(amigo -> amigo.getIdAmigo().equals(id02));

        if(listaAmigos.size()==tamanho){
            throw new FrindsNotFoundException("Amigo informado não localizado");
        }

        usuario.setAmigos(listaAmigos);

        this.repo.save(usuario);

        return listaAmigos;
    }

    private List<Friends> verificaNull(User usuario){

        List<Friends> lista = new ArrayList<>();

        if(usuario.getAmigos()!=null) {
            lista=usuario.getAmigos();
        }
        return lista;
    }

}
