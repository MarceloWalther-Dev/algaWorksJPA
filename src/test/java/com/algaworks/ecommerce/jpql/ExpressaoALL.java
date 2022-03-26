package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class ExpressaoALL extends EntityManagerTest {


    @Test
    public void pesquisarComAny() {
        // Podemos usar o ANY e o SOME.

        // Todos os produtos que já foram vendidos por um preco diferente do atual
        String jpql = "select p from Produto p " +
                " where p.preco <> ANY (select precoProduto from ItemPedido where produto = p)";

        // Todos os produtos que já foram vendidos, pelo menos, uma vez pelo preço atual.
//        String jpql = "select p from Produto p " +
//                " where p.preco = ANY (select precoProduto from ItemPedido where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }


    @Test
    public void pesquisarComAll() {
        //Todos os produtos nao foram vendidos mais depois que encareceram
        String jpql = "select p from Produto p where " +
                " p.preco > ALL (select precoProduto from ItemPedido where produto = p)";

        //todos os produtos que sempre foram vendidos pelo preco atual
        //String jpql = "select p from Produto p where p.preco = ALL(select precoProduto from ItemPedido where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);


        List<Produto> resultList = typedQuery.getResultList();
        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }


    @Test
    public void pesquisarComAllDesafio() {
        //Todos os produtos que sempre foram vendidos pelo mesmo preco
        String jpql = "select distinct p from ItemPedido ip join ip.produto p where " +
                " ip.precoProduto = ALL " +
                " (select precoProduto from ItemPedido where produto = p and id <> ip.id)";



        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);


        List<Produto> resultList = typedQuery.getResultList();
        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }



}
