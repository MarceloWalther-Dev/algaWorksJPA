package com.algaworks.ecommerce.mapeamentobasico;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutido extends EntityManagerTest {

    @Test
    public void analisarMapeamentoObjetoEmbutido(){
        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("88060233");
        endereco.setLogradouro("Servidao Fabriciana de Souza avila");
        endereco.setBairro("Rio vermelho");
        endereco.setNumero("123");
        endereco.setCidade("Florianopolis");
        endereco.setEstado("SC");

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(1000));
        pedido.setEnderecoEntrega(endereco);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        var pedidoVerifica = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerifica);
        Assert.assertNotNull(pedidoVerifica.getEnderecoEntrega());
        Assert.assertNotNull(pedidoVerifica.getEnderecoEntrega().getCep());

    }
}
