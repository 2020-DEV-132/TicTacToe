package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol

interface TicTacToeMatchStrategy {
    fun hasMatch(list: List<List<Symbol>>): Boolean
}
