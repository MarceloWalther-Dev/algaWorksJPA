package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

public class FlushTest extends EntityManagerTest {

    @Test(expected = Exception.class)
    public void chamarFlush(){
        try{
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush();
            if(pedido.getPagamento() == null){
                throw new RuntimeException("Pedido ainda não foi pago.");
            }

/*
            Uma consulta obriga o jpa a sincronizar o que ele tem em memoria
            Pedido pedidoPago = entityManager
                    .createQuery("from Pedido p where p.id = 1", Pedido.class)
                    .getSingleResult();

            System.out.println(pedidoPago.getStatus());
            Assert.assertEquals(pedido.getStatus(), pedidoPago.getStatus());
*/

            entityManager.getTransaction().commit();
        }catch (Exception e ){
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
