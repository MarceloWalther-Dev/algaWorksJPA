package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class LeftJoinTest extends EntityManagerTest {


    @Test
    public void fazerLeftJoin() {
        // vai trazer todos os registros de pedido pq ele esta a esquerda e tbm todos os pedidos que tem relacionamento com pagamento
        //String jpql = "select p from Pedido p left join p.pagamento pag";

        //vem todos os pedidos com associacao ao pagamento vai executar o id = id e status = PROCESSANDO e vai trazer todos os pedidos a esquerda
        //String jpql = "select p from Pedido p left join p.pagamento pag on pag.status = 'PROCESSANDO'";

        //vem todos os pedidos que o status = processando e trara todos pedidos a esquerda que tem status processando, nao trara os que nao tiver o status
        //String jpql = "select p from Pedido p left join p.pagamento pag where pag.status = 'PROCESSANDO'";

        String jpql = "select p from Pedido p left join p.pagamento pag";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertTrue(lista.size() == 1);
    }
}
