package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

public class BasicoCriteriaTest extends EntityManagerTest {


    @Test
    public void projetarOResultadoDTO() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoDTO> criteriaQuery = criteriaBuilder.createQuery(ProdutoDTO.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);
        // no metodo contruct do criteriaBuiler o primeiro e o dto que eu quero retornar o segundo parametro sao os daddos necessario do construtor do dto
        criteriaQuery.select(criteriaBuilder
                .construct(ProdutoDTO.class, root.get("id"), root.get("nome")));

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(criteriaQuery);
        List<ProdutoDTO> produtos = typedQuery.getResultList();

        Assert.assertNotNull(produtos);

        produtos.forEach( produtoDTO -> System.out.println("ID: " + produtoDTO.getId() + "\n Nome: " + produtoDTO.getNome()));


    }


    //Projecoes
    @Test
    public void projetarOResultadoComTuple() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<Produto> root = criteriaQuery.from(Produto.class);

        //criteriaQuery.multiselect(root.get("id"), root.get("nome"));
        criteriaQuery.select(criteriaBuilder.tuple(root.get("id").alias("id"), root.get("nome").alias("nome")));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Tuple> produtos = typedQuery.getResultList();

        Assert.assertNotNull(produtos);

        //produtos.forEach(p -> System.out.println("ID - "+ p.get(0)+ "\n Nome - "+ p.get(1)));
        produtos.forEach(p -> System.out.println("ID - " + p.get("id") + "\n Nome - " + p.get("nome")));

    }

    //Projecoes
    @Test
    public void projetarOResultado() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.multiselect(root.get("id"), root.get("nome"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> produtos = typedQuery.getResultList();

        Assert.assertNotNull(produtos);

        produtos.forEach(imprimirAtributosDosObjetosComArray2Posicoes);

    }


    @Test
    public void retornarTodosOsprodutos() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Produto> produtos = typedQuery.getResultList();

        produtos.forEach(imprimirAtributoDosObjetos);

        Assert.assertNotNull(produtos);
    }


    @Test
    public void selecionarUmAtributoParaRetorno() {
        //para criar a query precisamos do criteria builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //criando a query
        CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
        //Root é o que vem depois do from em uma jpql
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root.get("cliente"));

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        //aqui eu coloco o que eu vou retornar
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(criteriaQuery);
        Cliente cliente = typedQuery.getSingleResult();
        Assert.assertNotNull(cliente);


    }


    @Test
    public void buscarPorIdentificador() {
        //String jpql = "select p from Pedido p where p.id = 1";

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);// e a projecao
        Root<Pedido> root = criteriaQuery.from(Pedido.class); // root e a entitydade do from

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        Pedido pedido = typedQuery.getSingleResult(); // uma lista simples

        Assert.assertNotNull(pedido);
    }


    Consumer<Object> imprimirAtributoDosObjetos = o -> System.out.println(o.toString());

    Consumer<Object[]> imprimirAtributosDosObjetosComArray2Posicoes = o -> System.out.println("ID - " + o[0] + " \nNome - " + o[1]);

    Consumer<ProdutoDTO> imprimirAtributoDoProdutoDTO = produtoDTO -> System.out.println();



}
