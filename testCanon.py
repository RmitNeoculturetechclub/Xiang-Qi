"""
NOTES:
- This file is used to write unit test cases for the Canon piece
- If you want to create more test cases, create a new function and use the assertEqual() function to test the check_valid_moves() function of the piece
- The function should have the name test_<name of the test case>
- To run this file, type "python -m unittest testCanon.py" in the terminal
"""


from typing import List
import unittest

from essentials import Canon


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
            if [c, r] == currentPos:
                print(side, end="  ")
            elif [c, r] in moves:
                print('x', end="  ")
            else:
                print(board[r][c], end="  ")
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


class TestCanon(unittest.TestCase):
    def test_black21(self):
        """1st case: black Canon at position (2, 1)"""
        currentPos = [2, 1]
        p = Canon('c', currentPos, 'w')
        moves = p.check_valid_moves()
        # print_board(read_board(), currentPos, moves, p._name)
        self.assertEqual([], (moves))
    # def test_black27(self):
    #     """2nd case: black Canon at position (0, 5)"""
    #     currentPos = [2, 7]
    #     p = Canon('p', currentPos, 'b')
    #     moves = p.check_valid_moves()
    #     # print_board(read_board(), currentPos, moves, p._name)
    #     self.assertEqual(sorted([[0, 4]]), sorted(moves))

    # def test_white71(self):
    #     """3rd case: black Canon at position (1, 4)"""
    #     currentPos = [7, 1]
    #     p = Canon('p', currentPos, 'b')
    #     moves = p.check_valid_moves()
    #     # print_board(read_board(), currentPos, moves, p._name)
    #     self.assertEqual(sorted([[0, 4], [2, 4], [1, 3]]), sorted(moves))

    # def test_white79(self):
    #     """4th case: black Canon at position (1, 0)"""
    #     currentPos = [7, 9]
    #     p = Canon('p', currentPos, 'b')
    #     moves = p.check_valid_moves()
    #     # print_board(read_board(), currentPos, moves, p._name)
    #     self.assertEqual(sorted([[0, 0], [2, 0]]), sorted(moves))

if __name__ == '__main__':
	unittest.main()
