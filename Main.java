import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main implements ActionListener, KeyListener{
	// Main class fields
	public int screenwidth = 1000;
	public int screenheight = 600;
	public Timer timer;
	private JFrame screen;
	private JFrame menu;
	private JLabel field;
	private JLabel goal1;
	private JLabel goal2;
	ImageIcon powerbarLabel1 = new ImageIcon("images/power1.png");
	ImageIcon powerbarLabel2 = new ImageIcon("images/power2.png");
	ImageIcon powerbarLabel3 = new ImageIcon("images/power3.png");
	ImageIcon powerbarLabel4 = new ImageIcon("images/power4.png");
	ImageIcon powerbarLabel5 = new ImageIcon("images/power5.png");
	ImageIcon powerbarLabel6 = new ImageIcon("images/power6.png");
	ImageIcon powerbarLabel7 = new ImageIcon("images/power7.png");
	ImageIcon powerbarLabel8 = new ImageIcon("images/power8.png");
	ImageIcon powerbarLabel9 = new ImageIcon("images/power9.png");
	ImageIcon powerbarLabel10 = new ImageIcon("images/power10.png");
	ImageIcon powerbarLabel11 = new ImageIcon("images/power11.png");
	ImageIcon[] powerbarList = { powerbarLabel1, powerbarLabel2, powerbarLabel3, powerbarLabel4, powerbarLabel5,
			powerbarLabel6, powerbarLabel7, powerbarLabel8, powerbarLabel9, powerbarLabel10, powerbarLabel11 };
	JLabel powerbar1;
	JLabel powerbar2;
	private int counter1 = 0;
	private int counter2 = 0;
	private JLabel scoreDisplay;
	private String scores;
	private GameObject soccerball;
	private GameObject player1;
	private GameObject player2;
	private GameObject weapon1up;
	private GameObject weapon1down;
	private GameObject weapon2up;
	private GameObject weapon2down;
	private int whichSong = 1; // 1 for synthy, 2 for icy, 3 for chimes

	private static enum State{
		MENU,
		GAME,
		SETTINGS
	}; 
	private static State state = State.MENU;

	
	// ------------------------------------- Method Definitions -------------------------------------------//
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		if(state == State.MENU){
			Main main = new Main();
		}
	}

	public Main() {
		menu = new JFrame();
		JLabel background = new JLabel(new ImageIcon("images/nightbackground.png")); 	// image from https://www.vectorstock.com/royalty-free-vector/cartoon-game-background-vector-7926680
		JLabel title = new JLabel(new ImageIcon("images/saucysoccerlogo.png"));
		JLabel music = new JLabel(new ImageIcon("images/saucymusic.png"));
		JLabel controls = new JLabel(new ImageIcon("images/controls.png"));
		JLabel settings = new JLabel(new ImageIcon("images/settings.png"));
		JButton playbutton = new JButton();
		JButton controlsbutton = new JButton();
		JButton settingsbutton = new JButton();
		JButton forkbutton = new JButton();
		JButton backbutton = new JButton();
		JButton music1 = new JButton("synthy beat");
		JButton music2 = new JButton("icy synth");
		JButton music3 = new JButton("chimes beat");
		backbutton.setVisible(false);
		controls.setVisible(false);
		settings.setVisible(false);
		music.setVisible(false);
		music1.setVisible(false);
		music2.setVisible(false);
		music3.setVisible(false);
		backbutton.setIcon(new ImageIcon("images/backbutton1.png"));
		forkbutton.setIcon(new ImageIcon("images/githublogo.png"));
		playbutton.setIcon(new ImageIcon("images/playbutton.png"));
		controlsbutton.setIcon(new ImageIcon("images/controlsbutton.png"));
		settingsbutton.setIcon(new ImageIcon("images/settingsbutton.png"));
		title.setBounds(screenwidth/2-200,20,400,200);
		background.setBounds(0,0,screenwidth, screenheight);
		playbutton.setBounds(screenwidth / 2 -80, 240, 160, 80);
		controlsbutton.setBounds(screenwidth / 2 - 80, 340, 160, 80);
		settingsbutton.setBounds(screenwidth / 2 - 80, 440, 160, 80);
		forkbutton.setBounds(950,10,40,40);
		controls.setBounds(0,0,screenwidth,screenheight);
		settings.setBounds(0,0,screenwidth,screenheight);
		backbutton.setBounds(10, 10, 60, 60);
		music.setBounds(screenwidth/2-200,20,400,230);
		music1.setBounds(screenwidth/2-80,250,160,50);
		music2.setBounds(screenwidth / 2 - 80, 320, 160, 50);
		music3.setBounds(screenwidth / 2 - 80, 390, 160, 50);
		playbutton.setOpaque(false);
		playbutton.setContentAreaFilled(false);
		playbutton.setBorderPainted(false);
		backbutton.setOpaque(false);
		backbutton.setContentAreaFilled(false);
		backbutton.setBorderPainted(false);
		forkbutton.setContentAreaFilled(false);
		forkbutton.setOpaque(false);
		forkbutton.setBorderPainted(false);
		settingsbutton.setContentAreaFilled(false);
		settingsbutton.setOpaque(false);
		settingsbutton.setBorderPainted(false);
		controlsbutton.setContentAreaFilled(false);
		controlsbutton.setOpaque(false);
		controlsbutton.setBorderPainted(false);
		menu.add(music);
		menu.add(music1);
		menu.add(music2);
		menu.add(music3);
		menu.add(backbutton);
		menu.add(controls);
		menu.add(settings);
		menu.add(forkbutton);
		menu.add(controlsbutton);
		menu.add(settingsbutton);
		menu.add(playbutton);
		menu.add(title);
		menu.add(background);
		menu.pack();
		menu.setSize(screenwidth, screenheight);
		menu.setTitle("Saucy Soccer");
		menu.setResizable(false);
		menu.setLocationByPlatform(true);
		menu.setLayout(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);		
		forkbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLink("https://github.com/MihirBafna/SaucySoccer");
			}
		});
		playbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				menu.dispose();
				state = State.GAME;
				startGame();
			}
		});
		controlsbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playbutton.setVisible(false);
				forkbutton.setVisible(false);
				settingsbutton.setVisible(false);
				controls.setVisible(true);
				backbutton.setVisible(true);

			}
		});
		backbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controls.setVisible(false);
				backbutton.setVisible(false);
				settings.setVisible(false);
				music1.setVisible(false);
				music2.setVisible(false);
				music3.setVisible(false);
				music.setVisible(false);
				playbutton.setVisible(true);
				forkbutton.setVisible(true);
				controlsbutton.setVisible(true);
				settingsbutton.setVisible(true);
			}
		});
		settingsbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controls.setVisible(false);
				backbutton.setVisible(true);
				playbutton.setVisible(false);
				forkbutton.setVisible(false);
				settingsbutton.setVisible(false);
				controlsbutton.setVisible(false);
				music.setVisible(true);
				settings.setVisible(true);
				music1.setVisible(true);
				music2.setVisible(true);
				music3.setVisible(true);
			}
		});
		music1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				whichSong = 1;
			}
		});
		music2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				whichSong = 2;
			}
		});
		music3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				whichSong = 3;
			}
		});

	}

	public void startGame() {
		screen = new JFrame();
		field = new JLabel(new ImageIcon("images/nightbackground.png"));
		goal1 = new JLabel(new ImageIcon("images/goal1.png"));
		goal2 = new JLabel(new ImageIcon("images/goal2.png"));
		powerbar1 = new JLabel(powerbarLabel1);
		powerbar2 = new JLabel(powerbarLabel1);
		scoreDisplay = new JLabel("");
		powerbar1.setBounds(30, 10, 400, 100);
		powerbar2.setBounds(560, 10, 400, 100);
		goal1.setBounds(0, 345, 100, 125);
		goal2.setBounds(900, 345, 100, 125);
		field.setBounds(0, 0, screenwidth, screenheight);
		scoreDisplay.setBounds(screenwidth / 2 - 15, 50, 50, 50);
		soccerball = new Ball(new ImageIcon("images/SoccerBall.png"), screenwidth / 2 - 21 / 2, 50 - 21 / 2, 21,
				"ball");
		player1 = new Player(new ImageIcon("images/redBallChar.png"), 150, 420, 50, "player1");
		player2 = new Player(new ImageIcon("images/blueBallChar.png"), 800, 420, 50, "player2");
		weapon1up = new Weapon(new ImageIcon("images/TrainingStickP1Up.png"), 150, 420, 50, "weapon1up", 1, true);
		weapon1down = new Weapon(new ImageIcon("images/TrainingStickP1Down.png"), 150, 420, 50, "weapon1down", 1, false);
		weapon2down = new Weapon(new ImageIcon("images/TrainingStickP2Down.png"), 800, 420, 50, "weapon2down", 2,false);
		weapon2up = new Weapon(new ImageIcon("images/TrainingStickP2Up.png"), 800, 420, 50, "weapon2up", 2, true);
		screen.add(powerbar1);
		screen.add(powerbar2);
		screen.add(goal1);
		screen.add(goal2);
		screen.add(soccerball.getLabel());
		screen.add(player1.getLabel());
		screen.add(player2.getLabel());
		screen.add(weapon1up.getLabel());
		screen.add(weapon1down.getLabel());
		screen.add(weapon2down.getLabel());
		screen.add(weapon2up.getLabel());
		screen.add(scoreDisplay);
		screen.add(field);
		screen.setSize(screenwidth, screenheight);
		screen.setTitle("Saucy Soccer");
		screen.setResizable(false);
		screen.setLayout(null);
		soccerball.init();
		player1.init();
		player2.init();
		weapon1up.init();
		weapon1down.init();
		weapon2down.init();
		weapon2up.init();
		displayScores();
		screen.addKeyListener(this);
		timer = new Timer(1000 / 60, this);
		timer.start();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);
		if(whichSong == 1){
			SoundEffect.synthybeat.setRepeat(true);
			SoundEffect.synthybeat.play();
		}else if (whichSong == 2){
			SoundEffect.icysynth.setRepeat(true);
			SoundEffect.icysynth.play();
		}else if(whichSong == 3){
			SoundEffect.chimesbeattrap.setRepeat(true);
			SoundEffect.chimesbeattrap.play();
		}
	}


	public void loop() {
		if(state == State.GAME){
			soccerball.events();
			player1.events();
			player2.events();
			weapon1up.events();
			weapon1down.events();
			weapon2down.events();
			weapon2up.events();
			soccerball.updatePos();
			player1.updatePos();
			player2.updatePos();
			displayScores();
			displayPowerBars();
		}
	}

	public boolean won() {
		return true;
	}

	public void displayScores() {
		scores = ((Player) player1).getScore()/2 + " : " + ((Player) player2).getScore()/2;
		scoreDisplay.setText(scores);
		scoreDisplay.setText(scores);
	}

	public void displayPowerBars() {
		if (((Player) player1).hasPower()) {
			counter1 = 0;
		} else if (((Player) player1).getPowerLevel() % 50 == 0 || counter1 == 0) {
			if (counter1 == 0) {
				powerbar1.setIcon(powerbarList[counter1]);
				counter1++;
			} else {
				powerbar1.setIcon(powerbarList[counter1++]);
			}
		}
		if (((Player) player2).hasPower()) {
			counter2 = 0;
		} else if (((Player) player2).getPowerLevel() % 50 == 0 || counter2 == 0) {
			if (counter2 == 0) {
				powerbar2.setIcon(powerbarList[counter2]);
				counter2++;
			} else {
				powerbar2.setIcon(powerbarList[counter2++]);
			}
		}
	}
	
	public void openLink(String url) {
		try {
			Desktop.getDesktop().browse(new URL(url).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		loop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		// player 1 key events
    	if(e.getKeyCode() == 70) {
			((Weapon) weapon1up).setWeaponSwing(true);
		}
		if(e.getKeyCode() == 71) {
		}
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
			((Weapon) weapon1down).setWeaponSwing(true);
			player1.setKick(true);
		}
		// player 2 key events
		if (e.getKeyCode() == 16) {
			((Weapon) weapon2up).setWeaponSwing(true);
		}
		if (e.getKeyCode() == 18) {
			((Weapon) weapon2down).setWeaponSwing(true);
		}
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
		if (e.getKeyCode() == 70) {
			((Weapon) weapon1up).setWeaponSwing(false);
		}
		if (e.getKeyCode() == 71) {
		}
		if (e.getKeyCode() == 65) { // left
			player1.setLeftSlide(false);
		}
		if (e.getKeyCode() == 68) { // right
			player1.setRightSlide(false);
		}
		if (e.getKeyCode() == 32) { // spacebar
			((Weapon) weapon1down).setWeaponSwing(false);
			player1.setKick(false);
		}
		if (e.getKeyCode() == 69) { // e key
			((Player) player1).setUsingPower(true);
		}
		// player 2 key events
		if (e.getKeyCode() == 16) {
			((Weapon) weapon2up).setWeaponSwing(false);
		}
		if (e.getKeyCode() == 18) {
			((Weapon) weapon2down).setWeaponSwing(false);
		}
		if (e.getKeyCode() == 37) { // left
			player2.setLeftSlide(false);
		}
		if (e.getKeyCode() == 39) { // right
			player2.setRightSlide(false);
		}
		if (e.getKeyCode() == 18) { // right alt key
			player2.setKick(false);
		}
		if (e.getKeyCode() == 80) { // e key
			((Player) player2).setUsingPower(true);
		}
	}


	// unused override methods
	@Override
	public void keyTyped(KeyEvent e) {

	}


}