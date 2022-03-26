package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class DesafioSubQueries extends EntityManagerTest {


    @Test
    public void desafioDaSubqueries() {
        //trazer todos os clientes que fizerao 2 pedidos

        String jpql = "select c from Cliente c " +
                " where ( select count(cliente) from Pedido where cliente = c ) > 2";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);


        List<Cliente> resultList = typedQuery.getResultList();
        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }


    @Test
    public void desafioDaSubqueriesComExists() {
        //trazer todos os clientes que fizerao 2 pedidos

        String jpql = "select p from Produto p " +
                " where exists ( select 1 from ItemPedido where produto = p and precoProduto <> p.preco)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);


        List<Produto> resultList = typedQuery.getResultList();
        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }



}
