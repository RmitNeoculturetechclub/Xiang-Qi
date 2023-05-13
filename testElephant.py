"""
NOTES:
- This file is used to write unit test cases for the pawn piece
- If you want to create more test cases, create a new function and use the assertEqual() function to test the checkValidMoves() function of the piece
- The function should have the name test_<name of the test case>
- To run this file, type "python -m unittest testPawn.py" in the terminal
"""

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

""" Board setup (reversed row order so white is on top and black is on bottom)
A   0 _ . . . . . . . . .
B   1 _ . . . . . . . . .
C   2 _ . . . . . . . . .
D   3 _ . . . . . . . . .
E   4 _ . . . . . . . . .
F   5 _ . . . . . . . . .
G   6 _ . . . . . . . . .
H   7 _ . . . . . . . . .
I   8 _ . . . . . . . . .
J   9 _ . . . . . . . . .
        | | | | | | | | |
        0 1 2 3 4 5 6 7 8
"""

class TestPawn(unittest.TestCase):
    def test_black04(self):
        """1st case: black pawn at position (0, 4)"""
        currentPos = [0,4] 
        p = Pawn('p',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[1,4],[0,3]]), sorted(moves))

    def test_black05(self):
        """2nd case: black pawn at position (0, 5)"""
        currentPos = [0,5] 
        p = Pawn('p',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,4]]), sorted(moves))

    def test_black14(self):
        """3rd case: black pawn at position (1, 4)"""
        currentPos = [1,4] 
        p = Pawn('p',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,4],[2,4],[1,3]]), sorted(moves))

    def test_black10(self):
        """4th case: black pawn at position (1, 0)"""
        currentPos = [1,0] 
        p = Pawn('p',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,0],[2,0]]), sorted(moves))

    def test_black00(self):
        """5th case: black pawn at position (0, 0)"""
        currentPos = [0,0] 
        p = Pawn('p',currentPos,'b')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[1,0]]), sorted(moves))

    def test_white04(self):
        """6th case: white pawn at position (0, 4)"""
        currentPos = [0,4] 
        p = Pawn('P',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,5]]), sorted(moves))

    def test_white05(self):
        """7th case: white pawn at position (0, 5)"""
        currentPos = [0,5] 
        p = Pawn('P',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,6],[1,5]]), sorted(moves))

    def test_white15(self):
        """8th case: white pawn at position (1, 4)"""
        currentPos = [1,5] 
        p = Pawn('P',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,5],[2,5],[1,6]]), sorted(moves))

    def test_white19(self):
        """9th case: white pawn at position (1, 0)"""
        currentPos = [1,9] 
        p = Pawn('P',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[0,9],[2,9]]), sorted(moves))

    def test_white09(self):
        """10th case: white pawn at position (0, 0)"""
        currentPos = [0,9] 
        p = Pawn('P',currentPos,'w')
        moves = p.checkValidMoves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual(sorted([[1,9]]), sorted(moves))

if __name__=='__main__':
	unittest.main()
