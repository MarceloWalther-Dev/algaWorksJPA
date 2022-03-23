package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncaoHaving extends EntityManagerTest {

    @Test
    public void condicionarAgrupamentoComHaving(){
        //total de vendas dentre as categorias que mais vendem a cima de 100 reais

        String jpql = "select cat.nome, sum(ip.precoProduto) from ItemPedido ip " +
                " join ip.produto pro " +
                " join pro.categorias cat " +
                " group by cat.id " +
                " having avg(ip.precoProduto) > 1500";
        //só posso usar as funçoes de agregação ou as propriedades do group by

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = typedQuery.getResultList();

        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach(arr -> System.out.println(arr[0] + " | " + arr[1]));
    }

}
