package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.RowMapper;
import pers.kaslufl.ecommerce.model.entity.Promocao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromocaoMapper implements RowMapper<Promocao> {

    @Override
    public Promocao mapRow(ResultSet resultSet, int i) throws SQLException {
        Promocao promocao = new Promocao();
        promocao.setId(resultSet.getInt("id"));
        promocao.setNome(resultSet.getString("nome"));
        promocao.setDescricao(resultSet.getString("descricao"));
        promocao.setDataInicio(resultSet.getDate("dataInicio").toLocalDate());
        promocao.setDataFim(resultSet.getDate("dataFim").toLocalDate());
        promocao.setDataCadastro(resultSet.getTimestamp("datacadastro").toLocalDateTime());
        promocao.setDataUltimaAtualizacao(resultSet.getTimestamp("dataultimaatualizacao").toLocalDateTime());

        return promocao;
    }
}
