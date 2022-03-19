package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class MaiorOuMenor extends EntityManagerTest {

    @Test
    public void usarMaiorMenor(){
        //String jpql = "select p from Produto p where p.preco >= :precoInicial";
        String jpql = "select p from Produto p where p.preco >= :precoInicial and p.preco <= :precoFinal ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        typedQuery.setParameter("precoInicial", new BigDecimal(499));
        typedQuery.setParameter("precoFinal", new BigDecimal(1500));

        List<Object[]> list = typedQuery.getResultList();
        Assert.assertFalse(list.isEmpty());
    }

}
