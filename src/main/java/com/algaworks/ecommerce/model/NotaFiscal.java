package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "pedido_id")
    private Integer id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
//    @JoinTable(name = "pedido_nota_fiscal", joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true))  *** tambem é possivel mapear com o @JoinTable
    private Pedido pedido;

    @Lob
    private byte[] xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotaFiscal that = (NotaFiscal) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
