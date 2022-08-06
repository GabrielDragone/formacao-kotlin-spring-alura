package br.com.alura.forum.mapper

import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class RespostaViewMapper: Mapper<Resposta, RespostaView> {

    override fun map(resposta: Resposta): RespostaView {
        return RespostaView(
            id = resposta.id,
            mensagem = resposta.mensagem,
            solucao = resposta.solucao,
            autorNome = resposta.autor.nome,
            topicoId = resposta.topico?.id
        )
    }

}
