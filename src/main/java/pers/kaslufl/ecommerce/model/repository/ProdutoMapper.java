package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.RowMapper;
import pers.kaslufl.ecommerce.model.entity.Produto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoMapper implements RowMapper<Produto> {

    @Override
    public Produto mapRow(ResultSet resultSet, int i) throws SQLException {
        Produto produto = new Produto();
        produto.setId(resultSet.getInt("id"));
        produto.setNome(resultSet.getString("nome"));
        produto.setDescricao(resultSet.getString("descricao"));
        produto.setFotoUrl(resultSet.getString("fotourl"));
        produto.setDataCadastro(resultSet.getTimestamp("datacadastro").toLocalDateTime());
        produto.setDataUltimaAtualizacao(resultSet.getTimestamp("dataultimaatualizacao").toLocalDateTime());
        produto.setValorUnitario(resultSet.getFloat("valorunitario"));

        return produto;
    }
}
