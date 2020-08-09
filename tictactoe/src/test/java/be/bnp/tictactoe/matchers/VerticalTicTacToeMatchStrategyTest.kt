package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.noMatchTestCase
import be.bnp.tictactoe.verticalTestCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class VerticalTicTacToeMatchStrategyTest : BehaviorSpec() {
    init {
        given("A vertical match strategy") {
            val sut = VerticalTicTacToeMatchStrategy(HorizontalTicTacToeMatchStrategy)
            `when`("given a list with no matches") {
                val hasMatch = sut.hasMatch(noMatchTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe false
                }
            }

            `when`("given a list with matches") {
                val hasMatch = sut.hasMatch(verticalTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }
        }
    }
}
