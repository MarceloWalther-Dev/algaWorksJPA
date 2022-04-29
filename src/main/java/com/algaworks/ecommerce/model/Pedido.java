package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "pedido")
@EntityListeners({GerarNotaFiscalListener.class})
public class Pedido extends EntityBase{


    @NotNull
    @ManyToOne(optional = false)//Optional ele vai fazer um joinColumn em vez de fazer um left joinColumn, mais importante na hora de construir uma query
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente")) // fk_pedido_cliente = fk_pedido e onde vai ficar a foreign key e cliente e a referencia da tabela
    private Cliente cliente;

    @NotEmpty
    @OneToMany(mappedBy = "pedido",cascade = CascadeType.MERGE)// cascade = CascadeType.PERSIST
    private List<ItemPedido> itensPedido;

    @PastOrPresent
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @PastOrPresent
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;


    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @NotNull
    @Positive
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @NotNull
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

    @PostPersist
    public void aposPersistir() {
        System.out.println("Após persistir Pedido.");
    }

    @PostUpdate
    public void aposAtualizar() {
        System.out.println("Após atualizar Pedido.");
    }

    @PreRemove
    public void aoRemover() {
        System.out.println("Antes de remover Pedido.");
    }

    @PostRemove
    public void aposRemover() {
        System.out.println("Após remover Pedido.");
    }

    @PostLoad
    public void aoCarregar() {
        System.out.println("Após carregar o Pedido.");
    }
}
