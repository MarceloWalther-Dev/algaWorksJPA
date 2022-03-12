package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCLiente;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class SecondaryTableTest extends EntityManagerTest {

    @Test
    public void salvarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Finotti");
        cliente.setSexo(SexoCLiente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(1987, 5, 27));
        cliente.setCpf("3333");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteDoBanco = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNotNull(clienteDoBanco);
        Assert.assertEquals(cliente.getNome(), clienteDoBanco.getNome());
        Assert.assertEquals(cliente.getDataNascimento(), clienteDoBanco.getDataNascimento());

    }

}
