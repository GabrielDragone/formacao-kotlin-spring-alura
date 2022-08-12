package br.com.alura.forum.repository

import br.com.alura.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

// Representa a ponte com o banco de dados.
// Herdamos da interface JpaRepository que utiliza generics.
// Precisamos informar no primeiro parâmetro com que classe iremos trabalhar.
// E no segundo o tipo do atributo que representa a chave primaria, que nosso caso é Long.
// Dessa forma, já temos os métodos básicos para o CRUD.
interface TopicoRepository: JpaRepository<Topico, Long> {

    // O Spring Data vai reconhecer que queremos buscar os Topicos pelo atributo Nome dentro do Atributo Curso. Então
    // ele fará um SELECT com JOIN na tabela Curso com nome igual ao que foi passado no parâmetro. Para outros atributos,
    // basta seguir o mesmo padrão de nomenclatura.
    fun findByCursoNome(nomeCurso: String): List<Topico>

}