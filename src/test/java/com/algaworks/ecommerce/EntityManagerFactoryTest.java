package com.algaworks.ecommerce;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryTest {

    protected static EntityManagerFactory entityManagerFactory;

    //@BeforeClass é iniciado antes da classe iniciar, antes de tudo
    @BeforeClass
    public static void setUpBeforeClass(){
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    //@AfterClass é iniciado , depois de tudo
    @AfterClass
    public static void tearDownAfterClass(){
        entityManagerFactory.close();
    }

    public static void log(Object obj, Object... args){
        System.out.println(String.format("[LOG " + System.currentTimeMillis() + "] " + obj, args));
    }

    public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}
    }

}
