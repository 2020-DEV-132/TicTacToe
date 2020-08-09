package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.model.Symbol
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HorizontalTicTacToeMatchStrategyTest : BehaviorSpec() {
    init {
        given("A horizontal match strategy") {
            val sut = HorizontalTicTacToeMatchStrategy()
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

            `when`("given a list with matches") {
                val hasMatch = sut.hasMatch(
                    listOf(
                        listOf(Symbol.X,     Symbol.O,     Symbol.X),
                        listOf(Symbol.X,     Symbol.X,     Symbol.X),
                        listOf(Symbol.Blank, Symbol.Blank, Symbol.Blank)
                    )
                )
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }
        }
    }
}
