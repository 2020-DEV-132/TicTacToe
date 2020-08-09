package be.bnp.tictactoe.matchers

class HorizontalTicTacToeMatchStrategy(delegatingMatchStrategy: TicTacToeMatchStrategy) :
    TicTacToeMatchStrategy by delegatingMatchStrategy
