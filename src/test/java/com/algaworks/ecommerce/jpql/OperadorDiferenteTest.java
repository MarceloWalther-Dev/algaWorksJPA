package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class OperadorDiferenteTest extends EntityManagerTest {

    @Test
    public void usarExpressaoDiferente(){
        String jpql = "select p from Produto p where p.id <> : produtoId";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        typedQuery.setParameter("produtoId", 1);

        List<Produto> produtoList = typedQuery.getResultList();
        Assert.assertFalse(produtoList.isEmpty());
    }



}
