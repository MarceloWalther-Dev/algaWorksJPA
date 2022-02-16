package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicatTags(){
        entityManager.getTransaction().begin(); // abro a conexao

        Produto produto = entityManager.find(Produto.class, 1);

        HashSet<String> hashSet = new HashSet<>();

        hashSet.add("ebook");
        hashSet.add("Livro-digital");
        produto.setTags(hashSet);

        entityManager.getTransaction().commit(); // comito e fechando
        entityManager.clear(); // limpo a memoria do entityManager

        Produto produtoRetornadoBanco = entityManager.find(Produto.class, produto.getId());


        Assert.assertFalse(produtoRetornadoBanco.getTags().isEmpty());


    }

}
