"""Xiang Qi Chess Pieces Specifically Module"""

from __future__ import annotations
from base_pieces import Piece


class Rock(Piece):
    # number of pieces
    rockCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def checkValidMoves(self):
        pass


class Advisor(Piece):
    # number of pieces
    advisorCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def checkValidMoves(self):
        pass


class Horse(Piece):
    # number of pieces
    horseCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def checkValidMoves(self):
        pass


class General(Piece):
    # number of pieces
    generalCounter = 2

    def __init__(self, name):
        Piece.__init__(self, name)
        self.isCheck = False
        self.isCheckMate = False

    # TODO
    def checkValidMoves(self):
        pass


class Elephant(Piece):
    # number of pieces
    elephantCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def checkValidMoves(self):
        pass


class Canon(Piece):
    # number of pieces
    canonCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def checkValidMoves(self):
        pass


class Pawn(Piece):
    # number of pieces
    pawnCounter = 10

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def checkValidMoves(self):
        pass