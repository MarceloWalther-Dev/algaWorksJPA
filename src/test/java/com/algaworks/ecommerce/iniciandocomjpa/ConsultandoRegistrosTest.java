package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsultandoRegistrosTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador(){
        Produto produto = entityManager.find(Produto.class, 1);
        //Quando buscamos por referencia o hibernate não vai buscar enquanto não usarmos.
        Produto produtoDois = entityManager.getReference(Produto.class, 1);
        Assert.assertNotNull(produto);
        Assert.assertNotNull(produtoDois);
        Assert.assertEquals("Kindle", produto.getNome());
        Assert.assertEquals("Kindle", produtoDois.getNome());
    }

    @Test
    public void atualizarReferencia(){
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setNome("Microfone");

        //refresh é para voltar ao estado que está no banco de dados/ ele vai no banco de dados de novo e pega a entidade.
        entityManager.refresh(produto);

        assertEquals("Kindle", produto.getNome());
    }

}
