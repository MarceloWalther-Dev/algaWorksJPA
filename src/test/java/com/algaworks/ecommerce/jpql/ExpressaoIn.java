package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressaoIn extends EntityManagerTest {

    @Test
    public void usarExpressaoIN() {
        //String jpql = "select p from Pedido p where p.id in (1, 3, 4)";

        String jpql = "select p from Pedido p where p.id in (:listaPedidos)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("listaPedidos", Arrays.asList(1, 3, 4));
        List<Pedido> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());

        list.forEach(p -> System.out.println(p.getId() + " - " + p.getStatus()));
    }

}
