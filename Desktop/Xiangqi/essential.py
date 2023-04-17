class General(Piece):
    # the number of pieces
    generalCounter = 2
    # input: currrentPosition = [row,col]
    def __init__(self, name, currentPosition):
        Piece.__init__(self, name, currentPosition)
        self.isCheck = False
        self.isCheckMate = False
        self.currentPosition = currentPosition

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