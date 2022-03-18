package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void fazerJoin(){
        //String jpql = "select p from Pedido p join p.pagamento pag";
        //TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        //List<Pedido> lista = typedQuery.getResultList();

        //String jpql = "select p, pag from Pedido p join p.pagamento pag";
                                  //[0]  [1]
                            //Object[p] [pag]

        //String jpql = "select p, pag from Pedido p join p.pagamento pag where pag.status = 'PROCESSANDO'";
        //String jpql = "select p from Pedido p join p.itens i where i.precoProduto > 10;";
        //String jpql = "select prod from Pedido p
                        // join p.itens i
                        // join i.produto prod
                        // where prod.id = 1";

        //String jpql = "select p from Pedido p join p.pagamento pag";

        //String jpql = "select p,pag,i from Pedido p join p.pagamento pag join p.itens i";

        //String jpql = "select p from Pedido p join p.pagamento pag on pag.status = 'PROCESSANDO'"; o JPA ja compara o id quando fazemos o join
        // mas podemos aumentar esse filtro com o on como se fosse id = id e status = Processando

        String jpql = "select p from Pedido p join p.pagamento pag";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

}
