package br.com.estagio.testesgradle.contract.v1.user.pagination;

import br.com.estagio.testesgradle.contract.v1.user.model.UserResponse;
import br.com.estagio.testesgradle.pagination.ValidatePaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPagination {

    private final ValidatePaging validatePaging;

    @Autowired
    public UserPagination(ValidatePaging validatePaging) {
        this.validatePaging = validatePaging;
    }

    public Page<UserResponse> pagingUserResponse(List<UserResponse> listUsers, int page, int count){

        validatePaging.validate(listUsers.size(),page);

        String sortOrder = "desc";
        String sortBy = "name";
        Sort sort = new Sort(Sort.Direction.fromString(sortOrder), sortBy);

        PageRequest pageable = PageRequest.of(page, count, sort);

        int max = (count*(page+1)>listUsers.size())? listUsers.size(): count*(page+1);

        Page<UserResponse> pageList =
                new PageImpl<UserResponse>(
                        listUsers.subList(page*count, max),pageable, listUsers.size());

        return pageList;
    }
}
