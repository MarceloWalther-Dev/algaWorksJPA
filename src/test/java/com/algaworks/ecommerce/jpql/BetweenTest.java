package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BetweenTest extends EntityManagerTest {

    @Test
    public void usarBetween(){
        //String jpql = "select p from Produto p where p.preco between :precoInicial and :precoFinal ";

//        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
//        typedQuery.setParameter("precoInicial", new BigDecimal(500));
//        typedQuery.setParameter("precoFinal", new BigDecimal(1000));

        String jpql = "select p from Produto p where p.dataCriacao between :dataInicial and :dataFinal";
        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        List<Produto> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }


}
