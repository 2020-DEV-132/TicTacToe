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
            listener.onEvent(
                TicTacToeEvent.Information.SymbolPlaced(
                    currSym,
                    coordinate,
                    board.currentState.flatten()
                )
            )

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
            listener.onEvent(TicTacToeEvent.GameOver.Winner(currSym, board.currentState.flatten()))
            return
        }

        if (!board.hasBlanks) {
            listener.onEvent(TicTacToeEvent.GameOver.Tie)
            return
        }

        if (turnsForCurrentPlayer() == WINNING_ATTEMPTS) {
            listener.onEvent(
                TicTacToeEvent.GameOver.MaximumTurnsReached(
                    currSym,
                    board.currentState.flatten()
                )
            )
            return
        }
    }

    fun startANewGame() {
        board = Board()
        currentSymbolIsX = true
        playerCounts[Symbol.X] = 0
        playerCounts[Symbol.O] = 0

        listener.onEvent(
            TicTacToeEvent.Information.NewGame(
                board.currentState.flatten(),
                currentSymbol,
                playerCounts.getValue(Symbol.X),
                playerCounts.getValue(Symbol.O)
            )
        )
    }

    interface TicTacToeEventListener {
        fun onEvent(event: TicTacToeEvent)
    }
}
