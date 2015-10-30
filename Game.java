import java.awt.peer.FramePeer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Game {

	public static int row, col ;
	private boolean playing;
	public char[][] board = new char[3][3];
	Scanner scan = new Scanner(System.in);
	public char turn = 'X';



	public Game(){
		for (int i =0 ; i <3 ; i++)
			for(int j =0; j < 3 ;j++)
				board[i][j] = '_';

		printBoard();

	}

	public void printBoard(){
		for (int i =0 ; i <3 ; i++){
			System.out.println( );
			for(int j =0; j < 3 ;j++)
				System.out.print(board[i][j] + "|");
		}

		System.out.println( );

	}

	public void play(player){
		this.AI = player;
		playing = true;
		while (playing){
			System.out.println("Please enter row and column:");
			row = scan.nextInt() - 1;
			col = scan.nextInt() - 1;
			if((row < 3 && row > -1) && (col < 3 && row > -1) && board[row][col] == '_'){
			board[row][col] = turn;

			if(GameOver()){
				//if(draw == 0){
				playing = false;
				System.out.println("player " + turn + " wins");
		//	}
		//	else{
				//playing = false;
				//System.out.println("It's a draw!");
		//	}
			}
			printBoard();

			if(turn == 'X')
				turn = 'O';
			else
				turn = 'X';
		}

		else
			System.out.println("Only input from 1-3/Aleady taken");
		}
		}

	public boolean GameOver(){

		for(int i = 0 ; i < 3; i++)
		if(board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != '_' )
		return true;

		for(int i = 0 ; i < 3; i++)
	    if(board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != '_')
		return true;

		 if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != '_' )
			return true;
		 else if (board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] != '_' )
			return true;



		 return false;
	}

	public void turnSwitch(){
		if(turn == 'O')
			turn = 'X';
		else
			turn = 'O';

	}

	public char setBoard(int row, int col){
		board[row][col] = turn;
		return turn;
	}
	public char getTurn(){
		return turn;
	}


}
