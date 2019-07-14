package br.com.estagio.testesgradle.contract.v1.user.facade;

import br.com.estagio.testesgradle.contract.v1.user.mapper.UserMapper;
import br.com.estagio.testesgradle.contract.v1.user.model.UserResponse;
import br.com.estagio.testesgradle.contract.v1.user.model.UserResponseWithFriends;
import br.com.estagio.testesgradle.contract.v1.user.model.UserSave;
import br.com.estagio.testesgradle.contract.v1.user.model.UserUpdate;
import br.com.estagio.testesgradle.contract.v1.user.pagination.UserPagination;
import br.com.estagio.testesgradle.user.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import br.com.estagio.testesgradle.user.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

@Service
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserPagination userPagination;
    private final UserValidation userValidation;

    @Autowired
    public UserFacade(UserService userService, UserMapper userMapper, UserPagination userPagination, UserValidation userValidation) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userPagination = userPagination;
        this.userValidation = userValidation;
    }

    public List<UserResponse> getListAllUsers(){
        return userMapper.mapToUserResponseList(userService.listarTodos());
    }

    public Page<UserResponse> getListAllUsersWithPaging(int page,int count){
        return userPagination.pagingUserResponse(
                userMapper.mapToUserResponseList(userService.listarTodos()),page,count);
    }

    public List<UserResponseWithFriends> getListAllUsersWithFriends(){
        return userMapper.mapToUserResponseWithFriendsList(userService.listarTodos());
    }

    public UserResponse getUserById(String id){
        return userMapper.mapToUserResponse(userService.consultarId(id));
    }

    public UserResponse save(UserSave userSave){
        return userMapper.mapToUserResponse(
                userService.incluirUsuario(
                        userValidation.validateUser(
                                userMapper.mapToUser(userSave))));
    }

    public UserResponse update(UserUpdate userUpdate, String id){
        return userMapper.mapToUserResponse(
               userService.alterarUsuario(
               userMapper.mapToUser(userUpdate,id)));
    }

    public void remove(String id){
        userService.deletarUsuario(id);
    }
}
