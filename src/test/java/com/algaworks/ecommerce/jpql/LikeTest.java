package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class LikeTest extends EntityManagerTest {

    @Test
    public void usarExpressaoCondicionalLike(){
        String jpql = "select c from Cliente c where c.nome like concat('%' , :nome , '%')";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("nome", "a");

        List<Cliente> clientList = typedQuery.getResultList();
        Assert.assertFalse(clientList.isEmpty());
    }


}
