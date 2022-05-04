package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;

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
public class Pedido extends EntityBase
        //implements PersistentAttributeInterceptable
{


    @NotNull
    @ManyToOne(optional = false
            //, fetch = FetchType.LAZY
    )
//Optional ele vai fazer um joinColumn em vez de fazer um left joinColumn, mais importante na hora de construir uma query
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    // fk_pedido_cliente = fk_pedido e onde vai ficar a foreign key e cliente e a referencia da tabela
    private Cliente cliente;

    @NotEmpty
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)// cascade = CascadeType.PERSIST
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

    //@LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToOne(mappedBy = "pedido"
            //, fetch = FetchType.LAZY
    )
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

    //@LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToOne(mappedBy = "pedido"
           //, fetch = FetchType.LAZY
    )
    private Pagamento pagamento;

//    @Transient
//    @Setter(AccessLevel.NONE)// utilizamos essa anotacao pq substituimos o setter pelo public void $$_hibernate_setInterceptor
//    @Getter(AccessLevel.NONE)// utilizamos essa anotacao pq substituimos o getter pelo public PersistentAttributeInterceptor $$_hibernate_getInterceptor()
//    private PersistentAttributeInterceptor persistentAttributeInterceptor;
//
//
//    public NotaFiscal getNotaFiscal() {
//        if (this.persistentAttributeInterceptor != null) {
//            return (NotaFiscal) persistentAttributeInterceptor.readObject(this, "notaFiscal", this.notaFiscal);
//        } else {
//            return notaFiscal;
//        }
//    }
//
//    public void setNotaFiscal(NotaFiscal notaFiscal) {
//        if (this.persistentAttributeInterceptor != null) {
//           this.notaFiscal = (NotaFiscal) persistentAttributeInterceptor.writeObject(this, "notaFiscal", this.notaFiscal, notaFiscal);
//        } else {
//            this.notaFiscal = notaFiscal;
//        }
//    }
//
//    public Pagamento getPagamento() {
//        if (this.persistentAttributeInterceptor != null) {
//            return (Pagamento) persistentAttributeInterceptor.readObject(this, "pagamento", this.pagamento);
//        } else {
//            return pagamento;
//        }
//    }
//
//    public void setPagamento(Pagamento pagamento) {
//        if (this.persistentAttributeInterceptor != null) {
//            this.pagamento = (Pagamento)persistentAttributeInterceptor.writeObject(this, "pagamento", this.pagamento, pagamento);
//        } else {
//            this.pagamento = pagamento;
//        }
//    }
//
//
//    // get do atributo persistentAttributeInterceptor
//    @Override
//    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
//        return this.persistentAttributeInterceptor;
//    }
//
//    //set do atributo persistentAttributeInterceptor
//    @Override
//    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor persistentAttributeInterceptor) {
//        this.persistentAttributeInterceptor = persistentAttributeInterceptor;
//    }

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }


    private void calcularTotal() {
        if (this.itensPedido != null) {
            this.total = itensPedido.stream()
                    .map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }


    //callbacks{
    @PrePersist// a propriedade sera atribuida o valor assim que ele persistir
    public void aoPersistir() {
        this.dataCriacao = LocalDateTime.now();
        this.calcularTotal();
    }

    @PreUpdate// a propriedade recebera valor assim que acontecer o update
    public void aoAtualizar() {
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
