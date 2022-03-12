package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Test;

public class EstadoECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados(){

        //Estado Transient
        Categoria categoria = new Categoria();

        categoria.setNome("Eletronicos");

        //Estado Gerenciada
        Categoria categoriaManaged = entityManager.find(Categoria.class, 1);


        //Passando uma categoria gerenciada para o estado remove
        entityManager.remove(categoriaManaged);

        //Voltando do estado remove para gerenciada/Managed
        entityManager.persist(categoriaManaged);

        //Mudando o estado transiente para gerenciada/Managed, o que vai ser gerenciada é o retorno do merge que fica em memoria
        Categoria categoriaGerenciada = entityManager.merge(categoria);

        //Mudando o estado de gerenciada para desanexada/detached
        entityManager.detach(categoriaGerenciada);

    }

}
