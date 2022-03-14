#Configurando operações em cascata

cascate = CascadeType.**PERSISTE** - _salva_ \
cascate = CascadeType.**MERGE** - _atualizar_ \
cascate = CascadeType.**REMOVE** - _remover todos os itens_\
cascate = CascadeType.**DETACH** - _desanexar_ \
cascate = CascadeType.**REFRESH** - _quando chamar o getRefresh vai atualizar o atributo tbm_\
\
**orphanRemoval** = _Remove os filhos,Pedido e pedido tem itens com essa anotacao entao removendo pedido
automaticamente removeremos os itens que estao relacionado ao pedido excluido_\


-  **JPQL** _=> se referencia a entidade e nao o nome da tabela_