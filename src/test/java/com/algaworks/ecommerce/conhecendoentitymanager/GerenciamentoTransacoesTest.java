package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test
    public void abrirFecharCancelarTransacao(){
        //Transação é um periodo de tempo em que podemos fazer mudança no banco de dados com consistencia.
        //abrir
        entityManager.getTransaction().begin();

        //Cancelar uma transação
        entityManager.getTransaction().rollback();

        //fechar
        entityManager.getTransaction().commit();

    }
}
