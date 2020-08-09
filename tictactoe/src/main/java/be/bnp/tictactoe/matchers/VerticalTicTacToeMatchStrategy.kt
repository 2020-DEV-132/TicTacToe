package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol

class VerticalTicTacToeMatchStrategy(private val delegatingMatchStrategy: TicTacToeMatchStrategy) :
    TicTacToeMatchStrategy {
    override fun hasMatch(list: List<List<Symbol>>): Boolean {
        val firstLine = listOf(list[0][0], list[1][0], list[2][0])
        val secondLine = listOf(list[0][1], list[1][1], list[2][1])
        val thirdLine = listOf(list[0][2], list[1][2], list[2][2])
        return delegatingMatchStrategy.hasMatch(listOf(firstLine, secondLine, thirdLine))
    }
}
