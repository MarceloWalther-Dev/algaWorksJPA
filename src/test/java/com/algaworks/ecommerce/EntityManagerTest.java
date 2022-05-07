package com.algaworks.ecommerce;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerTest extends EntityManagerFactoryTest{

    protected EntityManager entityManager;

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
