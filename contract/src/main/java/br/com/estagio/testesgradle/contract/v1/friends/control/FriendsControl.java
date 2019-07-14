package br.com.estagio.testesgradle.contract.v1.friends.control;

import br.com.estagio.testesgradle.contract.v1.friends.facade.FrindsFacade;
import br.com.estagio.testesgradle.contract.v1.friends.model.FriendsResponse;
import br.com.estagio.testesgradle.contract.v1.friends.model.FriendsSave;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/friends")
@Api(value = "Friends")
public class FriendsControl {

    private final FrindsFacade frindsFacade;

    @Autowired
    public FriendsControl(FrindsFacade frindsFacade) {
        this.frindsFacade = frindsFacade;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<FriendsResponse>> listAllFrinds(@PathVariable(value = "id")String id){
        return new ResponseEntity<>(frindsFacade.listAllFrinds(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<List<FriendsResponse>> includeFriend(@PathVariable(value = "id")String id,
                                                       @RequestBody FriendsSave friendsSave){
        return new ResponseEntity<>(frindsFacade.includeFrinds(id,friendsSave),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{idUser}/{idFriend}")
    public ResponseEntity<List<FriendsResponse>> removeFriend(@PathVariable(value = "idUser")String idUser,
                                                              @PathVariable(value = "idFriend")String idFriend){
        return  new ResponseEntity<>(frindsFacade.removeFriend(idUser,idFriend),HttpStatus.NO_CONTENT);
    }
}
