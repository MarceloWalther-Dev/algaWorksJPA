package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedido")
@EntityListeners({GerarNotaFiscalListener.class})
public class Pedido extends EntityBase{


    @ManyToOne(optional = false)//Optional ele vai fazer um joinColumn em vez de fazer um left joinColumn, mais importante na hora de construir uma query
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente")) // fk_pedido_cliente = fk_pedido e onde vai ficar a foreign key e cliente e a referencia da tabela
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido",cascade = CascadeType.MERGE)// cascade = CascadeType.PERSIST
    private List<ItemPedido> itensPedido;

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    @Column(name = "endereco_entrega")
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public boolean isPago(){
        return StatusPedido.PAGO.equals(status);
    }


    private void calcularTotal(){
        if (this.itensPedido != null){
            this.total = itensPedido.stream()
                    .map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }else{
            total = BigDecimal.ZERO;
        }
    }


    //callbacks{
    @PrePersist// a propriedade sera atribuida o valor assim que ele persistir
    public void aoPersistir(){
       this.dataCriacao = LocalDateTime.now();
       this.calcularTotal();
    }

    @PreUpdate// a propriedade recebera valor assim que acontecer o update
    public void aoAtualizar(){
        this.dataUltimaAtualizacao = LocalDateTime.now();
        this.calcularTotal();
    }
}
