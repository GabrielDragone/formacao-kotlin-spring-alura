package br.com.alura.forum.mapper


interface Mapper<T, U> {

    // Como o Mapper é uma interface, a implementação do método abaixo será feita dentro da classe que Implementar ela.
    fun map(t: T): U

}
