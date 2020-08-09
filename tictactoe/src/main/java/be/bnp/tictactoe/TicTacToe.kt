package be.bnp.tictactoe

import be.bnp.tictactoe.exceptions.SpaceOccupiedOnBoardException
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import be.bnp.tictactoe.model.TicTacToeEvent

class TicTacToe internal constructor(
    private val listener: TicTacToeEventListener,
    private var board: Board
) {
    private var currentSymbolIsX = true
    private val currentSymbol get() = if (currentSymbolIsX) Symbol.X else Symbol.O

    constructor(listener: TicTacToeEventListener) : this(listener, Board())

    fun addSymbol(coordinate: Coordinate) {
        val currSym = currentSymbol
        currentSymbolIsX = !currentSymbolIsX

        try {
            board = board.addSymbol(currSym, coordinate)
            listener.onEvent(TicTacToeEvent.Information.SymbolPlaced(currSym, coordinate))
        } catch (e: SpaceOccupiedOnBoardException) {
            listener.onEvent(
                TicTacToeEvent.Failure.SpaceWasAlreadyOccupied(
                    e.symbolOccupyingSpace,
                    coordinate
                )
            )
        }
    }

    interface TicTacToeEventListener {
        fun onEvent(event: TicTacToeEvent)
    }
}
