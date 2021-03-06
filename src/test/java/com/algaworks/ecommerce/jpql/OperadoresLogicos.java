package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class OperadoresLogicos extends EntityManagerTest {

    @Test
    public void usarOperadores(){

        String jpql = "select p from Pedido p where p.total > 100 and p.status = 'AGUARDANDO' and p.cliente.id = 1";

        //String jpql = "select p from Pedido p where (p.status = 'AGUARDANDO' or  p.status = 'PAGO') and p.total > 100";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());
    }

}
