package br.com.alura.forum.repository

import br.com.alura.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

// Representa a ponte com o banco de dados.
// Herdamos da interface JpaRepository que utiliza generics.
// Precisamos informar no primeiro parâmetro com que classe iremos trabalhar.
// E no segundo o tipo do atributo que representa a chave primaria, que nosso caso é Long.
// Dessa forma, já temos os métodos básicos para o CRUD.
interface TopicoRepository: JpaRepository<Topico, Long> {
}