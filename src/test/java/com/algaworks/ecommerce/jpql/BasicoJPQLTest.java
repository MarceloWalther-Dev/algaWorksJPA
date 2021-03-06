package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void buscarPorIdentificador() {
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);

//        List<Pedido> resultList = typedQuery.getResultList(); quando e uma lista

        Pedido pedido = typedQuery.getSingleResult(); // uma lista simples

        Assert.assertNotNull(pedido);
    }

    @Test
    public void selecionarUmAtributoParaRetorno() {

        String jpql = "select p.nome from Produto p";
        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);

        List<String> listaNomeProduto = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(listaNomeProduto.get(0).getClass()));

        String jpqlCliente = "select pedido.cliente from Pedido pedido";
        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);

        List<Cliente> listaCliente = typedQueryCliente.getResultList();
        listaCliente.forEach(cliente -> System.out.println(cliente.getNome()));

        Assert.assertTrue(Cliente.class.equals(listaCliente.get(0).getClass()));
    }

    @Test
    public void projetarObjeto() {
        String jpql = "select p.id, p.nome from Produto p"; // [projecao =  p.id, p.nome]

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);// usamos Object[] pq sao dois atributos de diferentes tipos
        // int e string o mais generico
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(i -> System.out.println(i[0] + ", " + i[1]));

        Assert.assertTrue(lista.get(0).length == 2);
    }

    @Test
    public void projetarNoDTO(){
        String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) from Produto";

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> produtoDTOList = typedQuery.getResultList();

        Assert.assertFalse(produtoDTOList.isEmpty());

        produtoDTOList.forEach( dto -> System.out.println(dto.getId() + ", "+ dto.getNome()));
    }

}
