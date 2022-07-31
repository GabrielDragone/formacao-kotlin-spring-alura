// Documentação do data class: https://kotlinlang.org/docs/data-classes.html
// Dicas de como usar o data class e sua diferença se comparada com apenas class:
fun main(args: Array<String>) {
    println()
    println("Hello World!")

    val gabriel = Pessoa(
        nome = "Gabriel Alves",
        idade = 27
    )
    gabriel.falar()
    println()

    // componentN(): Não é possível atribuir variaveis de acordo com os dados do objeto gabriel pois não é uma data class:
    //val(nome, idade) = gabriel
    val(nome) = gabriel // componentN(): Só funciona pois dentro da classe Pessoa foi implementado o component1().
    println("Olá $nome")
    println()

    val gabriel2 = Pessoa(
        nome = "Gabriel Alves",
        idade = 27
    )

    val gabriel3 = gabriel2
    //val gabriel4 = gabriel2.copy() // copy(): Não funciona pq não é um data class.

    println("Validações de objetos da class Pessoa (false): ") // hashCode() não implementado, NÃO são iguais pois não apontam pra mesma referência.
    println(gabriel == gabriel2)
    println()

    println("Validações de objetos da class Pessoa (true): ") // hashCode() não implementado, SÃO iguais pois apontam pra mesma referência.
    println(gabriel2 == gabriel3)
    println()

    val documento = Documento(
        rg = "12.345.789-0",
        cpf = "123.456.789-10"
    )

    // componentN(): É possível pegar os dados do objeto documento e passar pras variaveis rg e cpf:
    val(rg1, cpf) = documento
    println("Meu RG é: $rg1 e meu CPF é: $cpf")
    println()
    documento.mostrar()
    println()

    val documento2 = Documento(
        rg = "12.345.789-0",
        cpf = "123.456.789-10"
    )

    val documento3 = documento2.copy() // copy(): Método implementado pra copiar a classe.
    val documento4 = documento2.copy(rg = "14.785.968-10") // Faz a mesma coisa que o acima, porém podemos atribuir um valor diferente em alguma propriedade

    println("Validações de objetos da data class Documento: ") // hashCode() implementado, são iguais pois a validação é feita em cima das propriedades:
    println(documento == documento2)
    println()

    println(gabriel)        // class imprime: Pessoa@19dfb72a
    println(documento)      // data class imprime: Documento(rg=12.345.789-0, cpf=123.456.789-10)
    println(documento2)     // data class imprime: Documento(rg=12.345.789-0, cpf=123.456.789-10)
    println(documento3)     // data class imprime: Documento(rg=12.345.789-0, cpf=123.456.789-10)
    println(documento4)     // data class imprime: Documento(rg=12.345.789-0, cpf=123.456.789-10)

}

//class Pessoa(val nome: String, idade: Int) {
class Pessoa(val nome: String, val idade: Int) { // Se não usar val ou var, não conseguimos pegar a variavel dentro da class.

    fun falar() {
        println(this.nome + " disse: Oi, meu nome é ${this.nome} e tenho ${this.idade} anos!") // Utilizando ${this.variavel}, conseguimos pegar o valor correto: Gabriel Alves disse: Oi, meu nome é Gabriel Alves e tenho 27 anos!
        println(nome + " disse: Oi, meu nome é $nome e tenho $idade anos!") // Ou podemos usar assim direto
    }

    operator fun component1() = this.nome // componentN(): implementado na mão.

}

// Utilizando o prefixo "data class":
// - O Construtor Primário precisar ter pelo menos um parâmetro.
// - É obrigatório usar var ou val, pois as implementações dos métodos são baseadas nas properties.
// - As data classes não podem ser abstracts, open, sealed ou inner.
// - Apenas as propriedades do Construtor Primario serão utilizadas nas implementações.
// - Dessa forma, teremos algumas implementações padrões como:
// - equals() / hashCode():
//      Implementação que pega a referência do objeto na memória. Nesse caso, ela vai ser focada dentro das properties
//      (rg e cpf) que temos no construtor primario.
// - toString:
//      Imprime os dados do objeto. Exemplo: Documento(rg=12.345.789-0, cpf=123.456.789-10).
// - componentN():
//      Funções correspondentes às propriedades em sua ordem de declaração.
//      Destructuring Declarations: conseguimos pegar os atributos da classe e atribuir à variáveis na ordem que foram declarados.
// - copy():
//      Faz a cópia exata de uma classe a atriubui para outra.
//      Também é possível copiar o objeto inteiro, e alterar algumas propriedades.
data class Documento(val rg: String, val cpf: String) { // Construtor primário

    lateinit var nomeMae: String // Não será utilizada nas implementações geradas automaticamente. Ela deve estar no Construtor Primario

    fun mostrar() {
        println("O RG é: $rg e o CPF é: $cpf")
    }

}

