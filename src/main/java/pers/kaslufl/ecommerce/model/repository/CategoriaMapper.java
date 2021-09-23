package pers.kaslufl.ecommerce.model.repository;

import org.springframework.jdbc.core.RowMapper;
import pers.kaslufl.ecommerce.model.entity.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaMapper implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet resultSet, int i) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getInt("id"));
        categoria.setNome(resultSet.getString("nome"));
        categoria.setDescricao(resultSet.getString("descricao"));
        categoria.setImagemSimboloUrl(resultSet.getString("imagemsimbulourl"));

        return categoria;
    }
}
