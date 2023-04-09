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
        #return boolean
        pass

    # exit game
    def exitGame():
        pass

def main():
    # prompt simple Terminal GUI for starting game, exitting game and so on

    # Call relative classes and functions based on the command passed in
    
    # return boolean (false if there is error in a function)
    pass

# call main
if __name__ == '__main__':
    main()

