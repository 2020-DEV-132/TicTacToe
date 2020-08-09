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
                                Coordinate(0, 0),
                                listOf(
                                    Symbol.X,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank
                                )
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
                            TicTacToeEvent.GameOver.Winner(Symbol.X,
                                listOf(
                                    Symbol.X,
                                    Symbol.X,
                                    Symbol.X,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank,
                                    Symbol.Blank
                                )
                            )
                        )
                    }
                }
            }
        }

        "A game of TicTacToe with one blank space left" - {
            val sut = TicTacToe(
                listener, Board(
                    listOf(
                        listOf(Symbol.Blank, Symbol.X, Symbol.O),
                        listOf(Symbol.O,     Symbol.O, Symbol.X),
                        listOf(Symbol.X,     Symbol.O, Symbol.X)
                    )
                )
            )

            "when adding the last non winning move" - {
                sut.addSymbol(Coordinate(0, 0))
                "it should notify us of a tie" - {
                    verify {
                        listener.onEvent(TicTacToeEvent.GameOver.Tie)
                    }
                }
            }
        }

        "A game of TicTacToe" - {
            val sut = TicTacToe(listener)
            "when a new game is started" - {
                sut.startANewGame()
                "then it should notify the listener" - {
                    verify {
                        listener.onEvent(
                            TicTacToeEvent.Information.NewGame(
                                blankBoard.flatten(),
                                Symbol.X
                            )
                        )
                    }
                }
            }
        }
    }
}
