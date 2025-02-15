package com.gabkt.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity(name = "lista_compra")
public class ListaCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String status;
    private double valorTotal;
    private LocalDateTime data;
    private LocalDateTime ultimaModificacao;
    @OneToMany(mappedBy = "listaCompraItens", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itens;
    @ManyToMany(mappedBy = "listaCompraUsuarios", fetch = FetchType.EAGER)
    private List<Usuario> users;

    public ListaCompra() {
    }

    public ListaCompra(String status, double valorTotal, LocalDateTime data, LocalDateTime ultimaModificacao,
            List<Item> itens, List<Usuario> user) {
        this.status = status;
        this.valorTotal = valorTotal;
        this.data = data;
        this.ultimaModificacao = ultimaModificacao;
        this.itens = itens;
        this.users = user;
    }

    public ListaCompra(UUID id, String status, double valorTotal, LocalDateTime data, LocalDateTime ultimaModificacao,
            List<Item> itens, List<Usuario> users) {
        this.id = id;
        this.status = status;
        this.valorTotal = valorTotal;
        this.data = data;
        this.ultimaModificacao = ultimaModificacao;
        this.itens = itens;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(LocalDateTime ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public List<Usuario> getUsers() {
        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
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
        ListaCompra other = (ListaCompra) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
