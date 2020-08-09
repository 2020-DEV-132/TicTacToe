package be.bnp.tictactoe

import be.bnp.tictactoe.model.Symbol
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly

class BoardFactoryTest : BehaviorSpec() {
    init {
        given("A board factory") {
            val sut = Board.Factory
            `when`("constructing the initial board") {
                then("it should return a 3 by 3 board with all ${Symbol.Blank::class.java.simpleName}s") {
                    sut.constructBoardState() shouldContainExactly blankBoard
                }
            }
        }
    }
}
