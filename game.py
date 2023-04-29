import essentials

board = [
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
    ['.','.','.','.','.','.','.','.','.',],
]

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
        #return boolean
        pass

    # exit game
    def exitGame():
        pass

def main():
    # prompt simple Terminal GUI for starting game, exitting game and so on

    # Call relative classes and functions based on the command passed in
    
    # return boolean (false if there is error in a function)
    currentPos = [0,4] 
    p1 = essentials.Pawn('s1',currentPos,'w')
    moves = p1.checkValidMoves()

    for r in range(10):
        for c in range(9):
            if [c,r] == currentPos:
                print('o',end = "  ")
            elif [c,r] in moves:
                print('x',end = "  ")
            else:
                print(board[r][c],end = "  ")
        print()

# call main
if __name__ == '__main__':
    main()

