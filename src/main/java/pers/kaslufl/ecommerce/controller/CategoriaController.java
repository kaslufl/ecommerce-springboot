package pers.kaslufl.ecommerce.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import pers.kaslufl.ecommerce.model.entity.Produto;
import pers.kaslufl.ecommerce.model.repository.CategoriaRepository;
import pers.kaslufl.ecommerce.model.repository.ProdutoBadRequestException;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(JdbcTemplate jdbcTemplate) {
        this.categoriaRepository = new CategoriaRepository(jdbcTemplate);
    }

    @GetMapping("/{id}/produto")
    public List<Produto> searchProducts(
            @PathVariable int id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Float valorMinimo,
            @RequestParam(required = false) Float valorMaximo
    ) {
        if ((nome == null) && ((valorMinimo == null) || (valorMaximo == null))){
            return categoriaRepository.searchProducts(id);
        }
        if ((nome != null) && ((valorMinimo == null) || (valorMaximo == null))){
            return categoriaRepository.searchProducts(id, nome);
        }
        if ((nome == null) && ((valorMinimo != null) && (valorMaximo != null))){
            return categoriaRepository.searchProducts(id, valorMinimo, valorMaximo);
        }
        return categoriaRepository.searchProducts(id, nome, valorMinimo, valorMaximo);
    }
}
