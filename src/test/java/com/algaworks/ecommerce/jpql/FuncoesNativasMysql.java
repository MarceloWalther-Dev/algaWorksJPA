package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesNativasMysql extends EntityManagerTest {

    @Test
    public void aplicarFuncaoNativas() {
        String jpql = "select function('dayname', p.dataCriacao) from Pedido p " +
                " where function('acima_media_faturamento', p.total) = 1";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);

        List<String> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

}
