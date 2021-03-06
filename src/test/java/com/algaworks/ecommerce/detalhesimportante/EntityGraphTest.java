package com.algaworks.ecommerce.detalhesimportante;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Cliente_;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Pedido_;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityGraphTest extends EntityManagerTest {

    @Test
    public void buscarAtributosEssenciaisDePedidoUsandoSubGraphMetaModel(){

        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(Pedido_.dataCriacao, Pedido_.status, Pedido_.total);

        Subgraph<Cliente> subgraphCliente = entityGraph.addSubgraph(Pedido_.cliente);
        subgraphCliente.addAttributeNodes(Cliente_.nome, Cliente_.cpf);

        List<Pedido> list = entityManager.createQuery("select p from Pedido p")
                //.setHint("javax.persistence.loadgraph", entityGraph)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();

        Assert.assertNotNull(list);

    }


    @Test
    public void buscarAtributosEssenciaisDePedidoUsandoSubGraph(){

        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("dataCriacao", "status", "total");

        Subgraph<Cliente> subgraphCliente = entityGraph.addSubgraph("cliente", Cliente.class);
        subgraphCliente.addAttributeNodes("nome", "cpf");

        List<Pedido> list = entityManager.createQuery("select p from Pedido p")
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        Assert.assertNotNull(list);

    }




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
