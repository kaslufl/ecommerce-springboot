package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.ecommerce.model.entity.Categoria;
import pers.kaslufl.ecommerce.model.entity.Produto;
import pers.kaslufl.ecommerce.model.entity.Promocao;
import pers.kaslufl.ecommerce.model.entity.PromocaoItem;

import java.util.HashMap;
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

    public HashMap search(int id) {
        Produto produto = jdbcTemplate.queryForObject(
                "select * from produto where id = ?",
                new ProdutoMapper(),
                id
        );
        List<Categoria> categoria = jdbcTemplate.query(
                "select c.* from categoria c inner join categoriaProduto cp on c.id = cp.categoriaid " +
                        "inner join produto p on p.id = cp.produtoid where p.id = ?",
                new CategoriaMapper(),
                id
        );

        List<Promocao> promocao = jdbcTemplate.query(
                "select pr.* from promocao pr inner join promocaoItem pi on pi.promocaoId = pr.id " +
                        "inner join produto p on p.id = pi.produtoId where p.id = ?",
                new PromocaoMapper(),
                id
        );

        List<PromocaoItem> promocaoitem = jdbcTemplate.query(
                "select pi.* from promocaoItem pi inner join produto p on p.id = pi.produtoid where p.id = ?",
                new PromocaoItemMapper(),
                id
        );

        HashMap json = new HashMap();
        json.put("Produto", produto);
        json.put("Categoria", categoria);
        json.put("Promocao", promocao);
        json.put("PromocaoItem", promocaoitem);

        return json;
    }

    public List<Produto> search (String nome) {
        return jdbcTemplate.query(
                "select * from produto where nome = ?",
                new ProdutoMapper(),
                nome
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
                "select * from produto where nome = ? and valorUnitario between ? and ?",
                new ProdutoMapper(),
                nome,
                valorMinimo,
                valorMaximo
        );
    }

    public Produto create(Produto produto) throws Exception {
        int insert = jdbcTemplate.update(
            "insert into produto(nome, descricao, fotoUrl, dataCadastro, dataUltimaAtualizacao, valorUnitario)" +
                    " values (?, ?, ?, ?, ?, ?)",
                produto.getNome(),
                produto.getDescricao(),
                produto.getFotoUrl(),
                produto.getDataCadastro(),
                produto.getDataUltimaAtualizacao(),
                produto.getValorUnitario()
        );
        if( insert == 1 ) {
            int id = jdbcTemplate.queryForObject(
                    "select max(id) from produto",
                    Integer.class
            );
            produto.setId(id);
            return produto;
        }
        throw new Exception("Produto não foi cadastrado!");
    }

    public Produto update(Produto produto) {
        int update = jdbcTemplate.update(
                "update produto set nome = ?, descricao = ?, fotourl = ?, dataCadastro = ?, " +
                        "dataUltimaAtualizacao = ?, valorUnitario = ? where id = ?",
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
