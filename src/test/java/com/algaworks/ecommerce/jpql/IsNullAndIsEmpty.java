package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class IsNullAndIsEmpty extends EntityManagerTest {

    @Test
    public void usarIsNull(){
        //Quero produto que a foto seja null, todos os produtos que a foto seja nulla
        String jpql = "select p from Produto p where p.foto is null";

        //Quero produto onde a foto não seja nulla.
        //String jpql = "select p from Produto p where p.foto is not null";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void usarIsEmpty(){
        // is empty é ideal para trabalhar com coleções, Quero todos os produtos que não tenham categoria
        //String jpql = "select p from Produto p where p.categorias is empty";

        //Quero todos os produtos onde a categoria não seja vazia
        String jpql = "select p from Produto p where p.categorias is not empty";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> list = typedQuery.getResultList();

        Assert.assertFalse(list.isEmpty());
    }

}
