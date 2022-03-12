package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CallbackTest extends EntityManagerTest {

    @Test
    public void acionarCallbacks(){
        var cliente = entityManager.find(Cliente.class, 1);
        Pedido pedido = new Pedido();


        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        //aqui vai jogar na base de dados e chamar o callback do persist
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        //como o commit já chama o flush consequentimente ele vai lançar na base de dados e já vai chamar o callBack do update
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }
}
