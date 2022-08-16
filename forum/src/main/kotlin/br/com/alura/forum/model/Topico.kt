package br.com.alura.forum.model

import br.com.alura.forum.enum.StatusTopico
import java.time.LocalDateTime
import javax.persistence.*

@Entity // Anota a classe como uma Entidade da JPA, mapeando uma tabela no banco de dados.
data class Topico (
    @Id // Informa à JPA que o campo id é uma ID no Banco de Dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Informa à JPA que è a aplicação que vai gerenciar os Id's e não o banco de dados.
    var id: Long? = null, // ? retorna null em caso de falha de conversão. var é variavel, ou seja, muda, ava???
    var titulo: String, // val é constante, ou seja, esse valor nunca vai mudar
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(), // No momento da criação já pega a data.
    @ManyToOne // Cardialidade, Um Curso pode ter vários Tópicos Relacionados. Um Topico pertence àpenas um Curso.
    val curso: Curso,
    @ManyToOne // O Tópico pertence àpenas Um Usuário. Um Usuario pode ter vários Tópicos.
    val autor: Usuario,
    @Enumerated(value = EnumType.STRING) // Precisamos informar assim pra JPA saber que iremos salvar o nome de um Enum e não o index dele.
    var status: StatusTopico = StatusTopico.NAO_RESPONDIDO, // Enum, qnd criar já cria como não respondido.
    @OneToMany(mappedBy = "topico") // Um Tópico pode ter várias respostas. Como na classe Resposta tbm tem o mapeamento
    // de Topico, precisamos fazer o relacionamento bidirecional e informar o nome do atributo na classe Resposta.
    val respostas: List<Resposta> = ArrayList()
)