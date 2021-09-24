package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.ecommerce.model.entity.Produto;

import java.util.List;

public class ProdutoRepository {
    private JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> search () {
        return jdbcTemplate.query(
                "select * from produto",
                new ProdutoMapper()
        );
    }

    public Produto search(int id) {
        return jdbcTemplate.queryForObject(
                "select * from produto where id = ?",
                new ProdutoMapper(),
                id
        );
    }

    public List<Produto> search (String nome) {
        return jdbcTemplate.query(
                "select * from produto where nome = ?",
                new ProdutoMapper()
        );
    }

    public List<Produto> search (Float valorMinimo, Float valorMaximo) {
        return jdbcTemplate.query(
                "select * from produto where valorunitario between ? and ?",
                new ProdutoMapper(),
                valorMinimo,
                valorMaximo
        );
    }

    public List<Produto> search (String nome, Float valorMinimo, Float valorMaximo) {
        return jdbcTemplate.query(
                "select * from produto where nome = ? and valorunitario between ? and ?",
                new ProdutoMapper(),
                nome,
                valorMinimo,
                valorMaximo
        );
    }

    public Produto create(Produto produto) throws Exception {
        int insert = jdbcTemplate.update(
            "insert into produto(nome, descricao, fotourl, datacadastro, dataultimaautalizacao, valorunitario)" +
                    " values (?, ?, ?, ?, ?, ?, ?)",
                produto.getNome(),
                produto.getDescricao(),
                produto.getFotoUrl(),
                produto.getDataCadastro(),
                produto.getDataUltimaAtualizacao(),
                produto.getValorUnitario()
        );
        if( insert == 1 ) {
            return produto;
        }
        throw new Exception("Produto não foi cadastrado!");
    }

    public Produto update(Produto produto) {
        int update = jdbcTemplate.update(
                "update produto set nome = ?, descricao = ?, fotourl = ?, datacadastro = ?, " +
                        "dataultimaautalizacao = ?, valorunitario = ? where id = ?",
                produto.getNome(),
                produto.getDescricao(),
                produto.getFotoUrl(),
                produto.getDataCadastro(),
                produto.getDataUltimaAtualizacao(),
                produto.getValorUnitario(),
                produto.getId()
        );
        if( update == 1 ) {
            System.out.println("Produto:(ID - "+ produto.getId() + " ) " + produto.getNome() + " foi atualizado!");
        }
        return produto;
    }

    public void delete(int id) {
        int delete = jdbcTemplate.update("delete from produto where id = ?",id);
        if( delete == 1 ) {
            System.out.println("Produto:(ID - " + id + ") foi deletado!");
        }
    }
}
