package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.TimeZone;

public class FuncoesDatas extends EntityManagerTest {

    @Test
    public void aplicarFuncao(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        //current_date, current_time, current_timestamp
        //String jpql = "select current_date, current_time, current_timestamp from Pedido p";

        //String jpql = "select year(current_timestamp) from Pedido p";
        //String jpql = "select year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao) from Pedido p";
        //String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second(p.dataCriacao) from Pedido p";
        String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second(p.dataCriacao) from Pedido p where hour(p.dataCriacao) > 7";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach( arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

}
