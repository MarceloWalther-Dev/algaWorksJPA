package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@SecondaryTable(name = "cliente_detalhe",pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
//Jpa vai entender que e para criar um tabela com o nome cliente detalhe com a coluna cliente_id e que ao mesmo tempo ela sera primary key e fk para a tabela cliente
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente",
        uniqueConstraints = {@UniqueConstraint(name = "unq_cpf", columnNames = { "cpf" })},
        indexes = { @Index(name = "idx_nome", columnList = "nome") }) // uniqueConstraints nada mais e que uma coluna no banco de dados que nao pode se repetir

public class Cliente extends EntityBase{

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(columnDefinition = "varchar(14) not null")
    private String cpf;

    @ElementCollection // aqui diz que vai ser um elemento imbutido e que sera gerado outra tabela
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_cliente_contatos"))) //nome da tabela sera cliente_contato, e tera uma coluna cliente_id referenciando a tabela cliente
    @MapKeyColumn(name = "tipo")// uma coluna com o nome tipo que sera a chave do map
    @Column(name = "descricao")//uma coluna com o nome descricao que sera o valor do map
    private Map<String, String> contatos;

    @Transient // propriedade faz o jpa ignorar esse atributo
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    @Column(table = "cliente_detalhe", columnDefinition = "varchar(30) not null")
    private SexoCLiente sexo;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")// lá na minha classe pedido no atributo cliente tem os meta dados para que o jpa utilize para gerar as consultas
    private List<Pedido> pedidos;

    @PostLoad //toda vez que carregar ele da base
    public void configurarPrimeiroNome(){
        if(nome != null && !nome.isBlank()){
            int index = nome.indexOf(" ");
            if(index > -1){
                primeiroNome = nome.substring(0, index);
            }
        }
    }
}
