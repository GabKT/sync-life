package com.gabkt.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private boolean disponibilidade;
    private String nome;
    private int quantidade;
    @ManyToOne
    @JoinColumn(name = "lista_compra_id", nullable = false)
    private ListaCompra listaCompraItens;
    private String setor;
    private String unidade;
    private String obs;

    public Item() {
    }

    public Item(boolean disponibilidade, String nome, int quantidade, String setor, String unidade,
            String obs, ListaCompra listaCompra) {
        this.disponibilidade = disponibilidade;
        this.nome = nome;
        this.quantidade = quantidade;
        this.setor = setor;
        this.unidade = unidade;
        this.obs = obs;
        this.listaCompraItens = listaCompra;
    }

    public Item(UUID id, boolean disponibilidade, String nome, int quantidade, ListaCompra listaCompraItens,
            String setor, String unidade, String obs) {
        this.id = id;
        this.disponibilidade = disponibilidade;
        this.nome = nome;
        this.quantidade = quantidade;
        this.listaCompraItens = listaCompraItens;
        this.setor = setor;
        this.unidade = unidade;
        this.obs = obs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public ListaCompra getListaCompra() {
        return listaCompraItens;
    }

    public void setListaCompra(ListaCompra listaCompra) {
        this.listaCompraItens = listaCompra;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", disponibilidade=" + disponibilidade + ", nome=" + nome + ", quantidade="
                + quantidade + ", setor=" + setor + ", unidade=" + unidade + ", obs=" + obs + "]\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
