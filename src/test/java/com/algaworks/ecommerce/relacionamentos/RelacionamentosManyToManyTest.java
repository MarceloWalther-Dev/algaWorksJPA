package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class RelacionamentosManyToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento(){
        var produto = entityManager.find(Produto.class, 1);
        var categoria = entityManager.find(Categoria.class, 1);

        entityManager.getTransaction().begin();
        categoria.setProdutos(Arrays.asList(produto));
        entityManager.getTransaction().commit();

        var categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
    }

}
