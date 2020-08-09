package be.bnp.tictactoe

import be.bnp.tictactoe.model.Symbol

// Re-usable test cases
val noMatchTestCase = listOf(
    listOf(Symbol.X,     Symbol.O,     Symbol.X),
    listOf(Symbol.X,     Symbol.X,     Symbol.O),
    listOf(Symbol.Blank, Symbol.Blank, Symbol.Blank)
)

val allXTestCase = listOf(
    listOf(Symbol.X, Symbol.X, Symbol.X),
    listOf(Symbol.X, Symbol.X, Symbol.X),
    listOf(Symbol.X, Symbol.X, Symbol.X)
)

// Diagonal
val leftTopToRightBottomTestCase = listOf(
    listOf(Symbol.X,     Symbol.O,     Symbol.X),
    listOf(Symbol.Blank, Symbol.X,     Symbol.O),
    listOf(Symbol.Blank, Symbol.Blank, Symbol.X)
)

val bottomLeftToTopRightTestCase = listOf(
    listOf(Symbol.Blank, Symbol.O,     Symbol.X),
    listOf(Symbol.Blank, Symbol.X,     Symbol.O),
    listOf(Symbol.X,     Symbol.Blank, Symbol.X)
)

// Horizontal
val horizontalTestCase = listOf(
    listOf(Symbol.X,     Symbol.O,     Symbol.X),
    listOf(Symbol.X,     Symbol.X,     Symbol.X),
    listOf(Symbol.Blank, Symbol.Blank, Symbol.Blank)
)

// Vertical
val verticalTestCase = listOf(
    listOf(Symbol.O,     Symbol.O,     Symbol.X),
    listOf(Symbol.X,     Symbol.X,     Symbol.X),
    listOf(Symbol.Blank, Symbol.Blank, Symbol.X)
)
