package com.algaworks.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PagamentoBoleto {

    @Id
    private Integer id;

    private Integer pedidoId;

    private StatusPagamento status;

    private String codigoBarras;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public String getNumero() {
        return codigoBarras;
    }

    public void setNumero(String numero) {
        this.codigoBarras = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PagamentoBoleto that = (PagamentoBoleto) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
