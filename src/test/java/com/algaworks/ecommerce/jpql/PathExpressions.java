package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PathExpressions extends EntityManagerTest {

    @Test
    public void entendendoPathExpressionsTest(){


        String jpql = "select p.cliente.nome from Pedido p ";


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        System.out.println(lista.get(0));
        Assert.assertFalse(lista.isEmpty());
    }

}
