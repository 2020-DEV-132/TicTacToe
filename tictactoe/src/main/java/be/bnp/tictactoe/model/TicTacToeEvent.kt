package be.bnp.tictactoe.model

sealed class TicTacToeEvent {
    sealed class Information : TicTacToeEvent() {
        data class SymbolPlaced(val symbol: Symbol, val coordinate: Coordinate) : Information()
    }

    sealed class Failure : TicTacToeEvent() {
        data class SpaceWasAlreadyOccupied(
            val occupyingSymbol: Symbol,
            val coordinate: Coordinate
        ) : Failure()
    }

    sealed class GameOver : TicTacToeEvent() {
        data class Winner(val winner: Symbol) : GameOver()
        object Tie : GameOver()
    }
}
