# APIs com Kotlin e Spring Data REST:

Projeto de exemplo de utilização do Spring Data REST pra gerar um CRUD de maneira mais rápida possível mostrado na Formação Kotlin + Spring do Alura.

### Parte 01: https://www.alura.com.br/artigos/kotlin-spring-data-reset-parte-1
* Criando uma aplicação API REST o mais rápido possível, utilizando as ferramentas fornecidas pelo SPRING e implementando o CRUD.
* Vantagem do Kotlin, linguagem que roda na JVM e é complemetamente interoperável com JAVA. Além de ser menos verbosa.
* Spring Data Rest:
  * Principais caracteristas:
    * Expõe uma API REST detectável para seu modelo de domínio usando HAL como tipo de mídia.
    * Expões recursos de coleção, item e associação que representam seu modelo. 
    * Atualmente suporta JPA, MongoDB, Neo4j, Solr, Cassandra, Gemfire. 
    * Permite personalizações avançadas nos recursos padrões expostos. 
    * Dentre outros, mas a que mais se destaca é a facilidade na exposição de recursos baseados nas classes de modelo da aplicação.
* O HAL: é uma padronização para tráfego de informações que está em desenvolvimento e visa, basicamente, criar uma representação da informação baseada em links que possibilitem a navegação e descobrimento de novas informações.
* Mãos à obra:
  * Criar projeto: https://start.spring.io/
  * Dependências: Spring Web, Spring Data JPA, Rest Repositories e H2 Database.
  * Gerar projeto.
  * Abrir na IDE.
  * Criar model e fazer as anotações pro JPA se configurar.
  * Criamos a Repository da model, utilizando a herença de CrudRepository, que é uma interface que pertence ao Spring Data REST responsável por identificar o modelo de dados e criar TODOS os endpoints necessários para as operações CRUD.
  * E por ultimo inserimos dentro da .properties a configuração para informar que nossa aplicação trabalhará com JSON.
  * Feito o passo a passo acima, podemos realizar os testes via POSTMAN (ao invés de usar o CURL igual eles fizeram).
### Parte 02: https://www.alura.com.br/artigos/kotlin-spring-data-reset-parte-2
* Como não definimos nenhum Controller com o RequestMapping, as URI’s são geradas automaticamente e podem não ficar no padrão que queremos. Exemplo. Professor vai ficar Professors ao invés de Professores. Logo, o endpoint ficaria no padrão localhost:8080/professors, ou seja a URI é construída com base no plural dos nomes das classes (A biblioteca Evo Inflector faz isso).
* Para alterarmos esse padrão, podemos anotar nossa Repository com @RepositoryRestResource(path = “professores”).
* Outro detalhe importante, seria a utilização de “/api” na URI, que também podemos alterar, para isso, dentro de .properites podemos colocar a configuração spring.data.rest.basePath=/api
* E por último, no cenário, não queremos que seja realizada as operações de DELETE. Para isso devemos sobrescrever dentro de repository ambos os métodos deleteById e delete usando o @RestResource(exported = false).
Fim.
