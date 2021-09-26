package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.ecommerce.model.entity.Categoria;
import pers.kaslufl.ecommerce.model.entity.Produto;
import pers.kaslufl.ecommerce.model.entity.Promocao;
import pers.kaslufl.ecommerce.model.entity.PromocaoItem;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {
    private JdbcTemplate jdbcTemplate;
    private ProdutoRepository produtoRepository;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.produtoRepository = new ProdutoRepository(jdbcTemplate);
    }

    public List<Categoria> search(){
        return jdbcTemplate.query(
                "select * from categoria",
                new CategoriaMapper()
        );
    }

    public Categoria search(int id){
        return jdbcTemplate.queryForObject(
                "select * from produto where id = ?",
                new CategoriaMapper(),
                id
        );
    }

    public List<Produto> searchProducts(int id) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select p.* from produto p inner join categoriaproduto cp on cp.produtoid = p.id " +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ?",
                new ProdutoMapper(),
                id
        );
        List<Produto> response = produtoRepository.addProdutoRelationships(produtoList);
        return response;
    }

    public List<Produto> searchProducts(int id, String nome) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select p.* from produto p inner join categoriaproduto cp on cp.produtoid = p.id " +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ? and p.nome like ?",
                new ProdutoMapper(),
                id,
                "%" + nome + "%"
        );
        List<Produto> response = produtoRepository.addProdutoRelationships(produtoList);
        return response;
    }

    public List<Produto> searchProducts(int id, Float valorMinimo, Float valorMaximo) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select p.* from produto p inner join categoriaproduto cp on cp.produtoid = p.id " +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ? " +
                        "and p.valorunitario between ? and ?",
                new ProdutoMapper(),
                id,
                valorMinimo,
                valorMaximo
        );
        List<Produto> response = produtoRepository.addProdutoRelationships(produtoList);
        return response;
    }

    public List<Produto> searchProducts(int id, String nome, Float valorMinimo, Float valorMaximo) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select p.* from produto p inner join categoriaproduto cp on cp.produtoid = p.id " +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ? and p.nome like ?" +
                        "and p.valorunitario between ? and ?",
                new ProdutoMapper(),
                id,
                "%" + nome + "%",
                valorMinimo,
                valorMaximo
        );
        List<Produto> response = produtoRepository.addProdutoRelationships(produtoList);
        return response;
    }

    public Categoria create(Categoria categoria) throws Exception {
        int insert = jdbcTemplate.update(
                "insert into categoria(nome, descricao, imagemsimbolourl) values (?, ?, ?)",
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getImagemSimboloUrl()
        );
        if( insert == 1 ) {
            int id = jdbcTemplate.queryForObject(
                    "select max(id) from categoria",
                    Integer.class
            );
            categoria.setId(id);
            return categoria;
        }
        throw new Exception("Categoria não foi cadastrada!");
    }

    public Categoria update(Categoria categoria) {
        int update = jdbcTemplate.update(
                "update categoria set nome = ?, descricao = ?, imagemdimbolourl = ? where id = ?",
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getImagemSimboloUrl(),
                categoria.getId()
        );
        if( update == 1 ) {
            System.out.println("Categoria:(ID - "+ categoria.getId() + " ) " + categoria.getNome() + " foi atualizada!");
        }
        return categoria;
    }

    public void delete(int id) {
        int delete = jdbcTemplate.update("delete from categoria where id = ?",id);
        if( delete == 1 ) {
            System.out.println("Categoria:(ID - " + id + ") foi deletada!");
        }
    }
}
