package be.bnp.tictactoe

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import be.bnp.tictactoe.model.TicTacToeEvent

data class UiState(
    val board: List<Symbol> = emptyList(),
    val gameOverText: String? = null
) {
    val isGameOver: Boolean get() = gameOverText?.isNotBlank() ?: false
}

class MainViewModel : ViewModel() {
    private val ticTacToe = TicTacToe(object : TicTacToe.TicTacToeEventListener {
        override fun onEvent(event: TicTacToeEvent) {
            val newState = when (event) {
                is TicTacToeEvent.Information.SymbolPlaced -> uiState.value?.copy(board = event.currentBoardState)
                is TicTacToeEvent.Failure.SpaceWasAlreadyOccupied -> uiState.value
                is TicTacToeEvent.GameOver -> {
                    val gameOverText = when (event) {
                        is TicTacToeEvent.GameOver.Winner ->
                            "We have a winner! ${event.winner}"
                        TicTacToeEvent.GameOver.Tie ->
                            "We have a tie! Nobody won. Better luck next time!"
                    }
                    uiState.value?.copy(gameOverText = gameOverText)
                }
                is TicTacToeEvent.Information.NewGame -> uiState.value?.copy(
                    board = event.currentBoardState,
                    gameOverText = null
                )
            }
            _uiState.value = newState
        }
    })

    private val _uiState = MutableLiveData<UiState>(UiState())
    val uiState: LiveData<UiState> get() = _uiState

    fun handleClick(view: View) {
        val coordinates = view.tag.toString().split(",").map { it.toInt() }
        ticTacToe.addSymbol(Coordinate(coordinates[0], coordinates[1]))
    }

    fun onNewGame() = ticTacToe.startANewGame()
}
