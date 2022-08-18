# Repositório com todos os projetos da Formação Kotlin Spring do Alura

https://cursos.alura.com.br/formacao-kotlin-spring-boot

### 01 - Desenvolvendo a API
O primeiro passo será a criação do projeto e implementação das funcionalidades da API, com o mapeamento dos endpoints, validações e persistência em banco de dados.
* Data class no Kotlin (dentro do projeto dataclass)
  * Explicações e exemplos data class x class. 
* API REST com Kotlin e Spring Boot: Camada Web (dentro do projeto forum)
  * Criação do projeto via Spring Initializr.
  * Utilização de Controllers (classe responsável pelos endpoint's).
  * CRUD (Utilizando todos os verbos do HTTP: GET (listar), POST (cadastrar), PUT (atualizar) e DELETE (deletar)).
  * Utilização Services (classe responsável pelas regras de negócio da aplicação).
  * Utilização de DTO's para representar os dados de Entrada e Saida da API.
  * Utilização de Mappers para realizar a conversão/parser do DTO para Dominio (model), vice-versa.
  * Boas práticas do REST utilizando ResponseEntity e ResponseStatus.
  * Utilização de Bean Validations.
  * Tratamento de erros na API utilizando RestControllerAdvice e ExceptionHandler.
* API REST com Kotlin e Spring Boot: Camada de persistência:
  * Utilizando o mesmo projeto forum, foi implementada a parte de persistência da API.
  * Configuração do Spring Data JPA.
  * Mapeamento de entidades.
  * Utilização de interface JpaRepository.
  * Utilização de migrations com Flyway.
  * Utilização de Cache.
  * Filtros, paginação e ordenação.
  * Montar queries personalizadas.
  * Configuração para acesso de banco via console no Navegador.
  * Acessar diretamente o EntityManager.
* APIs com Kotlin e Spring Data REST:
  * Abrir projeto spring-data-rest-example para verificar as anotações (README.md) e exemplos de implementação.
