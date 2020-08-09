package be.bnp.tictactoe

import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol

class Board(private val currentState: List<List<Symbol>> = constructBoardState()) {

    companion object Factory {
        internal fun constructBoardState(symbolConstructorFunc: (Coordinate) -> Symbol = { Symbol.Blank }): List<List<Symbol>> =
            ArrayList<List<Symbol>>().apply {
                (0 until 3).onEach { row ->
                    add(row, List(3) { column ->
                        symbolConstructorFunc(Coordinate(row, column))
                    })
                }
            }
    }

    override fun toString() =
        StringBuilder().apply {
            appendln("TicTacToe")
            (1..3).forEach { row ->
                appendln(currentState[row - 1].joinToString("\t") { it.toString() })
            }
        }.toString()
}
