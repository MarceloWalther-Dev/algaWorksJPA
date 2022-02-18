package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class RelacionamentosOneToOneTest extends EntityManagerTest {




    @Test
    public void verificarRelacionamento(){
        var pedido = entityManager.find(Pedido.class, 1);
        var pagamento = new PagamentoCartao();
        pagamento.setNumero("1234");
        pagamento.setStatus(StatusPagamento.PROCESSANDO);
        pagamento.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamento);
        entityManager.getTransaction().commit();
        entityManager.clear();

        var pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getPagamento());
    }

    @Test
    public void verificarRelacionamentoPedidoNotaFiscal(){
        var pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setDataEmissao(new Date());
        //notaFiscal.setXml("TESTE");
        notaFiscal.setPedido(pedido);

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();
        entityManager.clear();
        Pedido pedidoVerify = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerify.getNotaFiscal());

    }

}
