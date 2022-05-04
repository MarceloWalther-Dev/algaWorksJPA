#Configurando operações em cascata

cascate = CascadeType.**PERSISTE** - _salva_ \
cascate = CascadeType.**MERGE** - _atualizar_ \
cascate = CascadeType.**REMOVE** - _remover todos os itens_\
cascate = CascadeType.**DETACH** - _desanexar_ \
cascate = CascadeType.**REFRESH** - _quando chamar o getRefresh vai atualizar o atributo tbm_\
\
**orphanRemoval** = _Remove os filhos,Pedido e pedido tem itens com essa anotacao entao removendo pedido
automaticamente removeremos os itens que estao relacionado ao pedido excluido_\


- **JPQL** _=> se referencia a entidade e nao o nome da tabela_

<h1>Diferenca de Query e TypeQuery</h1>

**TypedQuery**<Pedido> typedQuery = _entityManager.createQuery(jpql, Pedido.class)
Pedido pedido = typedQuery.getSingleResult();_

**Query** query = _entityManager.createQuery(jpql);_ \
**Pedido** pedido2 = _(Pedido) query.getSingleResult();_

# EntityGraph
_**Você especifica FETCHcomo sua estratégia importando javax.persistence.fetchgraphno arquivo que contém a entidade. 
Nesse caso, todos os atributos especificados em seu gráfico de entidade serão tratados como FetchType.EAGER, e todos os atributos não especificados serão tratados como FetchType.LAZY. Por outro lado, se você especificar LOADcomo sua estratégia importando javax.persistence.loadgraph, todos os atributos especificados no gráfico de entidade também serão, FetchType.EAGERmas os atributos não especificados usarão seu tipo especificado ou padrão se a entidade não especificar nada.**_