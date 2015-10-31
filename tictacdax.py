from javax.swing import (JButton, JFrame, JPanel, JLabel, JTextField, JOptionPane,
        SwingConstants, WindowConstants)
from java.awt import Component, GridLayout, Font

import random
class Player(object):
    def __init__(self, name, xo, board, empty_cells, ui, level='human'): #['human','random','1','2','3']
        self.level = level
        self.name = name
        self.xo = xo
        self.board = board
        self.empty_cells = empty_cells
        self.ui = ui
        self.win_cells = [['00', '01', '02'], ['10', '11', '12'], ['20', '21', '22'], ['00', '10', '20'],
                             ['10','11','21'], ['02', '12','22'], ['00', '11', '22'], ['02', '11', '20']]
        self.maxval = 0;



    def ai_pick(self):
        if self.level == 'level1':
            tracker = {}
            for emptycell in self.empty_cells:
                tracker[emptycell] = 0
                if self.doIwin(emptycell):
                    tracker[emptycell] = 100
                elif self.enemywins(emptycell):
                    tracker[emptycell] = 50
                else:
                    tracker[emptycell] = self.numwins(emptycell)

            count = tracker[self.empty_cells[0]]
            cell = self.empty_cells[0]
            for emptycell in self.empty_cells:
                if count < tracker[emptycell]:
                    cell = emptycell
                    count = tracker[emptycell]
            print 'pick', cell, count, tracker



            return cell


    def turn(self,button=None):

        if self.level == 'human':
            cell = str(button.row) + str(button.col)
            row = button.row
            col = button.col
            button.text = self.xo
        elif self.level == 'random':
            cell = self.pick_random()
            row = int(cell[0])
            col = int(cell[1])

            for b in self.ui.buttons:
                if b.row == row and b.col == col:
                    b.text = self.xo
        elif self.level == 'level1':
            cell = self.ai_pick()
            row = int(cell[0])
            col = int(cell[1])
            for b in self.ui.buttons:
                if b.row == row and b.col == col:
                    b.text = self.xo

        self.empty_cells.remove(cell)
        self.board[row][col] = self.xo



    def pick_random(self):
        cell = random.choice(self.empty_cells)
        return cell


    def __repr__(self):
        return '<Player: name:' + self.name +  ' level: ' + self.level +'>'

    def getenemymarker(self):
        if(self.xo == 'x'):
            return 'o'
        else: return 'x'

    def numwins(self, cell):
        count = 0
        for win_cell in self.win_cells:
            has_enemy = False
            for win in win_cell:
                row = int(win[0])
                col = int(win[1])
                if(self.getenemymarker() == self.board[row][col]):
                    has_enemy = True
                    break

            if cell in win_cell and has_enemy==False:
                count += 1

        return count

    def enemywins(self, cell):
        for win_cell in self.win_cells:
            count = 0
            hasblank = False
            if cell in win_cell:
                for i in win_cell:
                    row = int(i[0])
                    col = int(i[1])
                    if(self.board[row][col] == self.getenemymarker()):
                        count += 1
                    elif(self.board[row][col] == '_'):
                        hasblank = True
                    if(count == 2 and hasblank == True):
                        return True
        return False

    def doIwin(self, cell):
        for win_cell in self.win_cells:
            count = 0
            hasblank = False
            if cell in win_cell:
                for i in win_cell:
                    row = int(i[0])
                    col = int(i[1])
                    if(self.board[row][col] == self.xo):
                        count += 1
                    elif(self.board[row][col] == '_'):
                        hasblank = True
                    if(count == 2 and hasblank == True):
                        return True
        return False


class Game(object):
    def __init__(self,p1_level='human', p2_level='human'):
        self.board = [['_','_','_'],['_','_','_'],['_','_','_']]
        self.empty_cells = ['00','01','02','10','11','12','20','21','22']
        self.turn = 0
        self.state = 'next'
        self.ui = UI(self)

        self.p1 = Player('p1', 'x', self.board, self.empty_cells, self.ui, level=p1_level)
        self.p2 = Player('p2', 'o', self.board, self.empty_cells, self.ui, level=p2_level)


        self.current_player = self.p1

    def auto(self):
        if self.p1.level != 'human' and self.p2.level != 'human':
            while(self.state=='next'):
                self.update()


    def update(self,button=None): #human triggered
        print 'current1', self.current_player

        if(button!=None):
            #human
            cell = str(button.row) + str(button.col)
            if cell in self.empty_cells and self.current_player.level=='human':
                self.current_player.turn(button)
                self.update_state()
                if (self.state == 'next'):
                    self.switch_turn()

        print 'current2', self.current_player
        if self.current_player.level != 'human':
            #ai!
            print "before ai's turn", self.current_player, self.board, self.empty_cells

            self.current_player.turn()
            self.update_state()
            if (self.state == 'next'):
                self.switch_turn()

            print "after ai's turn", self.current_player, self.board, self.empty_cells

        print 'update', self.state, self.turn

        if self.state == 'over':
            JOptionPane.showMessageDialog(self.ui.panel,
                "Game Over! Player: " + self.current_player.name + " Wins!!",
                "Winner", JOptionPane.INFORMATION_MESSAGE)
        elif self.state == 'draw':
                JOptionPane.showMessageDialog(self.ui.panel,
                    "Its a draw!",
                    "Draw", JOptionPane.INFORMATION_MESSAGE)

    def switch_turn(self):
        if self.current_player == self.p1:
            self.current_player = self.p2
        else:
            self.current_player = self.p1

        self.turn += 1


    def update_state(self):
        board = self.board
        for i in range(3):
            if(board[i][0] == board[i][1] and board[i][0] == board[i][2] and board[i][0] != '_' ):
                self.state = 'over'
                return

        for i in range(3):
            if(board[0][i] == board[1][i] and board[0][i] == board[2][i] and board[0][i] != '_'):
                self.state = 'over'
                return

        if(board[0][0] == board[1][1] and board[0][0] == board[2][2] and board[0][0] != '_' ):
            self.state = 'over'
            return

        elif (board[2][0] == board[1][1] and board[2][0] == board[0][2] and board[2][0] != '_' ):
            self.state = 'over'
            return

        if self.turn>=8:
            self.state = 'draw'
            return

        self.state = 'next'
        return

class TicTacButton(JButton):
    def __init__(self, row, col, *args, **kwargs):
        self.row = row
        self.col = col
        JButton.__init__(self, *args, **kwargs)
        self.setFont(Font("Arial", Font.BOLD,90))

class UI(object):
    def __init__(self, game):

        self.frame = JFrame("Tic Tac Toe",
                             defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE)

        self.panel = JPanel(GridLayout(3,3))
        self.buttons = []
        self.game = game
        for row in range(3):
            for col in range(3):
                self.buttons.append(TicTacButton(row,col,'',actionPerformed=self.clicked_button))

        self.panel.add
        self.frame.add(self.panel)
        for b in self.buttons:
            self.panel.add(b)

        self.frame.pack()
        self.show()


    def show(self):
        self.frame.size = 600,600
        self.frame.visible = True


    def clicked_button(self, event):
        button = event.getSource()
        print 'current_player', self.game.current_player
        if self.game.current_player.level == 'human' and self.game.state=='next':
            button.text = self.game.current_player.xo
            self.game.update(button)
        else:
            pass

if __name__ == '__main__':
    g = Game(p2_level='level1')

    #g = Game(p1_level='random', p2_level='random')
    g.auto()
