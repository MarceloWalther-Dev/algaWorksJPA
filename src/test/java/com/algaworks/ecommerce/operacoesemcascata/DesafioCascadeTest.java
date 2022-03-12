package com.algaworks.ecommerce.operacoesemcascata;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

public class DesafioCascadeTest extends EntityManagerTest {

    //@Test
    public void persistirCategoriaAtravezDeProduto(){
        Produto produto = new Produto();
        produto.setNome("Celular S8");
        produto.setDescricao("Celular da ultima geração");
        produto.setDataCriacao(LocalDateTime.now());
        produto.setPreco(new BigDecimal("8000.00"));

        Categoria categoria = new Categoria();
        categoria.setNome("EletronicosTest");

        produto.setCategorias(Collections.singletonList(categoria));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoBanco = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotNull(produtoBanco);
        Assert.assertNotNull(produtoBanco.getCategorias());

    }


}
