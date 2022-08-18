package br.com.alura.springdatarestexample.repository

import br.com.alura.springdatarestexample.model.Professor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource

// CrudRepository é uma classe da Spring Data REST que é responsável por identificar o Model e criar TODOS os endpoints
// necessários para as operações CRUD.
@RepositoryRestResource(path = "professores") // Altera a URI gerada automaticamente de professors para professores, ficando dessa forma correto.
interface ProfessorRepository: CrudRepository<Professor, Long> {

    @RestResource(exported = false) // Sobescreve método e informa à nossa aplicação que não queremos torná-lo publico para nossa API. Retornará um 405 Method Not Allowed.
    override fun deleteById(id: Long) {
    }

    @RestResource(exported = false) // Obrigatóriamente devemos sobescrever ambos os métodos, por isso esse também ta aqui. Detalhes? https://docs.spring.io/spring-data/rest/docs/current/reference/html/#customizing-sdr.hiding-repository-crud-methods
    override fun delete(entity: Professor) {
    }

}