package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class PaginacaoJPQLTest extends EntityManagerTest {

    @Test
    public void paginarResultados(){
        String jpql = "select c from Categoria c order by c.nome";

        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
        //FIRST_RESULT = MAX_RESULTS * (pagina - 1)
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(2);

        List<Categoria> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(cliente -> System.out.println(cliente.getId() +", " + cliente.getNome()));
    }

}
