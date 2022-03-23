package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncaoGroupBy extends EntityManagerTest {

    @Test
    public void agruparResultado() {

        //Quantidade de produtos por categoria
        //String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";

        //Quantidade de vendas por mês
//        String jpql = "select concat(year(p.dataCriacao), ' - ' , function('monthname', p.dataCriacao)), " +
//                " sum(p.total) from Pedido p " +
//                " group by year(p.dataCriacao), month(p.dataCriacao) ";

        //Total de vendas por Categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.produto prod " +
//                " join prod.categorias c " +
//                " group by c";

        //total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto) from Cliente c " +
//                " join c.pedidos p " +
//                " join p.itensPedido ip " +
//                " group by c";

//        String jpql = "select c.nome, concat(year(p.dataCriacao),' - ', month(p.dataCriacao), ' - ', day(p.dataCriacao)), sum(ip.precoProduto) from Pedido p " +
//                " join p.itensPedido ip " +
//                " join ip.produto prod" +
//                " join prod.categorias c " +
//                " group by c";


        //        Total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.pedido p join p.cliente c " +
//                " group by c.id";

//        Total de vendas por dia e por categoria
        String jpql = "select " +
                " concat(year(p.dataCriacao), '/', month(p.dataCriacao), '/', day(p.dataCriacao)), " +
                " concat(c.nome, ': ', sum(ip.precoProduto)) " +
                " from ItemPedido ip join ip.pedido p join ip.produto pro join pro.categorias c " +
                " group by year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao), c.id " +
                " order by p.dataCriacao, c.nome ";


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(arr -> System.out.println(arr[0] + " | " + arr[1]));
    }


    @Test
    public void agruparUsandoGroupByComWhere(){
        //total de vendas por mês
//        String jpql = "select concat(year(p.dataCriacao), ' - ', function('monthname', p.dataCriacao)), " +
//                " sum(p.total) " +
//                " from Pedido p " +
//                " where year(p.dataCriacao) = year(current_date) and p.status = :status " +
//                " group by year(p.dataCriacao), month(p.dataCriacao) ";

        //Total de vendas por categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
//                " join ip.produto prod " +
//                " join prod.categorias c " +
//                " join ip.pedido p " +
//                " where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) = month(current_date) " +
//                " group by c.id ";

        //Total de vendas por cliente
        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip " +
                " join ip.pedido p " +
                " join p.cliente c " +
                " join ip.pedido p " +
                " where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) >= (month(current_date) - 3) " +
                " group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        //typedQuery.setParameter("status", StatusPedido.PAGO);

        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(arr -> System.out.println(arr[0] + " | " + arr[1]));

    }

}
