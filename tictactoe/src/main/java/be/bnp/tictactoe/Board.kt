package be.bnp.tictactoe

import be.bnp.tictactoe.exceptions.SpaceOccupiedOnBoardException
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol

class Board(internal val currentState: List<List<Symbol>> = constructBoardState()) {

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

    private fun constructBoardStateWithSymbolOnCoordinate(coordinate: Coordinate, newSymbol: Symbol) =
        constructBoardState { (x, y) ->
            if (coordinate.x == x && coordinate.y == y) {
                newSymbol
            } else {
                currentState[x][y]
            }
        }

    fun addSymbol(symbol: Symbol, coordinate: Coordinate): Board {
        val newSymbol = when (val currentSymbol = currentState[coordinate.x][coordinate.y]) {
            Symbol.X,
            Symbol.O -> throw SpaceOccupiedOnBoardException(currentSymbol)
            Symbol.Blank -> symbol
        }
        return Board(constructBoardStateWithSymbolOnCoordinate(coordinate, newSymbol))
    }

    override fun toString() =
        StringBuilder().apply {
            appendln("TicTacToe")
            (1..3).forEach { row ->
                appendln(currentState[row - 1].joinToString("\t") { it.toString() })
            }
        }.toString()
}
