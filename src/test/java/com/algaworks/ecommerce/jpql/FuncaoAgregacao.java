package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncaoAgregacao extends EntityManagerTest {

    @Test
    public void aplicarFuncaoAgregacao() {
        //avg, count, min, max, sum

        //vai pegar o total somar e dividir por dois
        //String jpql = "select avg(p.total) from Pedido p";
        //String jpql = "select avg(p.total) from Pedido p where p.dataCriacao >= current_date";

        //count não importa o atributo de retorno, ele vai contar quantas linhas iram voltar
        //String jpql = "select count(*) from Pedido p ";

        // vai me trazer o pedido com o menor valor
        // select p.id from pedido p where p.total in (select min(pedido.total) from pedido);
        //String jpql = "select min(p.total) from Pedido p";

        //vai me trazer o pedido com o maior valor
        //String jpql = "select max(p.total) from Pedido p";

        //vai fazer a soma de todos os pedidos que estão na base
        String jpql = "select sum(p.total) from Pedido p";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jpql, Number.class);

        List<Number> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj));
    }

}
