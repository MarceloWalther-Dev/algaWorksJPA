package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import static org.junit.Assert.*;
import org.junit.Test;

public class CrudClienteTest extends EntityManagerTest {

    @Test
    public void inserirUmCliente(){
        Cliente cliente = new Cliente(4,"Marcelo");
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();

         var clienteRetornoBanco = entityManager
                 .find(Cliente.class, 4);

        assertNotNull(clienteRetornoBanco);
    }

    @Test
    public void deveRetornarUmClienteDoBanco(){
        var cliente = entityManager.find(Cliente.class, 1);
        assertNotNull(cliente);
    }

    @Test
    public void deveAlterarValorDeUmAtributoDoCliente(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        cliente.setNome("Arthur");
        entityManager.getTransaction().begin();
        var clienteRetornoMemoria = entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        entityManager.clear();
        var clienteBanco = entityManager.find(Cliente.class, 1);
        assertEquals(clienteBanco.getNome(), clienteRetornoMemoria.getNome());
    }

    @Test
    public void deveAlterarUmclienteCompleto(){
        Cliente cliente = entityManager.find(Cliente.class, 3);
        cliente.setNome("Theo");
        //abro a transação para alterar o objeto do banco
        entityManager.getTransaction().begin();
        var clienteDaMemoriaDoEntityManager = entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        assertEquals(cliente.getNome(), clienteDaMemoriaDoEntityManager.getNome());
    }

    @Test
    public void deveDeletarUmClienteBanco(){
        var cliente = entityManager.find(Cliente.class, 3);
        entityManager.remove(cliente);
        var clienteDeletado = entityManager.find(Cliente.class, 3);
        assertNull(clienteDeletado);
    }

}
