package com.algaworks.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
    @TableGenerator(name = "tabela", table = "hibernate_sequences",
                         pkColumnName = "sequence_name",
                         pkColumnValue = "categoria",
                         valueColumnName = "next_val",
                         initialValue = 0,
                         allocationSize = 50)
    private Integer id;

    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;

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

    public Integer getCategoriaPaiId() {
        return categoriaPaiId;
    }

    public void setCategoriaPaiId(Integer categoriaPaiId) {
        this.categoriaPaiId = categoriaPaiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
