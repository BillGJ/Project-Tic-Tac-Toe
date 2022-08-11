# Project-Tic-Tac-Toe
This is a basic Tic-Tac-Toe Game with graphical UI. Just for fun!

## Rules of the Game

Tic-Tac-Toe is a 2 player board game. 
One player is X and the other player is O.
The game starts with an empty 3x3 grid with 9 cells.
The players take turns marking each cell with either an X or an O.
The goal for the X player is to get 3 X’s in one line either vertically,
horizontally or diagonally. The goal for the O player is to get 3 O’s in one line either vertically,
horizontally or diagonally.

![Tic Tac Toe - Diagonal Line](/assets/images/image00.png)

X wins by forming a diagonal line

![Tic Tac Toe - Horizontal Line](/assets/images/image01.png)

O wins by forming a horizontal line

Once a line is formed the game ends and the player who formed that line wins!

Sometimes there is no winner. 
This occurs when all of the cells are filled in but there is no line of 3 X's or 3 O's. 
This often happens, especially when both players are good. In this case, this game ends as a tie.

![Tic Tac Toe - No Winner](/assets/images/image02.png)

Game ends as a tie


## Code design

The source code includes 2 main Java files, **Game.java** and **GameUI.java**.
1. **Game.java** is where all the game logic code exists.
2. **GameUI.java** is where all the user interface code exists. You can take a look at **GameUI.java** if you like.

There is a function called **checkGameWinner()** in the **Game.java** file, 
that is where the logic behind checking the winner is implemented.

`String checkGameWinner(char [][] grid)` has a 2D char array as an input representing the game grid 
(see below for details)
and it returns a String message indicating which player has won
(X wins , O wins, Tie, or None if the game hasn't ended yet)

**doChecks()** is another function that is responsible for calling **checkGameWinner()**
every time a player takes a turn

The Grid is represented as a 2D array of characters, indexed as follows:

![Tic Tac Toe - 2D Array Indices](/assets/images/image03.png)

2D Array Indices
