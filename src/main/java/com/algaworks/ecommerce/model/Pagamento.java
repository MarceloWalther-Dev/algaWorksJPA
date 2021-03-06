package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// uma tabela por classe
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING) //nome da coluna no banco, o jpa coloca um como padrao para modificar temos que ir na classe alterar o nome da tabela com a anotacao
//DiscriminatorValue()
@Entity
@Table(name = "pagamento")
public abstract class Pagamento extends EntityBase{

    @NotNull
    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pagamento_pedido"))
    private Pedido pedido;

    @NotNull
    @Column(columnDefinition = "varchar(30) not null")
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

}
