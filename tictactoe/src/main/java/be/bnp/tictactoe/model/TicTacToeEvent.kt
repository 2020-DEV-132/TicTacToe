package be.bnp.tictactoe.model

sealed class TicTacToeEvent {
    sealed class Information : TicTacToeEvent() {
        data class SymbolPlaced(
            val symbol: Symbol,
            val coordinate: Coordinate,
            val currentBoardState: List<Symbol>
        ) : Information()

        data class NewGame(
            val currentBoardState: List<Symbol>,
            val currentSymbol: Symbol,
            val xTurns: Int,
            val yTurns: Int
        ) :
            TicTacToeEvent()
    }

    sealed class Failure : TicTacToeEvent() {
        data class SpaceWasAlreadyOccupied(
            val occupyingSymbol: Symbol,
            val coordinate: Coordinate
        ) : Failure()
    }

    sealed class GameOver : TicTacToeEvent() {
        data class Winner(val winner: Symbol, val currentBoardState: List<Symbol>) : GameOver()
        object Tie : GameOver()
        data class MaximumTurnsReached(val winner: Symbol, val currentBoardState: List<Symbol>) :
            GameOver()
    }
}
