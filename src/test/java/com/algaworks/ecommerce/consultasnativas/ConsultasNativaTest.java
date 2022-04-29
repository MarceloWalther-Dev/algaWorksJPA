package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
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

}
