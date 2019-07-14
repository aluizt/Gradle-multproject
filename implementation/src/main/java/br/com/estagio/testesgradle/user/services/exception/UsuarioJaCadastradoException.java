package br.com.estagio.testesgradle.user.services.exception;


public class UsuarioJaCadastradoException extends RuntimeException{

    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
}
