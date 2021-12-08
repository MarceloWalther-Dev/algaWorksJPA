package com.algaworks.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoCLiente sexo;

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SexoCLiente getSexo() {
        return sexo;
    }

    public void setSexo(SexoCLiente sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
