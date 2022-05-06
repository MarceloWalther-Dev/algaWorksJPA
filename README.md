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
Nesse caso, todos os atributos especificados em seu gráfico de entidade serão tratados como FetchType.EAGER, 
e todos os atributos não especificados serão tratados como FetchType.LAZY. Por outro lado, se você especificar LOAD 
como sua estratégia importando javax.persistence.loadgraph, todos os atributos especificados no gráfico de entidade também serão, 
FetchType.EAGERmas os atributos não especificados usarão seu tipo especificado ou padrão se a entidade não especificar nada.**_


# Cache de segundo nivel
**_Colocamos a propriedade no persistence 
property name="javax.persistence.sharedCache.mode" value="ALL"/
essa propriedade nos permite configurar o cache de segundo nivel.
podemos tbm usar a  <persistence-unit name="Ecommerce-PU">
<shared-cache-mode>ENABLE_SELECTIVE</shared-cache-mode>_**

**ALL -> cache de segundo nivel para todas as entidades\
UNSPECIFIED -> default, significa que quem vai decidir e o hybernate no caso nao vai fazer o cache, traducao e nao especificado\
NONE -> e para nao colocar nenhuma entidade no cache\
DISABLE_SELECTIVE -> e para cachear todas as unidades, porem as que tiverem anotacao @Cacheable(false) nao e para cachear\
ENABLE_SELECTIVE -> so ira cachear as entidades que estiverem com a anotacao @Cacheable pois por default ela ja e true**

**javax.persistence.cache.retrieveMode CacheRetrieveMode** -> _essa propriedade vai nos ajudar na hora de fazer a pesquisa
se vamos querer que a pesquisa fique no cache_

**javax.persistence.cache.storeMode CacheStoreMode** -> _essa propriedade vai nos ajudar na hora de pegar o resultado de uma pesquisa
pegando esse resultado e colocar no cache ou nao._

Usando a **StoreMode** {
_CacheStoreMode.USE -> Adiciona no cache
CacheStoreMode.BYPASS -> Ignorar Retorno e nao vai adicionar o retorno no cache
CacheStoreMode.REFRESH -> e parecido com o use toda a consulta que tem ele pega retorno e joga no cache, Porem ele sempre atualiza o cache_
}


# Lock Otimista e Lock Pessimista

**Otimista**: _É uma regra da aplicação, geralmente usado uma versão para cada entidade com anotação_ **@Version**\
_Gerando uma coluna no banco de dados_\
**@Version**\
_private Integer versao;_

_Quando vamos alterar a entidade vinda do banco o hibernate aplica a query:_
**UPDATE**  produto **SET** nome = '', descricao = '', outros atributos = '' **WHERE** id = 1 and versao = '_versao que veio do banco no caso a_ **5**'

_adicionando a versão no UPDATE acresentando um numero para uma nova versão já que ele foi atualizado._
**UPDATE**  produto **SET** versao = 'nova versão '6 nome = '', descricao = '', outros atributos = '' **WHERE** id = 1 and versao = '**5**'

**Pessimista**: _Usa recursos extras do banco de dados._\
"_Ae banco de dados trava essa linha ai e não deixa mais ninguem alterar ela_"

**LockModeType.PESSIMISTIC_READ** -> _permite que outras threads leiam mas na hora de comitar não permite, pois só quem tem o lock que pode commitar_\
_Quando a proxima thread for comitar vai jogar um erro pq ela vai estar com os dados antigos pois a busca que ela vez e não consegui lockar será alterado com a thread que locou_\
_Ele permite que todo mundo leia mas não atualize_\

**LockModeType.PESSIMISTIC_WRITE** -> _Esse parametro vai permitir o entityManager consultar fazer a alteração, só que não vai permitir salvar
enquanto a outra thread terminar o serviço, em outras palavras ela vai ficar aguardando até que se encerre a transação_\
*_*Se atentar para não colocar junto com o lockOtimista*_*\
_Usamos para pegar os dados atualizados, pegamos do banco e atualizamos e persistimos_\

**Outros tipos de lock**
**OPTMISTIC** -> _Lock Otimista._\
**OPTMISTIC_FORCE_INCREMENT** ->_Quando buscamos a entidade e não alteramos nada, o jpa não vai entender como uma alteração
então não vai mudar, se usar OPTMISTIC_FORCE_INCREMENT ele vai forçar uma alteração na versão_\
**PESSIMISTIC_FORCE_INCREMENT** -> _É uma mistura de_ **PESSIMISTIC_WRITE** _com_ **OPTMISTIC_FORCE_INCREMENT**\
