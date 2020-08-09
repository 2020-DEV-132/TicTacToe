package be.bnp.tictactoe

import be.bnp.tictactoe.exceptions.SpaceOccupiedOnBoardException
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly

class BoardTest : FreeSpec() {
    init {
        "With an empty board" - {
            val sut = Board()
            "when adding a symbol on an empty space" - {
                val newBoard = sut.addSymbol(Symbol.X, Coordinate(0, 0))
                "it should have added 'X' on coordinate 0, 0" - {
                    newBoard.currentState shouldContainExactly
                            listOf(
                                listOf(
                                    Symbol.X,
                                    Symbol.Blank,
                                    Symbol.Blank
                                ),
                                listOf(
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank
                                ),
                                listOf(
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank
                                )
                            )
                }

                "when adding a symbol on the same space" - {
                    "it should throw an ${SpaceOccupiedOnBoardException::class.java.simpleName}" - {
                        shouldThrow<SpaceOccupiedOnBoardException> {
                            newBoard.addSymbol(Symbol.X, Coordinate(0, 0))
                        }
                    }
                }
            }
        }
    }
}
