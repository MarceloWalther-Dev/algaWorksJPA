package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// uma tabela por classe
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING) //nome da coluna no banco, o jpa coloca um como padrao para modificar temos que ir na classe alterar o nome da tabela com a anotacao
//DiscriminatorValue()
@Entity
//@Table(name = "pagamento")
public abstract class Pagamento extends EntityBase{

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

}
