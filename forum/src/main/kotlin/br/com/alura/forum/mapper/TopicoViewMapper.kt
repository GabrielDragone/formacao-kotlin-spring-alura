package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component // No Spring, tudo são Componentes
class TopicoViewMapper: Mapper<Topico, TopicoView> {

        // Cria um objeto TopicoView através do objeto Topico:
        override fun map(t: Topico): TopicoView {
                return TopicoView( // Para cada registro do tipo Topico da lista, quero mapear ela e transforma-la no tipo TopicoView.
                        id = t.id,
                        titulo = t.titulo,
                        mensagem = t.mensagem,
                        dataCriacao = t.dataCriacao,
                        status = t.status
                )
        }

}