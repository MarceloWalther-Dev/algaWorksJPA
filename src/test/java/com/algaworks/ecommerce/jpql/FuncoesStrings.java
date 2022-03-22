package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class FuncoesStrings extends EntityManagerTest {
    //concat, length, locate, substring, lower, upper, trim

    @Test
    public void aplicarFuncao(){
        //String jpql = "select c.nome, concat('Categoria: ', c.nome) from Categoria c";

       //String jpql = "select c.nome, length(c.nome) from Categoria c"; //vai retornar o tamanho da string no caso o tamanho do nome

        // String jpql = "select c.nome, locate('a', c.nome) from Categoria c"; //retorna o indice do a e se nao encontrar retorna 0

        //String jpql = "select c.nome, substring(c.nome, 1, 2) from Categoria c";// ele vai pegar da string nome dois caracter comecando no 1

        //String jpql = "select c.nome, lower(c.nome, 1, 2) from Categoria c";// coloca em caixa baixa

        //String jpql = "select c.nome, upper(c.nome, 1, 2) from Categoria c";//caixa alta

        //String jpql = "select c.nome, trim(c.nome, 1, 2) from Categoria c";// remove os espacos em branco do inicio e do final



        //String jpql = "select c.nome , length (c.nome) from Categoria c where length(c.nome) > 10";

        String jpql = "select c.nome, length(c.nome) from Categoria c where substring(c.nome,1 ,1) = 'E'";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> resultList = typedQuery.getResultList();
        Assert.assertFalse(resultList.isEmpty());

        resultList.forEach( arr -> System.out.println(arr[0] + " - " + arr[1]));
    }



}
