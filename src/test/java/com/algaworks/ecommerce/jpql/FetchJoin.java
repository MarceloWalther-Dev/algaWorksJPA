package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FetchJoin extends EntityManagerTest {

    @Test
    public void usarJoinFetch(){

        // o fetch = trazer
        String jpql = "select p, p.cliente from Pedido p " +
               // " join fetch p.cliente " +
                " join fetch p.itensPedido " +
                " left join fetch p.notaFiscal " +
                " left join fetch p.pagamento";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(i -> System.out.println(i[0]));

        Assert.assertFalse(lista.isEmpty());
    }

}
