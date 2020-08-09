package be.bnp.tictactoe

import be.bnp.tictactoe.exceptions.SpaceOccupiedOnBoardException
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import be.bnp.tictactoe.model.TicTacToeEvent

class TicTacToe internal constructor(
    private val listener: TicTacToeEventListener,
    private var board: Board,
    private val playerCounts: MutableMap<Symbol, Int> = mutableMapOf(Symbol.X to 0, Symbol.O to 0)
) {
    private var currentSymbolIsX = true
    private val currentSymbol get() = if (currentSymbolIsX) Symbol.X else Symbol.O

    companion object {
        private const val WINNING_ATTEMPTS = 3
    }

    constructor(listener: TicTacToeEventListener) : this(listener, Board())

    fun addSymbol(coordinate: Coordinate) {
        val currSym = currentSymbol
        currentSymbolIsX = !currentSymbolIsX

        fun turnsForCurrentPlayer() = playerCounts.getValue(currSym)

        try {
            board = board.addSymbol(currSym, coordinate)
            listener.onEvent(TicTacToeEvent.Information.SymbolPlaced(currSym, coordinate))

            val currentPlayerTurns = turnsForCurrentPlayer()
            playerCounts[currSym] = currentPlayerTurns + 1
        } catch (e: SpaceOccupiedOnBoardException) {
            listener.onEvent(
                TicTacToeEvent.Failure.SpaceWasAlreadyOccupied(
                    e.symbolOccupyingSpace,
                    coordinate
                )
            )
        }

        if (board.hasThreeInARow) {
            listener.onEvent(TicTacToeEvent.GameOver.Winner(currSym))
        }

        if (!board.hasBlanks) {
            listener.onEvent(TicTacToeEvent.GameOver.Tie)
        }

        if (turnsForCurrentPlayer() == WINNING_ATTEMPTS) {
            listener.onEvent(TicTacToeEvent.GameOver.MaximumTurnsReached(currSym))
        }
    }

    interface TicTacToeEventListener {
        fun onEvent(event: TicTacToeEvent)
    }
}
