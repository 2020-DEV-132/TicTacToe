package be.bnp.tictactoe.matchers

import be.bnp.tictactoe.bottomLeftToTopRightTestCase
import be.bnp.tictactoe.leftTopToRightBottomTestCase
import be.bnp.tictactoe.noMatchTestCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DiagonalTicTacToeMatchStrategyTest : BehaviorSpec() {
    init {
        given("A diagonal match strategy") {
            val sut = DiagonalTicTacToeMatchStrategy(HorizontalTicTacToeMatchStrategy)
            `when`("given a list with no matches") {
                val hasMatch = sut.hasMatch(noMatchTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe false
                }
            }

            `when`("given a list with matches (left top to right bottom)") {
                val hasMatch = sut.hasMatch(leftTopToRightBottomTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }

            `when`("given a list with matches (bottom left to top right)") {
                val hasMatch = sut.hasMatch(bottomLeftToTopRightTestCase)
                then("it should have not found a match") {
                    hasMatch shouldBe true
                }
            }
        }
    }
}
