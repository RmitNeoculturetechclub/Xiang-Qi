class GameState(object):
    def __init__(self):
        self.playerTurn = 0
        self.isOccupied = {}

    # start game
    def start_game(self):
        # return boolean
        pass

    # create pieces
    def generate_pieces(self):
        pass

    # update black/white turn
    def update_player_turn(self):
        # return boolean
        pass

    # check if a position is occupied
    def is_occupied(self, position):
        pass

    # exit game
    def exit_game(self):
        pass
