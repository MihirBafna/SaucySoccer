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
import javax.swing.JLabel;

public class Main implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	// Main class fields
	public int screenwidth = 1000;
	public int screenheight = 600;
	public Timer timer;
	private JFrame screen;
	private JLabel field;
	private JLabel goal1;
	private JLabel goal2;
	private GameObject soccerball;
	private GameObject player1;
	private GameObject player2;
	private GameObject weapon1;
	// ------------------------------------- Method Definitions -------------------------------------------//
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Main main = new Main();
	}

	public Main() {
		screen = new JFrame();
		field = new JLabel(new ImageIcon("images/field.png"));
		goal1 = new JLabel(new ImageIcon("images/goal1.png"));
		goal2 = new JLabel(new ImageIcon("images/goal2.png"));
		goal1.setBounds(0, 345, 100, 125);
		goal2.setBounds(900, 345, 100, 125);
		field.setBounds(0, 0, screenwidth, screenheight);
		soccerball = new Ball(new ImageIcon("images/SoccerBall.png"), screenwidth / 2 - 21 / 2, 50 - 21 / 2, 21,
				"ball");
		player1 = new Player(new ImageIcon("images/redBallChar.png"), 50, 420, 50, "player1");
		player2 = new Player(new ImageIcon("images/blueBallChar.png"), 900, 420, 50, "player2");
		weapon1 = new Weapon(new ImageIcon("images/SoccerBall.png"), 50 , 420, 50, "weapon1", player1);
		// soccerball = new Ball(new BufferedImage("images/SoccerBall.png"), screenwidth / 2 - 21 / 2, 50 - 21 / 2, 21,
		// 		"ball");
		// player1 = new Player(new ImageIcon("images/SoccerBallBig.png"), 50, 420, 50, "player1");
		// player2 = new Player(new ImageIcon("images/SoccerBallBig.png"), 900, 420, 50, "player2");
		screen.add(goal1);
		screen.add(goal2);
		screen.add(soccerball.getLabel());
		screen.add(player1.getLabel());
		screen.add(player2.getLabel());
		screen.add(weapon1.getLabel());
		screen.add(field);
		screen.setSize(screenwidth, screenheight);
		screen.setTitle("Saucy Soccer");
		screen.setResizable(true);
		screen.setLayout(null);
		soccerball.init();
		player1.init();
		player2.init();
		weapon1.init();
		screen.addKeyListener(this);
		screen.addMouseMotionListener(this);
		timer = new Timer(1000 / 60, this);
		timer.start();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
	}

	public void loop() {
		soccerball.events();
		player1.events();
		player2.events();
		soccerball.updatePos();
		player1.updatePos();
		player2.updatePos();
		weapon1.updatePos();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		loop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// player 1 key events
		if (e.getKeyCode() == 87) { // up
			player1.setJump(true);
		}
		if (e.getKeyCode() == 83) { // down
		}
		if (e.getKeyCode() == 65) { // left
			player1.setLeftSlide(true);
		}
		if (e.getKeyCode() == 68) { // right
			player1.setRightSlide(true);
		}
		if (e.getKeyCode() == 32) { // spacebar
			player1.setKick(true);
		}
		// player 2 key events
		if (e.getKeyCode() == 38) { // up
			player2.setJump(true);
		}
		if (e.getKeyCode() == 40) { // down
		}
		if (e.getKeyCode() == 37) { // left
			player2.setLeftSlide(true);
		}
		if (e.getKeyCode() == 39) { // right
			player2.setRightSlide(true);
		}
		if (e.getKeyCode() == 18) { // right alt key
			player2.setKick(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// player 1 key events
		if (e.getKeyCode() == 65) { // left
			player1.setLeftSlide(false);
		}
		if (e.getKeyCode() == 68) { // right
			player1.setRightSlide(false);
		}
		if (e.getKeyCode() == 32) { // spacebar
			player1.setKick(false);
		}
		// player 2 key events
		if (e.getKeyCode() == 37) { // left
			player2.setLeftSlide(false);
		}
		if (e.getKeyCode() == 39) { // right
			player2.setRightSlide(false);
		}
		if (e.getKeyCode() == 18) { // right alt key
			player2.setKick(false);
		}
	}

	// unused override methods
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
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
