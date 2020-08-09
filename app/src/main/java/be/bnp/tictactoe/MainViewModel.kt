package be.bnp.tictactoe

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import be.bnp.tictactoe.model.TicTacToeEvent

data class UiState(val board: List<Symbol> = emptyList())

class MainViewModel : ViewModel() {
    private val ticTacToe = TicTacToe(object : TicTacToe.TicTacToeEventListener {
        override fun onEvent(event: TicTacToeEvent) {
            val newState = when (event) {
                is TicTacToeEvent.Information.SymbolPlaced -> uiState.value?.copy(board = event.currentBoardState)
                is TicTacToeEvent.Failure.SpaceWasAlreadyOccupied -> TODO()
                is TicTacToeEvent.GameOver.Winner -> TODO()
                TicTacToeEvent.GameOver.Tie -> TODO()
                is TicTacToeEvent.GameOver.MaximumTurnsReached -> TODO()
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
}
