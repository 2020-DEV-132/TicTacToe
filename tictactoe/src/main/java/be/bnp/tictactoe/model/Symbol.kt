package be.bnp.tictactoe.model

sealed class Symbol {
    object X : Symbol() {
        override fun toString(): String = "X"
    }

    object O : Symbol() {
        override fun toString(): String = "O"
    }

    object Blank : Symbol() {
        override fun toString() = "."
    }
}
