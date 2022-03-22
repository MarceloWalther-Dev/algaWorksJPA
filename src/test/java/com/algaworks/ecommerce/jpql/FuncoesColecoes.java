package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesColecoes extends EntityManagerTest {

    @Test
    public void aplicarFuncaoColecao(){

        String jpql = "select size(p.itensPedido) from Pedido p where size(p.itensPedido) > 1";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);
        List<Integer> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach( size -> System.out.println(size));
    }


}
