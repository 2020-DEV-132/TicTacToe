package be.bnp.tictactoe

import be.bnp.tictactoe.model.Coordinate
import be.bnp.tictactoe.model.Symbol
import be.bnp.tictactoe.model.TicTacToeEvent
import io.kotest.core.spec.style.FreeSpec
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify

class TicTacToeTest : FreeSpec() {

    @MockK(relaxed = true)
    private lateinit var listener: TicTacToe.TicTacToeEventListener

    init {
        MockKAnnotations.init(this)
        "A new game of TicTacToe" - {
            val sut = TicTacToe(listener)
            "adding a symbol on coordinate 0, 0" - {
                sut.addSymbol(Coordinate(0, 0))
                "the listener should have notified us" - {
                    verify {
                        listener.onEvent(
                            TicTacToeEvent.Information.SymbolPlaced(
                                Symbol.X,
                                Coordinate(0, 0)
                            )
                        )
                    }
                }

                "when trying to add a symbol on the same coordinate" - {
                    sut.addSymbol(Coordinate(0, 0))
                    "it should notify us from a failure" - {
                        verify {
                            listener.onEvent(
                                TicTacToeEvent.Failure.SpaceWasAlreadyOccupied(
                                    Symbol.X,
                                    Coordinate(0, 0)
                                )
                            )
                        }
                    }
                }
            }
        }

        "A game of TicTacToe with X in the lead" - {
            val sut = TicTacToe(
                listener, Board(
                    listOf(
                        listOf(
                            Symbol.X,
                            Symbol.X,
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
                )
            )

            "when 'X' makes a winning move" - {
                sut.addSymbol(Coordinate(0, 2))
                "then the listener should notify us that we have a winner" - {
                    verify {
                        listener.onEvent(
                            TicTacToeEvent.GameOver.Winner(Symbol.X)
                        )
                    }
                }
            }
        }
    }
}
