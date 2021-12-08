package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacaoComTransacaoTest extends EntityManagerTest {

    @Test
    public void impedirOperacaoComBancoDeDados(){
        Produto produto = entityManager.find(Produto.class, 1);
        entityManager.detach(produto);

        entityManager.getTransaction().begin();
        produto.setNome("KinderOvo");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertEquals("KinderOvo", produtoVerificacao.getNome());


    }




    @Test
    public void inserirObjetoMerge(){
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone Rode Videmic");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setPreco(new BigDecimal(1000));
        entityManager.getTransaction().begin();

        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }

    @Test
    public void inserirOPrimeiroObjeto(){
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Camera Canon");
        produto.setDescricao("Melhore as suas fotos");
        produto.setPreco(new BigDecimal(5000));

        //Joga para a memoria
        entityManager.persist(produto);

        entityManager.getTransaction().begin();

        //Ele comita porem ainda permanece com a entidade em memoria
        entityManager.getTransaction().commit();
        // limpa a memoria do entityManager
        entityManager.clear();

        //Esse find não foi no banco de dados ele pegou da memoria do entityManager
        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
    }


    @Test
    public void removerObjeto(){
        Produto produtoRemovido = entityManager.find(Produto.class, 3);
        entityManager.getTransaction().begin();
        entityManager.remove(produtoRemovido);
        entityManager.getTransaction().commit();

        Produto produtoVerificado = entityManager.find(Produto.class, 3);
        Assert.assertNull(produtoVerificado);
    }

    @Test
    public void atualizarObjeto(){
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Conheça o novo kindle");
        produto.setPreco(new BigDecimal(599));

        //Para usar o merge é necessario preencher o produto se não ele vai deixar nulo o que não for preenchido
        entityManager.merge(produto);
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao);
        Assert.assertEquals("Kindle Paperwhite",produtoVerificacao.getNome());
    }


    @Test
    public void abrirEFecharATransacao(){

        Produto produto = new Produto();
        //Abrir transacao, marco o inicio da transação
        entityManager.getTransaction().begin();

        //dentro da operação eu realizo a alteração do banco de dados

        //
        entityManager.persist(produto);
        entityManager.merge(produto);
        entityManager.remove(produto);



        //Vou comitar a transação
        entityManager.getTransaction().commit();
    }

}
