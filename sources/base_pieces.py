"""Xiang Qi Piece Abstract Module"""
# pylint: disable=unnecessary-pass, line-too-long

from __future__ import annotations
from typing import List, Dict, Union
from abc import abstractmethod, ABCMeta


class Piece(meta=ABCMeta):
    """Abstract Class of Xiang Qi Pieces"""

    def __init__(self, name: str):
        self._id: int = 0
        self._name: str = name
        self.is_alive: bool = True
        self.current_position: List[int, int] = []
        self.possible_positions: Dict[int : Union[List[int, int], None]] = {}

    # return the current teritory of this piece (0: White, 1: Black)
    @abstractmethod
    def check_teritory(self) -> int:
        """Checking the teritory of the pieces based on the position of it

        Returns:
            int: return either the pieces at White (0) or Black (1)
        """
        pass

    @abstractmethod
    def check_valid_moves(self) -> Dict[int : Union[List[int, int], None]]:
        """Check all of the possible move of a chess piece

        Returns:
            Dict[int: Union[List[int, int],  None]]: either return a hashmap of possible move or None
        """
        pass

    @abstractmethod
    def set_position(self, next_position: List[int, int]) -> bool:
        """Set the position of the piece to the corresponding move

        Args:
            next_position (List[int, int]): the next position of the piece will go to

        Returns:
            bool: move yet or not
        """
        pass

    def get_position(self) -> List[int, int]:
        """Get the current position of the piece
        
        Returns:
            - List[int, int]: the current position of the chess pieces
        
        """
        return getattr(self.current_position)
