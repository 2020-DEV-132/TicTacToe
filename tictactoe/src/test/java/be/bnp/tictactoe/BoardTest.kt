package be.bnp.tictactoe

import be.bnp.tictactoe.exceptions.SpaceOccupiedOnBoardException
import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class BoardTest : FreeSpec() {
    init {
        "With an empty board" - {
            val emptyBoard = Board()
            "when adding a symbol on an empty space" - {
                val newBoard = emptyBoard.addSymbol(Symbol.X, Coordinate(0, 0))
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

            "`hasBlanks` property should be true" - {
                emptyBoard.hasBlanks shouldBe true
            }

            "`flatState` should return a flat state of the current state" - {
                emptyBoard.flatState shouldBe
                        listOf(
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank,
                            Symbol.Blank
                        )
            }
        }

        "Given a completely filled board" - {
            val filledBoard = Board(allXTestCase)
            "There shouldn't be any blank spaces" - {
                filledBoard.hasBlanks shouldBe false
            }
        }

        table(
            headers("description", "testCases", "has match"),
            row("no matches", noMatchTestCase, false),
            row("left top to right bottom diagonal match", leftTopToRightBottomTestCase, true),
            row("bottom left to top right diagonal match", bottomLeftToTopRightTestCase, true),
            row("horizontal match", horizontalTestCase, true),
            row("vertical match", verticalTestCase, true)
        ).forAll { description, testCase, expectedThreeInARow ->
            description - {
                Board(testCase).hasThreeInARow shouldBe expectedThreeInARow
            }
        }
    }
}
