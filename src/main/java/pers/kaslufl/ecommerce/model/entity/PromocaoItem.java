package pers.kaslufl.ecommerce.model.entity;

import java.time.LocalDateTime;

public class PromocaoItem {
    private Integer id;
    private Float valorPromocao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimaAtualizacao;
    private Integer produtoId;
    private Integer promocaoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValorPromocao() {
        return valorPromocao;
    }

    public void setValorPromocao(Float valorPromocao) {
        this.valorPromocao = valorPromocao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getPromocaoId() {
        return promocaoId;
    }

    public void setPromocaoId(Integer promocaoId) {
        this.promocaoId = promocaoId;
    }
}
