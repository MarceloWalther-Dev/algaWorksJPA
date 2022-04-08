package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Cliente_;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.Produto_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ExpressoesCondicionaisCriteriaTest extends EntityManagerTest {


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
