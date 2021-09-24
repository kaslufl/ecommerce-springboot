package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.RowMapper;
import pers.kaslufl.ecommerce.model.entity.PromocaoItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromocaoItemMapper implements RowMapper<PromocaoItem> {

    @Override
    public PromocaoItem mapRow(ResultSet resultSet, int i) throws SQLException {
        PromocaoItem promocaoItem = new PromocaoItem();
        promocaoItem.setId(resultSet.getInt("id"));
        promocaoItem.setValorPromocao(resultSet.getFloat("valorpromocao"));
        promocaoItem.setDataCadastro(resultSet.getTimestamp("datacadastro").toLocalDateTime());
        promocaoItem.setDataUltimaAtualizacao(resultSet.getTimestamp("dataultimaatualizacao").toLocalDateTime());
        promocaoItem.setProdutoId(resultSet.getInt("produtoid"));
        promocaoItem.setPromocaoId(resultSet.getInt("promocaoid"));

        return promocaoItem;
    }
}
