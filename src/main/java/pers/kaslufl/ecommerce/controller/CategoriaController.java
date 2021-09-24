package pers.kaslufl.ecommerce.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.kaslufl.ecommerce.model.entity.Produto;
import pers.kaslufl.ecommerce.model.repository.CategoriaRepository;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(JdbcTemplate jdbcTemplate) {
        this.categoriaRepository = new CategoriaRepository(jdbcTemplate);
    }

    @GetMapping("/{id}")
    public List<Produto> searchProducts(@PathVariable int id) {
        return categoriaRepository.searchProducts(id);
    }
}
