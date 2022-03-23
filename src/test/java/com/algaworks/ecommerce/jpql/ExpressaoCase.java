package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class ExpressaoCase extends EntityManagerTest {

    @Test
    public void usarExpressaoCase(){
//        String jpql = "select p.id, " +
//                " case p.status " +
//                "       when 'PAGO' then 'Está pago' " +
//                "       when 'CANCELADO' then 'Foi cancelado' " +
//                "       else 'Está aguardando' " +
//                "end " +
//                "from Pedido p";

        String jpql = "select p.id, " +
                " case type(p.pagamento) " +
                "       when PagamentoBoleto then 'Pago com boleto' " +
                "       when PagamentoCartao then 'Pago com cartão' " +
                "       else 'Está aguardando' " +
                "end " +
                "from Pedido p";


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(arr -> System.out.println(arr[0] + " | " + arr[1]));

    }

}
