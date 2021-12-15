package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import java.math.BigDecimal;

public class ContextoPersistencia extends EntityManagerTest {

    @Test
    public void usarContextoPersistencia(){
        entityManager.getTransaction().begin();

        //colocamos o produto em estado gerenciado
        Produto produto = entityManager.find(Produto.class, 1);

        //Quando alteramos o produto que está sendo gerenciado ele aplica uma verificação e isso se chama dirty checking
        //ele verifica se aconteceu uma alteração se houve ele faz a persistencia no banco
        produto.setPreco(new BigDecimal(100.0));

        entityManager.getTransaction().commit();
    }
}
