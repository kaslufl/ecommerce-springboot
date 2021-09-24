package pers.kaslufl.ecommerce.model.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException() {
        super("Produto não encontrado!");
    }
}
