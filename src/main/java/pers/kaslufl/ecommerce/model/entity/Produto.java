package pers.kaslufl.ecommerce.model.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private String fotoUrl;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimaAtualizacao;
    private Float valorUnitario;
    private List<Categoria> categoriaList;
    private List<Promocao> promocaoList;
    private List<PromocaoItem> promocaoItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
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

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<Promocao> getPromocaoList() {
        return promocaoList;
    }

    public void setPromocaoList(List<Promocao> promocaoList) {
        this.promocaoList = promocaoList;
    }

    public List<PromocaoItem> getPromocaoItemList() {
        return promocaoItemList;
    }

    public void setPromocaoItemList(List<PromocaoItem> promocaoItemList) {
        this.promocaoItemList = promocaoItemList;
    }
}
