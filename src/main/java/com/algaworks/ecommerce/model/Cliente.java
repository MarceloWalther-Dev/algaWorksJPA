package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Transient // propriedade faz o jpa ignorar esse atributo
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    private SexoCLiente sexo;

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
