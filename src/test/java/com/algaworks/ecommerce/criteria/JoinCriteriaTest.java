package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;

public class JoinCriteriaTest extends EntityManagerTest {

    @Test
    public void fazerJoinON() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> joinPedidoPagamento = root.join("pagamento");
        //Utilizando o on no join
        joinPedidoPagamento.on(criteriaBuilder.equal(joinPedidoPagamento.get("status"), StatusPagamento.PROCESSANDO));


        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> pedidoList = typedQuery.getResultList();

        Assert.assertFalse(pedidoList.isEmpty());

        pedidoList.forEach(pedido -> System.out.println(pedido.getPagamento().getStatus()));
    }

    @Test
    public void fazerJoin() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemPedido> criteriaQuery = criteriaBuilder.createQuery(ItemPedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Join<Pedido, Pagamento> joinPedidoPagamento = root.join("pagamento");
        Join<Pedido, ItemPedido> joinPedidoItemPedido = root.join("itensPedido");
        Join<ItemPedido, Produto> joinItemPedidoProduto = joinPedidoItemPedido.join("produto");

//        criteriaQuery.select(joinPedidoPagamento);
//        criteriaQuery.where(criteriaBuilder
//                .equal(joinPedidoPagamento.get("status"), StatusPagamento.PROCESSANDO));

        criteriaQuery.select(joinPedidoItemPedido);

        TypedQuery<ItemPedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<ItemPedido> pedidoList = typedQuery.getResultList();

        Assert.assertFalse(pedidoList.isEmpty());

        pedidoList.forEach(item -> System.out.println(item.getPrecoProduto()));

    }
}