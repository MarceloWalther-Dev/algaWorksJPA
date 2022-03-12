package com.algaworks.ecommerce.operacoesemcascata;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CascadeTypePersistTest extends EntityManagerTest {

    @Test
    public void persistirPedidoComItensTest() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(new BigDecimal("324.0"));
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItensPedido(List.of(itemPedido));

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoVindoDoBanco = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVindoDoBanco);
    }

    @Test
    public void persistirItemPedidoComPedido(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(new BigDecimal(324.0));
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);//não é necessario cascadeType.persist pq possui o @MAPSID()
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        ItemPedido itemPedidoBanco = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertNotNull(itemPedidoBanco.getPedido());
    }


    //@Test
    public void persistirPedidoComCLiente(){
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(1987, 5, 27));
        cliente.setSexo(SexoCLiente.MASCULINO);
        cliente.setNome("Marcelo Walther da Silva");
        cliente.setCpf("0123456789");

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);//CascadeType.PERSIST
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Pedido pedidoBanco = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoBanco);
        Assert.assertNotNull(pedidoBanco.getCliente());
    }
}
