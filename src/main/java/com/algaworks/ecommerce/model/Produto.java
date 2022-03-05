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
@Table(name = "produto")
public class Produto extends EntityBase{


    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    //updatable = false garantimos que ele não tenha mudado o valor no update do produto
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    //insertable = false garantimos que ele não tenha valor na inserção
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
            joinColumns = @JoinColumn(name = "produto_id")) // nao vamos costumizar o nome da coluna,
    // pois sera os atributos da lista de Atributos, caso haja necessidade teriamos que entrar dentro do Atributos e usar anotacao @Column
    private List<Atributo> atributos;

    @Lob
    private byte[] foto;
}
