package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PassandoParametrosTest extends EntityManagerTest {

    @Test
    public void passarParametro(){
        String jpql = "select p from Pedido p join p.pagamento pag where p.id = :pedidoID and pag.status = ?1";


        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("pedidoID", 2);
        typedQuery.setParameter(1, StatusPagamento.PROCESSANDO);

        List<Pedido> resultList = typedQuery.getResultList();
        Assert.assertTrue(resultList.size() == 1);
    }

    @Test
    public void passarParametroDate(){
        String jpql = "select ntf from NotaFiscal ntf where ntf.dataEmissao <= ?1";


        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);

        List<NotaFiscal> resultList = typedQuery.getResultList();
        Assert.assertTrue(resultList.size() == 1);
    }


}
