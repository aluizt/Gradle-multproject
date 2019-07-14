package br.com.estagio.testesgradle.contract.v1.friends.facade;

import br.com.estagio.testesgradle.contract.v1.friends.mapper.FrindsMapper;
import br.com.estagio.testesgradle.contract.v1.friends.model.FriendsResponse;
import br.com.estagio.testesgradle.contract.v1.friends.model.FriendsSave;
import br.com.estagio.testesgradle.friends.exception.ValidadeFriendsException;
import br.com.estagio.testesgradle.friends.service.FriendsService;
import br.com.estagio.testesgradle.friends.validation.FriendsValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrindsFacade {

    private final FriendsService friendsService;
    private final FrindsMapper frindsMapper;
    private final FriendsValidation friendsValidation;

    @Autowired
    public FrindsFacade(FriendsService friendsService,
                        FrindsMapper frindsMapper,
                        FriendsValidation friendsValidation) {

        this.friendsService = friendsService;
        this.frindsMapper = frindsMapper;
        this.friendsValidation = friendsValidation;
    }

    public List<FriendsResponse> listAllFrinds(String id){
        return frindsMapper.mapToFriendsResponseList(friendsService.listAllFrinds(id));
    }

    public List<FriendsResponse> includeFrinds(String id, FriendsSave friendsSave) {
        return frindsMapper.mapToFriendsResponseList(
                friendsService.includeFrinds(id,
                       friendsValidation.validateFriend(frindsMapper.mapToFriends(friendsSave))));
    }

    public List<FriendsResponse> removeFriend(String idUser,String idFriend){
        return frindsMapper.mapToFriendsResponseList(friendsService.remove(idUser,idFriend));
    }
}
