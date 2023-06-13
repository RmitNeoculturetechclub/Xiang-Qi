import essentials


class gameState(object):
    def __init__(self):
        self.playerTurn = 0
        self.isOccupied = [
            [True, True, True, True, True, True, True, True, True],
            [],
            [False, True, False, False, False, False, False, True, False],
            [True, False, True, False, True, False, True, False, True],
            [],
            [],
            [True, True, True, True, True, True, True, True, True],
            [],
            [False, True, False, False, False, False, False, True, False],
            [True, False, True, False, True, False, True, False, True],
        ]

    # start game
    def startGame(self):
        # return boolean
        pass

    # create pieces
    def generatePieces():
        pass

    # update black/white turn
    def updatePlayerTurn(self):
        # return boolean
        pass

    # check if a position is occupied
    def isOccuppied(self, position):
        # return boolean
        pass

    # exit game
    def exitGame():
        pass


"""
NOTES:
- This main function is used to test the checkValidMoves() function of the piece
- If you want to test the checkValidMoves() function of a different piece, change the piece type
- If you want to test the checkValidMoves() function of a different position, change the currentPos
- To run this file, type "python game.py" in the terminal
"""


def main():
    board = []
    with open("board.txt") as f:
        for line in f.readlines():
            board.append(line.strip().split(' '))

    # change this to test different positions
    currentPos = [1, 5]

    # change this to test different pieces
    p1 = essentials.Pawn('s1', currentPos, 'w')

    moves = p1.checkValidMoves()

    for row in range(9, -1, -1):
        for col in range(9):
            if [col, row] == currentPos:
                print('o', end="  ")
            elif [col, row] in moves:
                print('x', end="  ")
            else:
                print(board[row][col], end="  ")
        print()


# call main
if __name__ == '__main__':
    main()
