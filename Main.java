import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main implements ActionListener,KeyListener,MouseListener, MouseMotionListener{
    // Main class fields
    public int screenwidth = 1000;
    public int screenheight = 600;
    public Timer timer;
    private JFrame screen;
    private GameObject soccerball;
    private GameObject player1;
    private GameObject player2;
    private Field field;
    private int dy = 10;
    private int dx = 10;
    private int keyIs; // this variable denotes what key was pressed (1: up, 2: down, 3: left, 4: right)


    //------------------------------------- Method Definitions -------------------------------------------//
    @SuppressWarnings("unused")
    public static void main(String[] args){
        Main main = new Main();
    }

    public Main(){
        screen = new JFrame();
        field = new Field(new ImageIcon("images/field.png"));
        soccerball = new Ball(new ImageIcon("images/SoccerBall.png"), screenwidth / 2 - 21/2, 50 - 21/2, 21);
        player1 = new Player(new ImageIcon("images/SoccerBallBig.png"), 50, 430, 50);
        player2 = new Player(new ImageIcon("images/SoccerBallBig.png"),900, 430, 50);
        screen.add(soccerball.getLabel());
        screen.add(player1.getLabel());
        screen.add(player2.getLabel());
        screen.add(field.getLabel());
        screen.setSize(screenwidth, screenheight);
        screen.setTitle("Saucy Soccer");
        screen.setResizable(true);
        screen.setLayout(null);
        screen.addKeyListener(this);
        screen.addMouseMotionListener(this);
        timer = new Timer(17,this);
        timer.start();
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
    } 

    public void loop(){
        soccerball.events();
        player1.events();
        player2.events();
        soccerball.updatePos();
        player1.updatePos();
        player2.updatePos();

    }

    @Override
	public void actionPerformed(ActionEvent arg0) {
		loop();
    }
    
    @Override
	public void keyPressed(KeyEvent e) {
        // player 1 key bindings
        if (e.getKeyCode() == 87) { // up
            player1.changeYPos(-dy);
            keyIs = 1;
            player1.setJump(true);
        }
        if (e.getKeyCode() == 83) { // down
            player1.changeYPos(dy);
            keyIs = 2;
        }
        if (e.getKeyCode() == 65) { // left
            player1.changeXPos(-dx);
            keyIs = 3;
        }
        if (e.getKeyCode() == 68) { // right
            player1.changeXPos(dx);
            keyIs = 4;
        }
        // player 2 key bindings
        if(e.getKeyCode()==38){ //up
            player2.changeYPos(-dy);
            keyIs = 1;
            player2.setJump(true);
		}
        if(e.getKeyCode()==40){ //down
            player2.changeYPos(dy);
            keyIs = 2;
		}
        if(e.getKeyCode()==37){ //left
            player2.changeXPos(-dx);
            keyIs = 3;
		}
		if(e.getKeyCode()==39){ //right
            player2.changeXPos(dx);
            keyIs = 4;
        }
		loop();
    }
    

    // unused override methods
    @Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

    }
    
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println(e.getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}


	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}
}