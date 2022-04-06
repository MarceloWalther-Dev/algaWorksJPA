package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.function.Consumer;

public class JoinCriteriaTest extends EntityManagerTest {

    //Desafio buscar pedidos com produtos especificos ex: produto com id = 1
    @Test
    public void buscarPedidosComProdutoEspecifico(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, ItemPedido> joinPedidoItemPedido = root.join("itensPedido").join("produto");

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(joinPedidoItemPedido.get("id"),1));
        //root.fetch("itensPedido",JoinType.INNER);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> pedido = typedQuery.getResultList();

        Assert.assertNotNull(pedido);

        //pedido.forEach(p -> p.getItensPedido().forEach(itemPedido -> System.out.println(itemPedido.getProduto().getNome())));


    }


    //JOIN FETCH
    @Test
    public void fazerJoinFetch() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido,ItemPedido> joinPedidoItemPedido = root.join("itensPedido");
        //Join<ItemPedido, Produto> joinItemPedidoProduto = joinPedidoItemPedido.join("produto");

        root.fetch("pagamento", JoinType.LEFT);
        root.fetch("notaFiscal", JoinType.LEFT);
        root.fetch("itensPedido", JoinType.LEFT);
        root.fetch("cliente");

        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> pedidoList = typedQuery.getResultList();

        Assert.assertFalse(pedidoList.isEmpty());

        pedidoList.forEach(pedido -> System.out.println("Nome do cliente: "+pedido.getCliente().getNome() +"\n Sexo : "
                + pedido.getCliente().getSexo()));



    }




    //LEFT OUTER JOIN
    @Test
    public void fazerLeftOuterJoin() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        //podemos so dizer qual o tipo de join
        Join<Pedido, Pagamento> joinPedidoPagamento = root.join("pagamento", JoinType.LEFT);

        criteriaQuery.select(root);

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> pedidoList = typedQuery.getResultList();

        Assert.assertFalse(pedidoList.isEmpty());

        pedidoList.forEach(pedido -> System.out.println(pedido.getPagamento().getStatus()));
    }

    //ON
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

    //JOIN
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