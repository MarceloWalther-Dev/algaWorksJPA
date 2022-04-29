package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

public class ConsultasNativaTest extends EntityManagerTest {

    @Test
    public void executarSQL(){
//        String sql = "select * from produto";
        String sql = "select id, nome from produto";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> listProduto = query.getResultList();

        listProduto.forEach( p -> System.out.println(String.format("Produto => ID: %s, Nome: %s", p[0], p[1])));
    }

    @Test
    public void retornandoEntidade(){
//        String sql = "select * from produto";
//        String sql = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, foto  from produto";
        String sql = "select p.* from produto p";

        //Quando a tabela nao esta igual a entidade temos que colocar alias e colocar o valor como null para conseguir trazer no formato da entidade.
//        String sql = " select id, nome, descricao, null AS data_criacao, null data_ultima_atualizacao, preco, null foto from erp_produto";

        Query query = entityManager.createNativeQuery(sql, Produto.class);

        List<Produto> listProduto = query.getResultList();

        listProduto.forEach( p -> System.out.println(String.format("Produto => ID: %s, Nome: %s", p.getId(), p.getNome())));

        listProduto.forEach(System.out::println);
    }


    @Test
    public void passarParametro(){
        String sql = "select p.* from produto p where p.id = :id";
        Query query = entityManager.createNativeQuery(sql, Produto.class);
        query.setParameter("id", 1);

        List<Produto> listProduto = query.getResultList();

        listProduto.forEach( p -> System.out.println(String.format("Produto => ID: %s, Nome: %s", p.getId(), p.getNome())));
    }


}
