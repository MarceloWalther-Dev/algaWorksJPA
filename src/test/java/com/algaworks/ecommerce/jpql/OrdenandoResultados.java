package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class OrdenandoResultados extends EntityManagerTest {

    @Test
    public void ordenarResultados(){

//        String jpql = "select c from Cliente c order by c.nome";
//        String jpql = "select c from Cliente c order by c.nome desc";
        String jpql = "select c from Cliente c order by c.nome asc";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach( cliente -> System.out.println(cliente.getId() + ", " + cliente.getNome()));

    }

}
