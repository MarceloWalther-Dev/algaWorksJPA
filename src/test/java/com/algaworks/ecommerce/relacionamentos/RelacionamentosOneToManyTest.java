package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentosOneToManyTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento(){
        var cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertFalse(clienteVerificado.getPedidos().isEmpty());

    }
}
