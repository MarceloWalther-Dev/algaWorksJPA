package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoRelacionamentosTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento(){

        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrônicos");

        Categoria categoriaFilha = new Categoria();
        categoriaFilha.setNome("Celulares");
        categoriaFilha.setCategoriaPai(categoriaPai);


        entityManager.getTransaction().begin();

        entityManager.persist(categoriaPai);
        entityManager.persist(categoriaFilha);

        entityManager.getTransaction().commit();

        entityManager.clear();

        var categoriaVerificado = entityManager.find(Categoria.class, categoriaFilha.getId());
        System.out.println(categoriaVerificado.getId());
        System.out.println(categoriaVerificado.getNome());
        System.out.println(categoriaVerificado.getCategoriaPai().getNome());
        System.out.println(categoriaVerificado.getCategoriaPai().getCategoriaPai());
        Assert.assertNotNull(categoriaVerificado.getCategoriaPai());

    }
}
