from typing import List

class Piece(object):
    def __init__(self, name):
        self._id = 0
        self._name = name
        self.isAlive = True
        self.currentPosition = []
        self.possiblePositions = []

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

class General(Piece):
    # the number of pieces (total for both sides)
    generalCounter = 2

    def __init__(self, name: str, currentPosition: List[int], side: str) -> None:
        self.name = name
        self.currentPosition = currentPosition
        self.side = side

    #TODO
    def checkValidMoves(self):
        validMoves = [] # array
        y, x = self.currentPosition 
        # X=ROW, Y=COL
        # Palace = 0 <= x <= 2 and 3 <= y <= 5 // elif 7 <= x <= 9 and 3 <= y <= 5
        if 0 <= x <= 2 and 3 <= y <= 5:  # General is on the top side of the board
            rowRange = range(0, 3)  # rows 0 to 2
            colRange = range(3, 6)  # columns 3 to 5
        elif 7 <= x <= 9 and 3 <= y <= 5:  # General is on the bottom side of the board
            rowRange = range(7, 10)  # rows 7 to 9
            colRange = range(3, 6)  # columns 3 to 5
        else:  # General is not in its palace, cannot move
            return validMoves # return []

        # generate valid moves (one step vertically or horizontallywithin the palace)
        for r in rowRange:
            for c in colRange:
                print("r, c", r, c)
                if r == x and c == y:  # current position, skip
                    continue
                if abs(r - x) + abs(c - y) == 1:  
                    validMoves.append([c, r]) # [r, c] -> valid next move position
        self.possiblePosition = validMoves
        return validMoves

class Elephant(Piece):
    # the number of pieces (total for both sides)
    elephantCounter = 4
    # only two squares diagonally + cannot cross the river in the middle of the board
    # river: the central area of the board that divides the two sides of the playing field (0-4, 5-9)
    def __init__(self, name: str, currentPosition: List[int], side: str) -> None:
        self.name = name
        self.currentPosition = currentPosition
        self.side = side

    # TODO
    def checkValidMoves(self):
        validMoves = [] # array
        y, x = self.currentPosition 
        # X=ROW, Y=COL
        
        # determine the range for the river
        if x <= 4:  # elephant is on the bottom side of the board
            rowRange = range(0, 5)  # rows 0 to 4
        else:  # elephant is on the top side of the board
            rowRange = range(5, 10)  # rows 5 to 9
        
        # generate valid moves within the elephant's range
        for r in rowRange:
            for c in range(9):
                print("r, c", r, c)
                if r == x and c == y:  # current position, skip
                    continue
                if abs(r - x) == 2 and abs(c - y) == 2:  # move two steps diagonally
                    """# check if there are other pieces between the current position of the elephant and the destination position
                    # cannot cross the river by stepping on a piece.
                    if r < x and c < y and self.board[(x-1, y-1)] != None:
                        continue # trying to move up and to the left / two steps -> middle row-1, col-1
                    elif r < x and c > y and self.board[(x-1, y+1)] != None:
                        continue
                    elif r > x and c < y and self.board[(x+1, y-1)] != None:
                        continue
                    elif r > x and c > y and self.board[(x+1, y+1)] != None:
                        continue"""
                    validMoves.append([c, r])
        self.possiblePosition = validMoves
        return validMoves
    
class Advisor(Piece):
    # number of pieces (total for both sides)
    advisorCounter = 4
    # move only one square move diagonally and confined to the palace
    def __init__(self, name: str, currentPosition: List[int], side: str) -> None:
        self.name = name
        self.currentPosition = currentPosition
        self.side = side

    # TODO
    def checkValidMoves(self):
        validMoves = [] # array
        y, x = self.currentPosition 
        # X=ROW, Y=COL

        # determine the range for the palace based on which side of the board the advisor(guard) is on
        if 0 <= x <= 2 and 3 <= y <= 5:  # advisor is on the top side of the board
            rowRange = range(0, 3)  # rows 0 to 2
            colRange = range(3, 6)  # columns 3 to 5
        elif 7 <= x <= 9 and 3 <= y <= 5:  # advisor is on the bottom side of the board
            rowRange = range(7, 10)  # rows 7 to 9
            colRange = range(3, 6)  # columns 3 to 5
        else:  # advisor is not in its palace, cannot move
            return validMoves # return []
        
        # generate valid moves within the advisor's range
        for r in rowRange:
            for c in colRange:
                if r == x and c == y:  # current position, skip
                    continue
                if abs(r - x) == 1 and abs(c - y) == 1:  # move one step diagonally
                    validMoves.append([c, r])
        self.possiblePosition = validMoves
        return validMoves

