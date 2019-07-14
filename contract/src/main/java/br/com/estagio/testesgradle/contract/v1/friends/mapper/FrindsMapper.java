package br.com.estagio.testesgradle.contract.v1.friends.mapper;

import br.com.estagio.testesgradle.contract.v1.friends.model.FriendsResponse;
import br.com.estagio.testesgradle.contract.v1.friends.model.FriendsSave;
import br.com.estagio.testesgradle.friends.model.Friends;
import br.com.estagio.testesgradle.user.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FrindsMapper {

    public List<FriendsResponse> mapToFriendsResponseList(List<Friends> list){
        List<FriendsResponse> listUsers = new ArrayList<>();
        for(Friends friends : list){
            listUsers.add(FriendsResponse.builder().build().builder()
                        .idAmigo(friends.getIdAmigo())
                        .nomeAmigo(friends.getNomeAmigo())
                        .build());
        }
        return  listUsers;
    }

    public Friends mapToFriends(FriendsSave friendsSave){
        return  Friends.builder()
                .idAmigo(friendsSave.getIdAmigo())
                .nomeAmigo(friendsSave.getNomeAmigo())
                .build();
    }

}
