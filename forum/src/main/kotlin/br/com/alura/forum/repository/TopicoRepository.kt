package br.com.alura.forum.repository

import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

// Representa a ponte com o banco de dados.
// Herdamos da interface JpaRepository que utiliza generics.
// Precisamos informar no primeiro parâmetro com que classe iremos trabalhar.
// E no segundo o tipo do atributo que representa a chave primaria, que nosso caso é Long.
// Dessa forma, já temos os métodos básicos para o CRUD.
interface TopicoRepository: JpaRepository<Topico, Long> {

    // O Spring Data vai reconhecer que queremos buscar os Topicos pelo atributo Nome dentro do Atributo Curso. Então
    // ele fará um SELECT com JOIN na tabela Curso com nome igual ao que foi passado no parâmetro. Para outros atributos,
    // basta seguir o mesmo padrão de nomenclatura.
    //fun findByCursoNome(nomeCurso: String): List<Topico>
    fun findByCursoNome(nomeCurso: String, pacinacao: Pageable): Page<Topico> // O Page, além de ter a lista, ele tem
    // os dados da paginação. Que são importantes pro front, pois ele precisa saber qual página está, quantas páginas
    // tem e quantos registros tem tbm. Todas essas informações tem dentro do Page.

    // Temos duas formas de fazer a consulta. A primeira é através do Spring, onde colocamos o nome concatenado (exemplo acima) em ordem e o Spring gera a consulta.
    // E utilizando um nome personalizado, anotando a função com o @Query e realizando a consulta, conforme exemplo abaixo. Nele podemos ver
    // que temos um new (esquema da JPA) após o SELECT, que retorna o objeto ja convertido, conforme definimos.
    @Query("SELECT new br.com.alura.forum.dto.TopicoPorCategoriaDto(curso.categoria, count(t)) " +
            "FROM Topico t JOIN t.curso curso " +
            "GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoriaDto>
    @Query("select t from Topico t where t.respostas is empty")
    fun pendentes(): List<Topico>

}