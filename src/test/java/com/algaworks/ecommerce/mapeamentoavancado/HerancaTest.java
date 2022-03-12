package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HerancaTest extends EntityManagerTest {

    @Test
    public void salvarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Fernanda Morais");
        cliente.setSexo(SexoCLiente.FEMININO);
        cliente.setCpf("333");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteDoBanco = entityManager.find(Cliente.class, cliente.getId());

        assertNotNull(clienteDoBanco.getNome());
        assertNotNull(clienteDoBanco.getId());

    }

    @Test
    public void buscarPagamentos(){
        List pagamentos = entityManager
                .createQuery("select p from Pagamento p")
                .getResultList();

        assertNotNull(pagamentos.isEmpty());
    }

    @Test
    public void incluirPagamentoPedido(){
        Pedido pedido = entityManager.find(Pedido.class, 1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
        pagamentoCartao.setNumeroCartao("123");

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoBanco = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoBanco.getPagamento());

    }


}
