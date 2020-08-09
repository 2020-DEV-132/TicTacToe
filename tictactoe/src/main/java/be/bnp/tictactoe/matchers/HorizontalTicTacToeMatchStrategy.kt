package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol

object HorizontalTicTacToeMatchStrategy : TicTacToeMatchStrategy {
    override fun hasMatch(list: List<List<Symbol>>): Boolean {
        var foundMatch = false
        list.forEach { symbolList ->
            if (foundMatch) {
                return@forEach
            }

            val isAllX = symbolList.all { symbol -> symbol is Symbol.X }
            if (isAllX) {
                foundMatch = true
            }
            val isAllO = symbolList.all { symbol -> symbol is Symbol.O }
            if (isAllO) {
                foundMatch = true
            }
        }
        return foundMatch
    }
}
