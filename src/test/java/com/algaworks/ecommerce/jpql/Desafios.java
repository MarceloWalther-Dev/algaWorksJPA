package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class Desafios extends EntityManagerTest {

    @Test
    public void consultandoPedidosProdutoEspecificoTest(){
//        String jpql = "select p from Pedido p join p.itens i where i.id.produtoId = 1";
//        String jpql = "select p from Pedido p join p.itens i where i.produto.id = 1";

        String jpql = "select p from Pedido p join fetch p.itensPedido item join fetch item.produto prod where prod.id = 1 ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());


    }

}
