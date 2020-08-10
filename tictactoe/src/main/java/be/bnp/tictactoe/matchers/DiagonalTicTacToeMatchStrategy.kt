package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol

class DiagonalTicTacToeMatchStrategy(private val horizontalMatchStrategy: HorizontalTicTacToeMatchStrategy = HorizontalTicTacToeMatchStrategy) :
    TicTacToeMatchStrategy {
    override fun hasMatch(list: List<List<Symbol>>): Boolean {
        val leftTopToRightBottom = listOf(list[0][0], list[1][1], list[2][2])
        val bottomLeftToTopRight = listOf(list[2][0], list[1][1], list[0][2])
        return horizontalMatchStrategy.hasMatch(listOf(leftTopToRightBottom, bottomLeftToTopRight))
    }
}
