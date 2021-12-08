package com.algaworks.ecommerce;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest {

    protected static EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

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

    //Executado antes de cada método anotado com @Test
    @Before
    public void setUp(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    //Executado apos de cada método anotado com @Test
    @After
    public void tearDown(){
        entityManager.close();
    }
}
