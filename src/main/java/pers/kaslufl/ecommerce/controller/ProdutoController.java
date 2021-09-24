package pers.kaslufl.ecommerce.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pers.kaslufl.ecommerce.model.entity.Produto;
import pers.kaslufl.ecommerce.model.repository.ProdutoBadRequestException;
import pers.kaslufl.ecommerce.model.repository.ProdutoNotFoundException;
import pers.kaslufl.ecommerce.model.repository.ProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(JdbcTemplate jdbcTemplate) {
        this.produtoRepository = new ProdutoRepository(jdbcTemplate);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Produto create(@RequestBody Produto produto) throws Exception {
        try {
            produto.setDataCadastro(LocalDateTime.now());
            produto.setDataUltimaAtualizacao(LocalDateTime.now());

            return produtoRepository.create(produto);
        }
        catch (DataIntegrityViolationException e){
            throw new ProdutoBadRequestException();
        }
    }

    @GetMapping("/{id}")
    public Produto search(@PathVariable int id) {
        try {
            return produtoRepository.search(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ProdutoNotFoundException();
        }
    }

    @GetMapping
    public List<Produto> search(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Float valorMinimo,
            @RequestParam(required = false) Float valorMaximo
    ) {
        if ((nome == null) && ((valorMinimo == null) || (valorMaximo == null))){
            throw new ProdutoBadRequestException();
        }
        if ((nome != null) && ((valorMinimo == null) || (valorMaximo == null))){
            return produtoRepository.search(nome);
        }
        if ((nome == null) && ((valorMinimo != null) && (valorMaximo != null))){
            return produtoRepository.search(valorMinimo, valorMaximo);
        }
        return produtoRepository.search(nome, valorMinimo, valorMaximo);
    }
}
