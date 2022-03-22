package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesNumeros extends EntityManagerTest {

    @Test
    public void aplicarFuncaoNumero(){
        //select abs(-10), mod(3,2), sqrt(9)
        //String jpql = "select abs(p.total), mod(p.id,2), sqrt(p.total) from Pedido p";
        String jpql = "select abs(p.total), mod(p.id,2), sqrt(p.total) from Pedido p where abs(p.total) > 1000";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach( arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));

    }

}
