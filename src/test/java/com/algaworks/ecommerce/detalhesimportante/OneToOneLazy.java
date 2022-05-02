package com.algaworks.ecommerce.detalhesimportante;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Pedido_;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OneToOneLazy extends EntityManagerTest {

    @Test
    public void mostrarProblema(){
        System.out.println("Buscando um pedido");
        Pedido pedido = entityManager.find(Pedido.class, 1);
        Assert.assertNotNull(pedido);

        System.out.println("--------------------------------------------------");

        System.out.println("Buscando uma lista de pedidos");
        List<Pedido> lista = entityManager.createQuery("Select p from Pedido p", Pedido.class).getResultList();
        Assert.assertFalse(lista.isEmpty());
    }


}
