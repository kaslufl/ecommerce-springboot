package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import pers.kaslufl.ecommerce.model.entity.Categoria;
import pers.kaslufl.ecommerce.model.entity.Produto;
import pers.kaslufl.ecommerce.model.entity.Promocao;
import pers.kaslufl.ecommerce.model.entity.PromocaoItem;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> search () {
        List<Produto> produtoList = jdbcTemplate.query(
                "select * from produto",
                new ProdutoMapper()
        );

        List<Produto> response = addProdutoRelationships(produtoList);
        return  response;
    }

    public Produto search(int id) {
        Produto produto = jdbcTemplate.queryForObject(
                "select * from produto where id = ?",
                new ProdutoMapper(),
                id
        );

        Produto response = addProductRelationships(produto);
        return response;
    }

    public List<Produto> search (String nome) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select * from produto where nome like ?",
                new ProdutoMapper(),
                "%" + nome + "%"
        );
        List<Produto> response = addProdutoRelationships(produtoList);
        return  response;
    }

    public List<Produto> search (Float valorMinimo, Float valorMaximo) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select * from produto where valorunitario between ? and ?",
                new ProdutoMapper(),
                valorMinimo,
                valorMaximo
        );
        List<Produto> response = addProdutoRelationships(produtoList);
        return  response;
    }

    public List<Produto> search (String nome, Float valorMinimo, Float valorMaximo) {
        List<Produto> produtoList = jdbcTemplate.query(
                "select * from produto where nome like ? and valorUnitario between ? and ?",
                new ProdutoMapper(),
                "%" + nome + "%",
                valorMinimo,
                valorMaximo
        );
        List<Produto> response = addProdutoRelationships(produtoList);
        return  response;
    }

    public List<Produto> addProdutoRelationships(List<Produto> produtoList){
        List<Produto> response = new ArrayList<>();
        for(Produto produto: produtoList) {
            response.add(addProductRelationships(produto));
        }
        return response;
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

            for(Categoria categoria : produto.getCategoriaList()){
                jdbcTemplate.update(
                        "insert into categoriaProduto(categoriaId, produtoId) values (?, ?)",
                        categoria.getId(),
                        id
                );
            }
            produto.setId(id);
            Produto response = addProductRelationships(produto);
            return response;
        }
        throw new Exception("Produto não foi cadastrado!");
    }

    public Produto addProductRelationships(Produto produto) {
        List<Categoria> categoriaList = jdbcTemplate.query(
                "select c.* from categoria c inner join categoriaProduto cp on c.id = cp.categoriaid " +
                        "inner join produto p on p.id = cp.produtoid where p.id = ?",
                new CategoriaMapper(),
                produto.getId()
        );

        List<Promocao> promocaoList = jdbcTemplate.query(
                "select pr.* from promocao pr inner join promocaoItem pi on pi.promocaoId = pr.id " +
                        "inner join produto p on p.id = pi.produtoId where p.id = ?",
                new PromocaoMapper(),
                produto.getId()
        );

        List<PromocaoItem> promocaoItemList = jdbcTemplate.query(
                "select pi.* from promocaoItem pi inner join produto p on p.id = pi.produtoid where p.id = ?",
                new PromocaoItemMapper(),
                produto.getId()
        );

        produto.setCategoriaList(categoriaList);
        produto.setPromocaoList(promocaoList);
        produto.setPromocaoItemList(promocaoItemList);

        return produto;
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
        Produto response = addProductRelationships(produto);
        return response;
    }

    public void delete(int id) {
        int delete = jdbcTemplate.update("delete from produto where id = ?",id);
        if( delete == 1 ) {
            System.out.println("Produto:(ID - " + id + ") foi deletado!");
        }
    }
}
