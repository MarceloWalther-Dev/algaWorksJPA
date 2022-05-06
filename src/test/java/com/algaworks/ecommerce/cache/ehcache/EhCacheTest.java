package com.algaworks.ecommerce.cache.ehcache;

import com.algaworks.ecommerce.model.Pedido;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class EhCacheTest {


    protected static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpBeforeClass(){
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass(){
        entityManagerFactory.close();
    }


    private static void esperar(int segundos){
        try{
            Thread.sleep(segundos*1000);
        }catch (InterruptedException e) {
        }
    }

    private static void log(Object obj){
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + obj);
    }


    @Test
    public void ehcache(){
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        log("Buscando e incluindo no cache ......");
        entityManager1.createQuery("select p from Pedido p", Pedido.class)
                .getResultList();
        log("--------");

        esperar(1);
        Assert.assertTrue(cache.contains(Pedido.class, 2));

        esperar(2);
        Assert.assertFalse(cache.contains(Pedido.class, 2));
    }


}
