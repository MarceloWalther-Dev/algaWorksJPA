package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BasicoCriteriaTest extends EntityManagerTest {


    @Test
    public void selecionarUmAtributoParaRetorno(){
        //para criar a query precisamos do criteria builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //criando a query
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        //Root é o que vem depois do from em uma jpql
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("cliente"));

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        //aqui eu coloco o que eu vou retornar
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        Cliente cliente = typedQuery.getSingleResult();
        Assert.assertNotNull(cliente);



    }


    @Test
    public void buscarPorIdentificador(){
        //String jpql = "select p from Pedido p where p.id = 1";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);// e a projecao
        Root<Pedido> root = criteriaQuery.from(Pedido.class); // root e a entitydade do from

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        Pedido pedido = typedQuery.getSingleResult(); // uma lista simples

        Assert.assertNotNull(pedido);
    }


}
