package be.bnp.tictactoe

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import be.bnp.tictactoe.databinding.ActivityMainBinding
import be.bnp.tictactoe.model.Symbol

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val row0Column0 get() = binding.row0Column0
    private val row0Column1 get() = binding.row0Column1
    private val row0Column2 get() = binding.row0Column2
    private val row1Column0 get() = binding.row1Column0
    private val row1Column1 get() = binding.row1Column1
    private val row1Column2 get() = binding.row1Column2
    private val row2Column0 get() = binding.row2Column0
    private val row2Column1 get() = binding.row2Column1
    private val row2Column2 get() = binding.row2Column2

    private val buttons
        get() = listOf(
            row0Column0, row0Column1, row0Column2,
            row1Column0, row1Column1, row1Column2,
            row2Column0, row2Column1, row2Column2
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        buttons.onEach {
            it.setOnClickListener(mainViewModel::handleClick)
        }

        mainViewModel.uiState.observe({ lifecycle }) {
            it.board.forEachIndexed { index, symbol ->
                fun isClickable() = when (symbol) {
                    Symbol.X,
                    Symbol.O -> false
                    Symbol.Blank -> true
                }

                fun textForButton() = when (symbol) {
                    Symbol.X -> getString(R.string.symbol_x)
                    Symbol.O -> getString(R.string.symbol_o)
                    Symbol.Blank -> ""
                }

                with(buttons[index]) {
                    isEnabled = isClickable()
                    text = textForButton()
                }
            }

            if (it.isGameOver) {
                AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setMessage(it.gameOverText!!)
                    .setPositiveButton(R.string.replay) { _, _ ->
                        mainViewModel.onNewGame()
                    }
                    .show()
            }
        }

        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
