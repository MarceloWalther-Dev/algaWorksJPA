package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador(){
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);

//        List<Pedido> resultList = typedQuery.getResultList(); quando e uma lista

        Pedido pedido = typedQuery.getSingleResult(); // uma lista simples

        Assert.assertNotNull(pedido);
    }

    @Test
    public void selecionarUmAtributoParaRetorno(){

        String jpql = "select p.nome from Produto p";
        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);

        List<String> listaNomeProduto = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(listaNomeProduto.get(0).getClass()));

        String jpqlCliente = "select pedido.cliente from Pedido pedido";
        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);

        List<Cliente> listaCliente = typedQueryCliente.getResultList();
        listaCliente.forEach(cliente -> System.out.println(cliente.getNome()));

        Assert.assertTrue(Cliente.class.equals(listaCliente.get(0).getClass()));


    }

}
