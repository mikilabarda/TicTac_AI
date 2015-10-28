import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JPanel p = new JPanel();
	JButton button[][] =  new JButton[3][3];
	Game ttt;
	public Frame(){
		super("TicTacToe");
		setSize(600,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(3,3));
		for(int i=0 ; i<3 ; i++){
			for(int j=0; j<3; j++){
				button[i][j] = new JButton();
				button[i][j].addActionListener(this);
				button[i][j].setFont(new Font("Arial", Font.BOLD, 90));
				p.add(button[i][j]);
			}

		}
		add(p);
		setVisible(true);

		ttt = new Game();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == button[0][0]){
			button[0][0].setText(Character.toString(ttt.setBoard(0, 0)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[0][1]){
			button[0][1].setText(Character.toString(ttt.setBoard(0, 1)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[0][2]){
			button[0][2].setText(Character.toString(ttt.setBoard(0, 2)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[1][0]){
			button[1][0].setText(Character.toString(ttt.setBoard(1, 0)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[1][1]){
			button[1][1].setText(Character.toString(ttt.setBoard(1, 1)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[1][2]){
			button[1][2].setText(Character.toString(ttt.setBoard(1, 2)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[2][0]){
			button[2][0].setText(Character.toString(ttt.setBoard(2, 0)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
			else if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[2][1]){
			button[2][1].setText(Character.toString(ttt.setBoard(2, 1)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
				else if(ttt.GameOver())
					JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
		if(event.getSource() == button[2][2]){
			button[2][2].setText(Character.toString(ttt.setBoard(2, 2)));
			if(ttt.GameOver())
				JOptionPane.showMessageDialog(this,"Game Over! Player: " + ttt.getTurn() + " Wins!!");
				else if(ttt.GameOver())
					JOptionPane.showMessageDialog(this,"It's a draw!");
			else
				ttt.turnSwitch();
		}
	}
}
