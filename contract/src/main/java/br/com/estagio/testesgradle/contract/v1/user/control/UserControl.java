package br.com.estagio.testesgradle.contract.v1.user.control;

import br.com.estagio.testesgradle.contract.v1.user.model.UserResponse;
import br.com.estagio.testesgradle.contract.v1.user.model.UserResponseWithFriends;
import br.com.estagio.testesgradle.contract.v1.user.model.UserSave;
import br.com.estagio.testesgradle.contract.v1.user.model.UserUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.estagio.testesgradle.contract.v1.user.facade.UserFacade;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
@Api(value = "Users")
public class UserControl {

    private final UserFacade userFacade;

    public UserControl(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    @ApiOperation(value = "Retorna todos os usuarios cadastrados ... " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, todos os usuarios foram listados"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso"),
            @ApiResponse(code = 404, message = "Não foi encontrado os usuarios")}
    )
    public ResponseEntity<List<UserResponse>> listAllUsers(){
        return new ResponseEntity<>(userFacade.getListAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/friends")
    @ApiOperation(value = "Retorna todos os usuarios cadastrados com sua lista de amigos ... " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, todos os usuarios foram listados"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso"),
            @ApiResponse(code = 404, message = "Não foi encontrado os usuarios")}
    )
    public ResponseEntity<List<UserResponseWithFriends>> listAllUserWithFriends(){
        return new ResponseEntity<>(userFacade.getListAllUsersWithFriends(),HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    @ApiOperation(value = "Retorna o usuario atraves do id ... " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, o usuario foi encontrado"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso"),
            @ApiResponse(code = 404, message = "Não foi encontrado este usuario")}
    )
    public ResponseEntity<UserResponse> listUserById(@PathVariable(value="id")String id){
        return new ResponseEntity<>(userFacade.getUserById(id),HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Inclui um novo usuario ... " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created, registrado novo usuario com sucesso"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso")}
    )
    public ResponseEntity<UserResponse> insertUser(@RequestBody UserSave userSave){
        return new ResponseEntity<>(userFacade.save(userSave),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Efetua alteração em um usuario ... " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, dados alterados "),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso"),
            @ApiResponse(code = 404, message = "Não foi encontrado este usuario")}
    )
    public ResponseEntity<UserResponse> updateUser(
                    @PathVariable(value = "id")String id,
                    @RequestBody UserUpdate userUpdate){
        return new ResponseEntity<>(userFacade.update(userUpdate,id),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove um ususario ... " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, o usuario foi removido"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso"),
            @ApiResponse(code = 404, message = "Não foi encontrado o usuario")}
    )
    public ResponseEntity remove(@PathVariable(value = "id")String id){
        userFacade.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/paging")
    @ApiOperation(value = "Lista usuarios de forma paginada " )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso, A lista foi exibida"),
            @ApiResponse(code = 401, message = "Você não esta autorizado"),
            @ApiResponse(code = 403, message = "Você não tem acesso a este recurso"),
            @ApiResponse(code = 404, message = "Não foi encontrado usuarios para exibição")}
    )
    public ResponseEntity<Page<UserResponse>> tListAllUsersWithPaging(
            @RequestParam int page,
            @RequestParam int count){

        return new ResponseEntity<>(this.userFacade.getListAllUsersWithPaging(page,count),HttpStatus.OK);
    }
}
