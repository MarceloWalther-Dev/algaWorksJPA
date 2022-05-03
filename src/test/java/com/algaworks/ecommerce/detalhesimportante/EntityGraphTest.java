package com.algaworks.ecommerce.detalhesimportante;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityGraphTest extends EntityManagerTest {

    @Test
    public void buscarAtributosEssenciaisDePedido(){

        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("dataCriacao", "status", "total", "cliente", "notaFiscal");

//        Map<String, Object> properties = new HashMap<>();
////        properties.put("javax.persistence.fetchgraph",entityGraph);
//        properties.put("javax.persistence.loadgraph",entityGraph);
//
//        Pedido pedido = entityManager.find(Pedido.class, 1, properties);
//        Assert.assertNotNull(pedido);

        List<Pedido> list = entityManager.createQuery("select p from Pedido p")
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        Assert.assertNotNull(list);

    }


}
