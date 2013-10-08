import java.io.*;
import java.util.*;

enum Piece
{
  BLANK(" "), STAR("★"), UMBRELLA("☂"), SUN("☀"), DIAMOND("♦"), HEART("♥"), SPADE("♠"), CLUB("♣");
  public String value;
  private Piece(String value){
    this.value = value;
  }
}

class Board {
  /* 
    Board should be a 2d array
  */
  public Piece[][] boardGrid;
  private int BOARD_SIZE = 8;
  private int[][] walkRow = {{1,0},{0,1}};
  private int[][] walkColumn = {{1,0},{0,1}};
  public Board(){
    this.boardGrid = new Piece[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        String mooz = " ";
        Random randomPiece = new Random();
        int gemInt = randomPiece.nextInt(BOARD_SIZE-1) + 1;
        this.boardGrid[i][j] = Piece.values()[gemInt];
      }
    }
  }



  public void displayBoard(){
    System.out.println("\033[H\033[2J"); // Clears terminal
    System.out.println("      You're playing Bejwelly...");
    StringBuilder topRow = new StringBuilder();
    topRow.append("  ");
    for (int i = 0; i < BOARD_SIZE; i++) {
      topRow.append("| " + (i + 1) + " ");
    }
    topRow.append("|");
    System.out.println(topRow.toString());
    for (int i = 0; i < BOARD_SIZE; i++) {
      StringBuilder sb = new StringBuilder();
      sb.append(getCharForNumber(i + 1) + " |");
      for (int j = 0; j < BOARD_SIZE; j++) {
        // String[] boadGrid = this.boardGrid.get(i).toArray(new String[this.boardGrid.get(i).size()]);
        // System.out.println(Arrays.toString(boadGrid));
        sb.append(" " + this.boardGrid[i][j].value + " ");
        sb.append("|");
      }
      System.out.println(sb.toString());
    }
    promptUser();
  }

  public void promptUser(){
    Console console = System.console();
    String input = console.readLine("Which piece would you like to move? (e.g 1A) ");
    // String[] input_array = input.split("\\|",-1); 
    char[] input_array = input.toCharArray();
    System.out.println("You entered Column: " + input_array[0] + " Row: " + input_array[1]);
    String direction_input = console.readLine("In which direction to swap? (U,D,L,R) ");
    char[] direction_input_array = direction_input.toCharArray();
    System.out.println("You entered Swap: " + direction_input_array[0]);
    displayBoard();
  }

  private String getCharForNumber(int i) {
    return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
  }

  private int getNumberForCharacter(String value) {
    return (int)(value).charAt(0)-64;
  }

}

public class Bejewelly {
  public static void main(String[] args) {
    Board gameBoard = new Board();
    gameBoard.displayBoard();
  }
}