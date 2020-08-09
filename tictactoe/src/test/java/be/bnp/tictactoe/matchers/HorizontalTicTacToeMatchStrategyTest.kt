package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.horizontalTestCase
import be.bnp.tictactoe.noMatchTestCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HorizontalTicTacToeMatchStrategyTest : BehaviorSpec() {
    init {
        given("A horizontal match strategy") {
            val sut = HorizontalTicTacToeMatchStrategy
            `when`("given a list with no matches") {
                val hasMatch = sut.hasMatch(noMatchTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe false
                }
            }

            `when`("given a list with matches") {
                val hasMatch = sut.hasMatch(horizontalTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }
        }
    }
}
