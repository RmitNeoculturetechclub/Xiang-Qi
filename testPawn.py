from typing import List
import unittest

from essentials import Pawn

def read_board() -> List[List[str]]:
    """Read the board setup in the board.txt for testing

    Returns:
        List[List[str]]: return the board setup
    """
    board = []
    with open("board.txt") as f:
        for line in f.readlines():
            board.append(line.strip().split(' '))
    return board

def print_board(board: List[List[str]], currentPos: List[int], moves: int, side: str):
    """Print the board, the possible moves and the current possition of the pieces 

    Args:
        board (List[List[str]]): the board setup
        currentPos (List[int]): current position of the piece
        moves (int): list of posssible moves of the piece
        side (str): name of the piece (lowercase for White, uppercase for Black)
    """
    for r in range(10):
        for c in range(9):
            if [c,r] == currentPos:
                print(side,end = "  ")
            elif [c,r] in moves:
                print('x',end = "  ")
            else:
                print(board[r][c],end = "  ")
        print()

"""
0 _ . . . . . . . . .
1 _ . . . . . . . . .
2 _ . . . . . . . . .
3 _ . . . . . . . . .
4 _ . . . . . . . . .
5 _ . . . . . . . . .
6 _ . . . . . . . . .
7 _ . . . . . . . . .
8 _ . . . . . . . . .
9 _ . . . . . . . . .
    | | | | | | | | |
    0 1 2 3 4 5 6 7 8
"""

class TestPawn(unittest.TestCase):
    def test_white04(self):
        """1st case: white pawn at position (0, 4)"""
        currentPos = [0,4] 
        p = Pawn('p',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[1,4],[0,3]].sort(), moves.sort())

    def test_white05(self):
        """2nd case: white pawn at position (0, 5)"""
        currentPos = [0,5] 
        p = Pawn('p',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,4]].sort(), moves.sort())

    def test_white14(self):
        """3rd case: white pawn at position (1, 4)"""
        currentPos = [1,4] 
        p = Pawn('p',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,4],[2,4],[1,3]].sort(), moves.sort())

    def test_white10(self):
        """4th case: white pawn at position (1, 0)"""
        currentPos = [1,0] 
        p = Pawn('p',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,0],[2,0]].sort(), moves.sort())

    def test_white00(self):
        """5th case: white pawn at position (0, 0)"""
        currentPos = [0,0] 
        p = Pawn('p',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[1,0]].sort(), moves.sort())

    def test_black04(self):
        """6th case: white pawn at position (0, 4)"""
        currentPos = [0,4] 
        p = Pawn('P',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,5]].sort(), moves.sort())

    def test_black05(self):
        """7th case: white pawn at position (0, 5)"""
        currentPos = [0,5] 
        p = Pawn('P',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,6],[1,5]].sort(), moves.sort())

    def test_black15(self):
        """8th case: white pawn at position (1, 4)"""
        currentPos = [1,5] 
        p = Pawn('P',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,5],[2,5],[1,6]].sort(), moves.sort())

    def test_black19(self):
        """9th case: white pawn at position (1, 0)"""
        currentPos = [1,9] 
        p = Pawn('P',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[0,9],[2,9]].sort(), moves.sort())

    def test_black09(self):
        """10th case: white pawn at position (0, 0)"""
        currentPos = [0,9] 
        p = Pawn('P',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([[1,9]].sort(), moves.sort())

if __name__=='__main__':
	unittest.main()
