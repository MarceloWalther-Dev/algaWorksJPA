package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EntityListeners({ GenericoListener.class })
@Entity
@Table(name = "produto",
        uniqueConstraints = {@UniqueConstraint(name = "unq_nome", columnNames = {"nome"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
public class Produto extends EntityBase{

    @Column(length = 100, nullable = false) // como ficaria no banco de dados, nome varchar(100) not null
    private String nome;

    @Column(name = "descricao")
    private String descricao;

                      //precision = quantidade de digitos, scale = numero de casas decimais
    @Column(precision = 19, scale = 2)// preco decimal(10,2)
    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    //@Lob posso usar para textos grandes pois string vai somente ate varchar(255)
    //updatable = false garantimos que ele não tenha mudado o valor no update do produto
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    //insertable = false garantimos que ele não tenha valor na inserção
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"), foreignKey = @ForeignKey(name = "fk_produto_tags"))
    @Column(name = "tag", length = 50, nullable = false)
    private Set<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_produto_atributos"))) // nao vamos costumizar o nome da coluna,
    // pois sera os atributos da lista de Atributos, caso haja necessidade teriamos que entrar dentro do Atributos e usar anotacao @Column
    private List<Atributo> atributos;

    @Lob
    private byte[] foto;
}
