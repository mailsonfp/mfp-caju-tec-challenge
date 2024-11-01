### **L1** - feito conforme descrição
### **L2** - feito conforme descrição
### **L3** - não sei se compreendi de forma integral e consequentemente, não sei se implementei da forma correta
### **L4**
Analisando a situação com os detalhes fornecidos, uma vez que a concorrência é pequena, pode-se usar
um lock otimista, utilizando uma versão da tabela de saldos, caso não seja possível atualzar a versão
reinicia-se a operação, com um limite de tentativas para respeitar o tempo de timeout, caso estoure 
as tentativas, retorna como transação recusada por erro e força a passar novamente o cartão.
Uma vez que o processamento não é pesado, acredito não ser problema executar mais de uma vez.

Também existe o lock pessimista, mas conforme as poucas informações, acredito que não seja a melhor
solução dentro desse cenário, porque pode travar um registro para demais consumos, algum fluxo que
alimente um lake por exemplo

## Sobre a solução desenvolvida:
Eu criei um swagger: http://localhost:8080/swagger-ui/index.html#/
Em relação aos packages, cada pacote funciona como um módulo e suas dependencias, seguindo o modelo
de clean architeture, que uso atualmente no trabalho
Eu utilizei o banco em memória h2, que está sendo salvo em um arquivo "data/caju"
Não é necessário inserir dados manualmente, as apis criadas são suficientes para alimentar a base
para realizar um teste de autorização de transação
- inserir um cliente
- inserir um conta vinculada a um cliente iniciando com saldos zerados
- movimentar a conta e inserir saldo
- desativar uma conta
- ativar uma conta
- inserir merchant
- inserir mcc para um merchant
- demais apis de get para visualizar as informações
** visualizar swagger

Dentro do diretório /files, adicionei a coleção do postman que utilizei para testes e um diagrama básico 
de um fluxo considerando usos de alguns recursos da aws

Mailson Fernando Pereira
email: mailsonfp.dev@gmail.com
#### github do projeto: https://github.com/mailsonfp/mfp-caju-tec-challenge
#### github pessoal: https://github.com/mailsonfp