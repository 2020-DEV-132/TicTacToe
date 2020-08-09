package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DiagonalTicTacToeMatchStrategyTest : BehaviorSpec() {
    init {
        given("A diagonal match strategy") {
            val sut = DiagonalTicTacToeMatchStrategy(DefaultTicTacToeMatchStrategy)
            `when`("given a list with no matches") {
                val hasMatch = sut.hasMatch(
                    listOf(
                        listOf(Symbol.X,     Symbol.O,     Symbol.X),
                        listOf(Symbol.X,     Symbol.X,     Symbol.O),
                        listOf(Symbol.Blank, Symbol.Blank, Symbol.Blank)
                    )
                )
                then("it should have not found a match") {
                    hasMatch shouldBe false
                }
            }

            `when`("given a list with matches (left top to right bottom)") {
                val hasMatch = sut.hasMatch(
                    listOf(
                        listOf(Symbol.X,     Symbol.O,     Symbol.X),
                        listOf(Symbol.Blank, Symbol.X,     Symbol.O),
                        listOf(Symbol.Blank, Symbol.Blank, Symbol.X)
                    )
                )
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }

            `when`("given a list with matches (bottom left to top right)") {
                val hasMatch = sut.hasMatch(
                    listOf(
                        listOf(Symbol.Blank, Symbol.O,     Symbol.X),
                        listOf(Symbol.Blank, Symbol.X,     Symbol.O),
                        listOf(Symbol.X,     Symbol.Blank, Symbol.X)
                    )
                )
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }
        }
    }
}
