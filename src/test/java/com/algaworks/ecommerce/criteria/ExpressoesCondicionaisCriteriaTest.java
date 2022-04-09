package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ExpressoesCondicionaisCriteriaTest extends EntityManagerTest {


    @Test
    public void usarBetween(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.between(root.get(Pedido_.total), new BigDecimal(499), new BigDecimal(800)),
                criteriaBuilder.between(root.get(Pedido_.dataCriacao),LocalDateTime.now().minusDays(5), LocalDateTime.now())
        );


        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> resultList = typedQuery.getResultList();
        Assert.assertNotNull(resultList);

        resultList.forEach(p -> System.out.println(String.format("*********\nID : %d \nTotal : %s  \nData : %s", p.getId(),p.getTotal(), p.getDataCriacao())));
    }


    @Test
    public void desafioTrazerDatas(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.lessThan(root.get(Pedido_.dataCriacao), LocalDateTime.now().minusDays(3))
                //criteriaBuilder.greaterThan(root.get(Pedido_.dataCriacao), LocalDateTime.of(LocalDate.of(2022,04,05), LocalTime.now()))
        );

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> resultList = typedQuery.getResultList();

        Assert.assertNotNull(resultList);

        resultList.forEach(pedido -> System.out.println(pedido.getStatus()));
    }


    @Test
    public void usarMaiorMenor(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        //greaterThan = Maior que
        criteriaQuery.where(criteriaBuilder.greaterThan(root.get(Produto_.preco), new BigDecimal(799)));

        //greaterThanOrEqualTo = Maior que ou igual a
        criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get(Produto_.preco), new BigDecimal(799)));

        //lessThan = menor que
        criteriaQuery.where(criteriaBuilder.lessThan(root.get(Produto_.preco), new BigDecimal(799)));

        //lessThanOrEqualTo = menor que ou igual a
        criteriaQuery.where(criteriaBuilder.lessThanOrEqualTo(root.get(Produto_.preco), new BigDecimal(799)));

        //Utilizando o AND
        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Produto_.preco), new BigDecimal(799)),
                //AND
                criteriaBuilder.lessThanOrEqualTo(root.get(Produto_.preco), new BigDecimal(3500)));




        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> resultList = typedQuery.getResultList();
        Assert.assertNotNull(resultList);

        resultList.forEach(p -> System.out.println(String.format("*********\nID : %d \nNome : %s \nPreço : %s ", p.getId(),p.getNome(),p.getPreco())));
        System.out.println("*********");
    }





    @Test
    public void usarIsEmpty(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.isEmpty(root.get(Produto_.categorias)));


        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> resultList = typedQuery.getResultList();

        Assert.assertNotNull(resultList);

        resultList.forEach( p -> System.out.println(p.getNome()));

    }


    @Test
    public void usarIsNull(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        //1 forma
        criteriaQuery.where(root.get(Produto_.foto).isNull());

        //2 forma
        criteriaQuery.where(criteriaBuilder.isNull(root.get(Produto_.foto)));

        criteriaQuery.where(criteriaBuilder.isNotNull(root.get(Produto_.foto)));

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> resultList = typedQuery.getResultList();

        Assert.assertNotNull(resultList);

        resultList.forEach( p -> System.out.println(p.getNome()));

    }

    @Test
    public void usarExpressaoCondicionalLike(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get(Cliente_.nome), "%A%"));


        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Cliente> resultList = typedQuery.getResultList();

        Assert.assertNotNull(resultList);

        resultList.forEach(c -> System.out.println(c.getNome()));
    }


}
