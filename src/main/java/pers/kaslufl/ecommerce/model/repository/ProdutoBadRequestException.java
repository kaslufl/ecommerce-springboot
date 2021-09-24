package pers.kaslufl.ecommerce.model.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoBadRequestException extends RuntimeException{
    public ProdutoBadRequestException() {
        super("Parâmetros errados ou faltando!");
    }
}
