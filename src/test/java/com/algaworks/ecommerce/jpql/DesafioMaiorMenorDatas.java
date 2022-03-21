package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class DesafioMaiorMenorDatas extends EntityManagerTest {

    @Test
    public void desafioDatasMaiorMenor(){

        String jpql = "select p from Pedido p where p.dataCriacao < : dataNow ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("dataNow", LocalDateTime.now());

        List<Pedido> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

    }

}
