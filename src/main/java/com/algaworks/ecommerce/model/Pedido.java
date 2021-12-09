package com.algaworks.ecommerce.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // o padrão dele já esse mas é bom deixar explicito
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "nota_fiscal_id")
    private Integer notaFiscalId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    @Column(name = "endereco_entrega")
    private EnderecoEntregaPedido enderecoEntrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Integer getNotaFiscalId() {
        return notaFiscalId;
    }

    public void setNotaFiscalId(Integer notaFiscalId) {
        this.notaFiscalId = notaFiscalId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public EnderecoEntregaPedido getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntregaPedido enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
