package br.com.estagio.testesgradle.contract.v1.user.mapper;

import br.com.estagio.testesgradle.contract.v1.user.model.UserResponse;
import br.com.estagio.testesgradle.contract.v1.user.model.UserResponseWithFriends;
import br.com.estagio.testesgradle.contract.v1.user.model.UserSave;
import br.com.estagio.testesgradle.contract.v1.user.model.UserUpdate;
import org.springframework.stereotype.Service;
import br.com.estagio.testesgradle.user.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .idUser(user.getIdUsuario())
                .name(user.getNomeUsuario())
                .cpf(user.getCpf())
                .build();
    }

    public UserResponseWithFriends mapToUserResponseWithFriends(User user){
        return UserResponseWithFriends.builder()
                .idUser(user.getIdUsuario())
                .name(user.getNomeUsuario())
                .cpf(user.getCpf())
                .listFriends(user.getAmigos())
                .build();
    }

    public List<UserResponseWithFriends> mapToUserResponseWithFriendsList(List<User> list){
     List<UserResponseWithFriends> listFriends = new ArrayList<>();
         for(User user : list){
             listFriends.add(UserResponseWithFriends.builder()
                            .idUser(user.getIdUsuario())
                            .name(user.getNomeUsuario())
                            .cpf(user.getCpf())
                            .listFriends(user.getAmigos())
                            .build());
         }
         return listFriends;
    }

    public User mapToUser(UserSave userSave){
        return User.builder()
                .nomeUsuario(userSave.getName())
                .password(userSave.getPassword())
                .cpf(userSave.getCpf())
                .build();
    }

    public List<UserResponse> mapToUserResponseList(List<User> userList){
        List<UserResponse> list = new ArrayList<>();
        for(User user:userList) {
            list.add(UserResponse.builder()
                    .idUser(user.getIdUsuario())
                    .name(user.getNomeUsuario())
                    .cpf(user.getCpf())
                    .build());
        }
        return list;
    }

    public User mapToUser(UserUpdate userUpdate,String id){
        return User.builder()
                .idUsuario(id)
                .nomeUsuario(userUpdate.getName())
                .password(userUpdate.getPassword())
                .cpf(userUpdate.getCpf())
                .build();
    }

}






















