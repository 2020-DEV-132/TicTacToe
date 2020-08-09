package be.bnp.tictactoe.exceptions

import be.bnp.tictactoe.model.Symbol

class SpaceOccupiedOnBoardException(val symbolOccupyingSpace: Symbol) :
    Exception("This place on the board is already occupied")
