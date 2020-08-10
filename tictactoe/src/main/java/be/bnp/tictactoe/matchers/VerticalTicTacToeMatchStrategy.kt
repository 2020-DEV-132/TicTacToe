package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol

class VerticalTicTacToeMatchStrategy(private val horizontalMatchStrategy: HorizontalTicTacToeMatchStrategy = HorizontalTicTacToeMatchStrategy) :
    TicTacToeMatchStrategy {
    override fun hasMatch(list: List<List<Symbol>>): Boolean {
        val firstLine = listOf(list[0][0], list[1][0], list[2][0])
        val secondLine = listOf(list[0][1], list[1][1], list[2][1])
        val thirdLine = listOf(list[0][2], list[1][2], list[2][2])
        return horizontalMatchStrategy.hasMatch(listOf(firstLine, secondLine, thirdLine))
    }
}
