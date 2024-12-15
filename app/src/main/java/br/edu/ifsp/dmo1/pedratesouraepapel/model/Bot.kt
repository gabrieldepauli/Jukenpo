package br.edu.ifsp.dmo1.pedratesouraepapel.model

// Cria um bot para quando tiver apenas 1 player
class Bot(val nome: String){

    var points: Int = 0
        private set
    fun recordPoint(){
        points += 1
    }

    // Função criada para sortear a arma que o bot usará
    fun selectWeapon(): Weapon{
        val weapons = listOf(Rock, Paper, Scissors)

        return weapons.random()
    }

}