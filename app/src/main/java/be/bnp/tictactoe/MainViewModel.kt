package be.bnp.tictactoe

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UiState

class MainViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UiState>(UiState())
    val uiState: LiveData<UiState> get() = _uiState

    fun handleClick(view: View) {
        val coordinates = view.tag.toString().split(",")
        println(coordinates)
    }
}
