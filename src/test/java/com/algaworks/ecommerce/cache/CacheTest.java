package com.algaworks.ecommerce.cache;

import com.algaworks.ecommerce.model.Pedido;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public class CacheTest {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeClass
    public static void setUpBeforeClass(){
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterClass
    public static void tearDownAfterClass(){
        entityManagerFactory.close();
    }


    @Test
    public void controlarCacheDinamicamente(){

        Cache cache = entityManagerFactory.getCache();

        System.out.println("Buscando todos os pedidos ....................................");
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        //podemos setar diretamente no entity manager a forma que ele vai se comportar com o cache de segundo nivel
        //entityManager1.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);

        entityManager1.createQuery("select p from Pedido p ", Pedido.class)
                //.setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
                .getResultList();

        //CacheStoreMode.USE -> Adiciona no cache
        //CacheStoreMode.BYPASS -> Ignorar Retorno e nao vai adicionar o retorno no cache
        //CacheStoreMode.REFRESH -> e parecido com o use toda a consulta que tem ele pega retorno e joga no cache.

        System.out.println("Buscando o pedido de ID igual a 2 ....................................");
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        Map<String, Object> properties = new HashMap<>();
        //properties.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.USE);
        entityManager2.find(Pedido.class, 2, properties);

        //Assert.assertTrue(cache.contains(Pedido.class, 1));
        entityManager1.close();
        entityManager2.close();
    }



    @Test
    public void analisarOpcoesCache(){
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instacia 1:");
        entityManager1.createQuery("select p from Pedido p ", Pedido.class).getResultList();

        Assert.assertTrue(cache.contains(Pedido.class, 1));
        entityManager1.close();
    }



    @Test
    public void verificandoSeUmaEntidadeEstaNoCache(){
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instacia 1:");
        entityManager1.createQuery("select p from Pedido p ", Pedido.class).getResultList();

        Assert.assertTrue(cache.contains(Pedido.class, 1));

        entityManager1.close();
    }


    @Test
    public void removerDoCache(){

        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instacia 1:");
        entityManager1.createQuery("select p from Pedido p ", Pedido.class).getResultList();

        System.out.println("Removendo pedido 1 do cache");

        //remove somente pedido com id 1 do cache
        cache.evict(Pedido.class, 1);

        //Remove todas as instancias do cache da entidade pedido
        //cache.evict(Pedido.class);

        //remove todas as instancias de todas as classes, ex: Produto, Pedido, Pagamento, CLiente ... remove tudo
        //cache.evictAll();


        //cache.contains(Pedido.class, 1);


        //cache.unwrap();



        System.out.println("Buscando a partir da instacia 2 com id 1:");
        entityManager2.find(Pedido.class, 1);

        System.out.println("Buscando a partir da instacia 2 com id 2:");
        entityManager2.find(Pedido.class, 2);

        entityManager1.close();
        entityManager2.close();
    }



    @Test
    public void adicionarPedidosNoCache(){

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instacia 1:");
        entityManager1.createQuery("select p from Pedido p ", Pedido.class).getResultList();

        System.out.println("Buscando a partir da instacia 2:");
        entityManager2.find(Pedido.class, 1);

        entityManager1.close();
        entityManager2.close();
    }


    @Test
    public void buscarDoCache(){

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instacia 1:");
        entityManager1.find(Pedido.class, 1);

        System.out.println("Buscando a partir da instacia 2:");
        entityManager2.find(Pedido.class, 1);

        entityManager1.close();
        entityManager2.close();
    }

}
