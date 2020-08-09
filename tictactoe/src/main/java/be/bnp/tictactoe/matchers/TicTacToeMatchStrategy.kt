package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol

interface TicTacToeMatchStrategy {
    fun hasMatch(list: List<List<Symbol>>): Boolean
}

class HorizontalTicTacToeMatchStrategy : TicTacToeMatchStrategy {
    override fun hasMatch(list: List<List<Symbol>>): Boolean {
        var foundHorizontalMatch = false
        list.forEach { symbolList ->
            if (foundHorizontalMatch) {
                return@forEach
            }

            val isAllX = symbolList.all { symbol -> symbol is Symbol.X }
            if (isAllX) {
                foundHorizontalMatch = true
            }
            val isAllO = symbolList.all { symbol -> symbol is Symbol.O }
            if (isAllO) {
                foundHorizontalMatch = true
            }
        }
        return foundHorizontalMatch
    }
}

