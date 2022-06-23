package com.udacity;

import java.util.Arrays;

/**
 * Created by udacity 2016
 * The Main class containing game logic and backend 2D array
 */
public class Game {

    private char turn; // who's turn is it, 'x' or 'o' ? x always starts
    private boolean twoPlayer; // true if this is a 2 player game, false if AI playing
    private char [][] grid; // a 2D array of chars representing the game grid
    private int freeSpots; // counts the number of empty spots remaining on the board (starts from 9  and counts down)
    private static GameUI gui;

    /**
     * Create a new single player game
     *
     */
    public Game() {
        newGame(false);
    }

    /**
     * Create a new game by clearing the 2D grid and restarting the freeSpots counter and setting the turn to x
     * @param twoPlayer: true if this is a 2 player game, false if playing against the computer
     */
    public void newGame(boolean twoPlayer){
        //sets a game to one or two player
        this.twoPlayer = twoPlayer;

        // initialize all chars in 3x3 game grid to '-'
        grid = new char[3][3];
        //fill all empty slots with -
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                grid[i][j] = '-';
            }
        }
        //start with 9 free spots and decrement by one every time a spot is taken
        freeSpots = 9;
        //x always starts
        turn = 'x';
    }


    /**
     * Gets the char value at that particular position in the grid array
     * @param i the x index of the 2D array grid
     * @param j the y index of the 2D array grid
     * @return the char value at the position (i,j):
     *          'x' if x has played here
     *          'o' if o has played here
     *          '-' if no one has played here
     *          '!' if i or j is out of bounds
     */
    public char gridAt(int i, int j){
        if(i>=3||j>=3||i<0||j<0)
            return '!';
        return grid[i][j];
    }

    /**
     * Places current player's char at position (i,j)
     * Uses the variable turn to decide what char to use
     * @param i the x index of the 2D array grid
     * @param j the y index of the 2D array grid
     * @return boolean: true if play was successful, false if invalid play
     */
    public boolean playAt(int i, int j){
        //check for index boundries
        if(i>=3||j>=3||i<0||j<0)
            return false;
        //check if this position is available
        if(grid[i][j] != '-'){
            return false; //bail out if not available
        }
        //update grid with new play based on who's turn it is
        grid[i][j] = turn;
        //update free spots
        freeSpots--;
        return true;
    }


    /**
     * Override
     * @return string format for 2D array values
     */
    public String toString(){
        return Arrays.deepToString(this.grid);
    }

    /**
     * Performs the winner chack and displayes a message if game is over
     * @return true if game is over to start a new game
     */
    public boolean doChecks() {
        //check if there's a winner or tie ?
        String winnerMessage = checkGameWinner(grid);
        if (!winnerMessage.equals("None")) {
            gui.gameOver(winnerMessage);
            newGame(false);
            return true;
        }
        return false;
    }

    /**
     * Allows computer to play in a single player game or switch turns for 2 player game
     */
    public void nextTurn(){
        //check if single player game, then let computer play turn
        if(!twoPlayer){
            if(freeSpots == 0){
                return ; //bail out if no more free spots
            }
            int ai_i, ai_j;
            do {
                //randomly pick a position (ai_i,ai_j)
                ai_i = (int) (Math.random() * 3);
                ai_j = (int) (Math.random() * 3);
            }while(grid[ai_i][ai_j] != '-'); //keep trying if this spot was taken
            //update grid with new play, computer is always o
            grid[ai_i][ai_j] = 'o';
            //update free spots
            freeSpots--;
        }
        else{
            //change turns
            if(turn == 'x'){
                turn = 'o';
            }
            else{
                turn = 'x';
            }
        }
        return;
    }


    /**
     * Checks if the game has ended either because a player has won, or if the game has ended as a tie.
     * If game hasn't ended the return string has to be "None",
     * If the game ends as tie, the return string has to be "Tie",
     * If the game ends because there's a winner, it should return "X wins" or "O wins" accordingly
     * @param grid 2D array of characters representing the game board
     * @return String indicating the outcome of the game: "X wins" or "O wins" or "Tie" or "None"
     */
    public String checkGameWinner(char [][]grid){
        String result = "None";
        //Student code goes here ...
        int sizeOfArray = 2;
        int winCount = 3 ;

        int countX, countO = 0;

        //Horizontal win rows
        int countFirstRowX = 0;
        int countFirstRowO = 0;
        int countSecondRowX = 0;
        int countSecondRowO = 0;
        int countThirdRowX = 0;
        int countThirdRowO = 0;

        //Horizontal win rows
        int countFirstColumnX = 0;
        int countFirstColumnO = 0;
        int countSecondColumnX = 0;
        int countSecondColumnO = 0;
        int countThirdColumnX = 0;
        int countThirdColumnO = 0;

        //Diagonal starting from top left
        int countDiagOneX = 0;
        int countDiagOneO = 0;
        int countDiagTwoX = 0;
        int countDiagTwoO = 0;

        //counting empty spot
        int emptySpot = 0;


        for(int column = 0; column <= sizeOfArray; column++){

            for(int row = 0; row <= sizeOfArray; row++){

                //checking for empty spot
                if(grid[column][row] == '-'){
                    emptySpot += 1;
                }

                if(emptySpot == 0)
                    result = "Tie.";

                //Check if the 1st row/ 2nd row / 3rd row has 3 same characters that are consecutive (X or O)
                if(row == 0){
                    if(grid[column][row] == 'x')
                        countFirstRowX += 1;
                    if(grid[column][row] == 'o')
                        countFirstRowO += 1;
                }
                if(row == 1){
                    if(grid[column][row] == 'x')
                        countSecondRowX += 1;
                    if(grid[column][row] == 'o')
                        countSecondRowO += 1;
                }
                if(row == 2){
                    if(grid[column][row] == 'x')
                        countThirdRowX += 1;
                    if(grid[column][row] == 'o')
                        countThirdRowO += 1;
                }


                //Check if the 1st column/ 2nd column / 3rd column has 3 same characters that are consecutive (X or O)
                if(column == 0){
                    if(grid[column][row] == 'x')
                        countFirstColumnX += 1;
                    if(grid[column][row] == 'o')
                        countFirstColumnO += 1;
                }
                if(column == 1){
                    if(grid[column][row] == 'x')
                        countSecondColumnX += 1;
                    if(grid[column][row] == 'o')
                        countSecondColumnO += 1;
                }
                if(column == 2){
                    if(grid[column][row] == 'x')
                        countThirdColumnX += 1;
                    if(grid[column][row] == 'o')
                        countThirdColumnO += 1;
                }

                //Diagonal starting from top left
                if(column == row){
                    if(grid[column][row] == 'x')
                        countDiagOneX += 1;
                    if(grid[column][row] == 'o')
                        countDiagOneO += 1;
                }
                //Diagonal starting from top right
                if(column + row == 2){
                    if(grid[column][row] == 'x')
                        countDiagTwoX += 1;
                    if(grid[column][row] == 'o')
                        countDiagTwoO += 1;
                }
            }

            if(countFirstRowX == winCount || countSecondRowX == winCount || countThirdRowX == winCount){
                result = "X wins";
            }
            if(countFirstRowO == winCount || countSecondRowO == winCount || countThirdRowO == winCount){
                result = "O wins";
            }


            if(countFirstColumnX == winCount || countSecondColumnX == winCount || countThirdColumnX == winCount){
                result = "X wins";
            }
            if(countFirstColumnO == winCount || countSecondColumnO == winCount || countThirdColumnO == winCount){
                result = "O wins";
            }

            if(countDiagOneX == winCount || countDiagTwoX == winCount){
                result = "X wins";
            }
            if(countDiagOneO == winCount || countDiagTwoO == winCount){
                result = "O wins";
            }


        }

        return result;
    }

    /**
     * Main function
     * @param args command line arguments
     */
    public static void main(String args[]){
        Game game = new Game();
        gui = new GameUI(game);
    }

}
