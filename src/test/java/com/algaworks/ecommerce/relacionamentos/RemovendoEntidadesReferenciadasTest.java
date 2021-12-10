package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.RollbackException;

public class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    public void removendoItens(){
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertNotNull(pedido.getItensPedido());

        pedido.getItensPedido().forEach( item -> entityManager.remove(item));

        entityManager.getTransaction().begin();
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();
        var pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoVerificacao);
    }

    @Test(expected = RollbackException.class)
    public void jogandoException(){
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertNotNull(pedido.getItensPedido());

        entityManager.getTransaction().begin();
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();
        var pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoVerificacao);
    }
}
