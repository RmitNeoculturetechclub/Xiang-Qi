"You should use GlobalBoard instead of XiangQiBoard, XiangQiBoard is for initialization." --> Fixed

"Have you handled the logic, the cell that Cannon can jump over another piece?"
Yes, the logic for Cannon jumping over another piece has been implemented using the isBlocked variable. Here's the pseudocode to explain the logic:

Pseudocode:

The Cannon piece will attempt to move horizontally to the left until it reaches the first blocked position.
If the Cannon encounters a piece (which blocks its movement) and that piece belongs to the opponent, it will add the position to its list of possible moves.
If the Cannon encounters a piece that is not an opponent's piece, it will ignore that position and stop.
The Cannon will follow the same logic for moving in other directions.

"Static variables should be placed in another static folder."
Since these are static variables specific to each piece, it is recommended to keep them in the corresponding class itself. However, if you still want to relocate them, please specify the desired location for the static variables.

Please let me know if you need any further assistance or clarification.