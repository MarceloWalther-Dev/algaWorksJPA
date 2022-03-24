package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class Distinct extends EntityManagerTest {

    @Test
    public void usarDistinct(){
        //String jpql = "select distinct p from Pedido p ";
        String jpql = "select distinct p from Pedido p " +
                " join p.itensPedido i " +
                " join i.produto prod " +
                " where prod.id in (1,2,3,4)";


        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach( pedido -> System.out.println(pedido.getStatus()));
        System.out.println(resultList.size());
    }

}
