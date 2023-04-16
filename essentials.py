import global_variable


class Piece(object):

    def __init__(self, name):
        self._id = 0
        self._name = name
        self.isAlive = True
        self.currentPosition = 0
        self.possiblePositions = []

    # return the current territory of this piece (0: White, 1: Black)
    def check_territory(self):
        if self.currentPosition // 10 <= 4:
            return 1
        else:
            return 0

    def check_valid_moves(self):
        # return a hash map of all possible moves
        pass

    def set_position(self, position):
        # return boolean
        pass

    def get_position(self):
        # return int
        return self.currentPosition


class Rock(Piece):
    # number of pieces
    rockCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def check_valid_moves(self):
        pass


class Advisor(Piece):
    # number of pieces
    advisorCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def check_valid_moves(self):
        pass


class Horse(Piece):
    # number of pieces
    horseCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def check_valid_moves(self):
        pass


class General(Piece):
    # number of pieces
    generalCounter = 2

    def __init__(self, name):
        Piece.__init__(self, name)
        self.isCheck = False
        self.isCheckMate = False

    # TODO
    def check_valid_moves(self):
        pass


class Elephant(Piece):
    # number of pieces
    elephantCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def check_valid_moves(self):
        pass


class Canon(Piece):
    # number of pieces
    canonCounter = 4

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def check_valid_moves(self):
        is_blocked = 0
        for i in range(10):
            if i == self.currentPosition % 10:
                continue
            if global_variable.board[self.currentPosition / 10][i]:
                is_blocked = is_blocked + 1
                continue
            if is_blocked == 2:
                self.possiblePositions.append([int(self.currentPosition / 10), i])
                break
            if is_blocked == 0:
                self.possiblePositions.append([int(self.currentPosition / 10), i])
        for i in range(10):
            if i == self.currentPosition // 10:
                continue
            if global_variable.board[self.currentPosition % 10][i]:
                is_blocked = is_blocked + 1
                continue
            if is_blocked == 2:
                self.possiblePositions.append([int(self.currentPosition % 10), i])
                break
            if is_blocked == 0:
                self.possiblePositions.append([int(self.currentPosition % 10), i])


class Pawn(Piece):
    # number of pieces
    pawnCounter = 10

    def __init__(self, name):
        Piece.__init__(self, name)

    # TODO
    def check_valid_moves(self):
        pass
