package com.algaworks.ecommerce.iniciandocomjpa;


import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import static org.junit.Assert.*;

import com.algaworks.ecommerce.model.SexoCLiente;
import org.junit.Test;

public class CrudClienteTest extends EntityManagerTest {

    @Test
    public void inserirUmCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Marcelo");
        cliente.setSexo(SexoCLiente.MASCULINO);
        cliente.setCpf("123456");
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
        cliente.setSexo(SexoCLiente.MASCULINO);
        cliente.setCpf("123456");
        //abro a transação para alterar o objeto do banco
        entityManager.getTransaction().begin();
        var clienteDaMemoriaDoEntityManager = entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        assertEquals(cliente.getNome(), clienteDaMemoriaDoEntityManager.getNome());
    }

    @Test
    public void deveDeletarUmClienteBanco(){
        Cliente cliente = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();

        entityManager.remove(cliente);

        entityManager.getTransaction().commit();

        entityManager.clear();
        Cliente clienteDeletado = entityManager.find(Cliente.class, 3);
        assertNull(clienteDeletado);
    }

}
