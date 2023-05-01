from typing import List

class Piece(object):
    def __init__(self, name):
        self._id = 0
        self._name = name
        self.isAlive = True
        self.currentPosition = []
        self.possiblePositions = {}

    # return the current teritory of this piece (0: White, 1: Black)
    def checkTeritory(currentPosition):
        # return 0/1
        pass

    def checkValidMoves(self):
        # return a hash map of all possible moves
        pass
    def setPosition(self, position):
        # return boolean
        pass
    def getPosition(self):
        # return int
        pass

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

    def __init__(self, name, currentPosition, side):
        Piece.__init__(self, name)
        self.currentPosition = currentPosition
        self.side = side

    def checkValidMoves(self) -> List[List[int]]:
        """Show all the possible moves of the pawn

        Returns:
            List[List[int]]: List of possible moves of the pawn
        """
        x, y = self.currentPosition
        increment = -1 if self.side == 'w' else 1
        if (y >= 5 and self.side == 'b') or (y <= 4 and self.side == 'w'):
            allMoves = [[x - 1, y], [x, y + increment], [x + 1, y]]
            possibleMoves = []
            for move in allMoves:
                if 0 <=move[0] <= 8 and 0 <= move[1] <= 9:
                    possibleMoves.append(move)
            return possibleMoves
        else:
            return [[x, y + increment]]
