package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.ecommerce.model.entity.Categoria;
import pers.kaslufl.ecommerce.model.entity.Produto;

import java.util.List;

public class CategoriaRepository {
    private JdbcTemplate jdbcTemplate;;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        return jdbcTemplate.query(
                "select * from produto p inner join categoriaproduto cp on cp.produtoid = p.id" +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ?",
                new ProdutoMapper(),
                id
        );
    }

    public List<Produto> searchProducts(int id, String nome) {
        return jdbcTemplate.query(
                "select * from produto p inner join categoriaproduto cp on cp.produtoid = p.id" +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ? and p.nome = ?",
                new ProdutoMapper(),
                id,
                nome
        );
    }

    public List<Produto> searchProducts(int id, Float valorMinimo, Float valorMaximo) {
        return jdbcTemplate.query(
                "select * from produto p inner join categoriaproduto cp on cp.produtoid = p.id" +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ? " +
                        "and p.valorunitario between ? and ?",
                new ProdutoMapper(),
                id,
                valorMinimo,
                valorMaximo
        );
    }

    public List<Produto> searchProducts(int id, String nome, Float valorMinimo, Float valorMaximo) {
        return jdbcTemplate.query(
                "select * from produto p inner join categoriaproduto cp on cp.produtoid = p.id" +
                        "inner join categoria c on c.id = cp.categoriaid where c.id = ? and p.nome = ?" +
                        "and p.valorunitario between ? and ?",
                new ProdutoMapper(),
                id,
                nome,
                valorMinimo,
                valorMaximo
        );
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
