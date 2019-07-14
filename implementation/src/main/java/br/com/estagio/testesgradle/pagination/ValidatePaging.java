package br.com.estagio.testesgradle.pagination;

import br.com.estagio.testesgradle.pagination.exceptions.InvalidPageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatePaging {
    public boolean validate(int listSize, int page){
        if(page <0){
            throw new InvalidPageException("Quantidade de paginas não pode ser menor do que zero ");
        }
        if(page >= listSize){
            throw new InvalidPageException("Quantidade de paginas informada é maior do que a quantidade existente");
        }
        return true;
    }
}
