package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    public void verificarCache(){
        var produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        System.out.println("-------------");

        var produtoResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatado.getNome());

    }

    @Test
    public void limpandoCachePrimeiroNivel(){
        var produto = entityManager.find(Produto.class, 1);
        System.out.println(produto.getNome());
        System.out.println("-------------");
        //o clear acaba limpando a memoria do entityManager, o close() tambem limpa a memoria
        entityManager.clear();
        var produtoResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatado.getNome());

    }
}
