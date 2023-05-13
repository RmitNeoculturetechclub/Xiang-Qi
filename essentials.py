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

class Horse(Piece):
    # number of pieces
    horseCounter = 4

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

    def __init__(self, name: str, currentPosition: List[int], side: str) -> None:
        """Initialize a pawn

<<<<<<< HEAD
    # TODO
    def checkValidMoves(self):
        pass

class General(Piece):
    # the number of pieces
    generalCounter = 2
    # input: currrentPosition = [row,col]
    def __init__(self, name, currentPosition):
        Piece.__init__(self, name, currentPosition)
        self.isCheck = False
        self.isCheckMate = False
        self.currentPosition = currentPosition
    #TODO
    def checkValidMoves(self):
        validMoves = {} # hashmap
        row, col = self.currentPosition[0], self.currentPosition[1]

        # determine the range for the palace based on which side of the board the General is on
        if 0 <= row <= 2 and 3 <= col <= 5:  # General is on the top side of the board
            rowRange = range(0, 3)  # rows 0 to 2
            colRange = range(3, 6)  # columns 3 to 5
        elif 7 <= row <= 9 and 3 <= col <= 5:  # General is on the bottom side of the board
            rowRange = range(7, 10)  # rows 7 to 9
            colRange = range(3, 6)  # columns 3 to 5
        else:  # General is not in its palace, cannot move
            return validMoves # return {}

        # generate valid moves within the palace
        for r in rowRange:
            for c in colRange:
                if r == row and c == col:  # current position, skip
                    continue
                if abs(r - row) + abs(c - col) == 1:  # move one step vertically or horizontally
                    validMoves[(r,c)] = True # (r, c) -> valid next move position
        self.possiblePosition = validMoves
        return validMoves



class Elephant(Piece):
    # the number of pieces
    elephantCounter = 4
    # only two squares diagonally but cannot cross the river in the middle of the board
    # river: the central area of the board that divides the two sides of the playing field (0-4, 5-9)
    def __init__(self, name):
        Piece.__init__(self, name)
        self.currentPosition = currentPosition

    # TODO
    def checkValidMoves(self):
        validMoves = {} # hashmap
        row, col = self.currentPosition[0], self.currentPosition[1]

        # determine the range for the elephant based on which side of the board it is on
        if row <= 4:  # elephant is on the bottom side of the board
            rowRange = range(0, 5)  # rows 0 to 4
        else:  # elephant is on the top side of the board
            rowRange = range(5, 10)  # rows 5 to 9
        
        if col <= 2:  # elephant is on the left side of the board
            colRange = range(0, 3)  # it can only move to the right (columns 3-8)
        elif col >= 6:  # elephant is on the right side of the board
            colRange = range(6, 9)  # it can only move to the left (columns 0-5)
        else:  # elephant is in the middle, cannot move, because it would have to cross the river
            return validMoves  # return {}

        # generate valid moves within the elephant's range
        for r in rowRange:
            for c in colRange:
                if r == row and c == col:  # current position, skip
                    continue
                if abs(r - row) == 2 and abs(c - col) == 2:  # move two steps diagonally
                    # check if there are other pieces between the current position of the elephant and the destination position
                    # cannot cross the river by stepping on a piece.
                    if r < row and c < col and self.board[(row-1, col-1)] != None:
                        continue # trying to move up and to the left / two steps -> middle row-1, col-1
                    elif r < row and c > col and self.board[(row-1, col+1)] != None:
                        continue
                    elif r > row and c < col and self.board[(row+1, col-1)] != None:
                        continue
                    elif r > row and c > col and self.board[(row+1, col+1)] != None:
                        continue
                    validMoves[(r,c)] = True
        self.possiblePosition = validMoves
        return validMoves
    

        
class Advisor(Piece):
    # number of pieces
    advisorCounter = 4
    # move only one square move diagonally and confined to the palace
    def __init__(self, name):
        Piece.__init__(self, name)
        self.currentPosition = currentPosition

    # TODO
    def checkValidMoves(self):
        validMoves = {} # hashmap
        row, col = self.currentPosition[0], self.currentPosition[1]

        # determine the range for the palace based on which side of the board the advisor(guard) is on
        if 0 <= row <= 2 and 3 <= col <= 5:  # advisor is on the top side of the board
            rowRange = range(0, 3)  # rows 0 to 2
            colRange = range(3, 6)  # columns 3 to 5
        elif 7 <= row <= 9 and 3 <= col <= 5:  # advisor is on the bottom side of the board
            rowRange = range(7, 10)  # rows 7 to 9
            colRange = range(3, 6)  # columns 3 to 5
        else:  # advisor is not in its palace, cannot move
            return validMoves # return {}
        
        # generate valid moves within the advisor's range
        for r in rowRange:
            for c in colRange:
                if r == row and c == col:  # current position, skip
                    continue
                if abs(r - row) == 1 and abs(c - col) == 1:  # move one step diagonally
                    validMoves[(r,c)] = True
        self.possiblePosition = validMoves
        return validMoves
=======
        Args:
            name (str): Name of the piece
            currentPosition (List[int]): Current position of the piece
            side (str): Side of the piece ('w' for white, 'b' for black)
        """
        Piece.__init__(self, name)
        self.currentPosition = currentPosition
        self.side = side

    def checkValidMoves(self) -> List[List[int]]:
        """Show all the possible moves of the pawn

        Returns:
            List[List[int]]: List of possible moves of the pawn
        """
        x, y = self.currentPosition
        increment = 1 if self.side == 'w' else -1
        if (y >= 5 and self.side == 'w') or (y <= 4 and self.side == 'b'):
            allMoves = [[x - 1, y], [x, y + increment], [x + 1, y]]
            possibleMoves = []
            for move in allMoves:
                if 0 <=move[0] <= 8 and 0 <= move[1] <= 9:
                    possibleMoves.append(move)
            return possibleMoves
        else:
            return [[x, y + increment]]

