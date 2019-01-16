import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;

public class Player extends GameObject {
	private int score;
	private int powerLevel;
	private boolean usingPower = false;
	private ImageIcon poweredplayer1 = new ImageIcon("images/BallCharacterRedFire.gif");
	private ImageIcon poweredplayer2 = new ImageIcon("images/BallCharacterBlueFire.gif");
	private GameObject ball ;
	private GameObject player1;
	private GameObject player2;

	public Player(ImageIcon image, int x, int y, int size, String key) {
		super(image, x, y, size, key);
	}

	public void init() {
		gameObjects.put(id, this);
		gameObjects.get(id).collisionArea = new Circle((double) getXPos() + getSize() / 2,
				(double) getYPos() + getSize() / 2, (double) getSize() / 2);

	}

	// events method contains a series of if statements that affect the players
	// velocity and position //
	public void events() {
		ball = gameObjects.get("ball");
		player1 = gameObjects.get("player1");
		player2 = gameObjects.get("player2");
		if (getYPos() <= playerground)
			changeYVel(gravity * dt); // above the ground
		if (getYPos() >= playerground) { // on the ground
			setYPos(playerground);
			setYVel(0);
		}
		if (getXPos() >= screenwidth - getSize()) { // past right wall
			setXPos(screenwidth - getSize());
		}
		if (getXPos() <= 0) { // past left wall
			setXPos(0);
		}
		if (jump && getYPos() >= ground - 30) { // if the player is jumping
			setYVel(-jumpspeed);
			jump = false;
		}
		if (leftSlide) {	// if player is moving to the left
			setXVel(-8);
		} else if (rightSlide) { // if player is moving to the right
			setXVel(8);
		} else {
			setXVel(0); // if player is not moving
		}
		if(rectangleCircleCollision(ball.collisionArea, goal1)){ // if player 2 scored a goal
			((Player)player2).score++;
			resetPosition();
		}
		if (rectangleCircleCollision(ball.collisionArea, goal2)) { // if player 1 scored a goal
			((Player) player1).score++;
			resetPosition();
		}
		if (isCircleCollision(gameObjects.get("player1").collisionArea, gameObjects.get("player2").collisionArea)) { // if player 1 and player 2 collide
			if(gameObjects.get("player1").getXVel()<0){
				gameObjects.get("player1").changeXPos(10);
				gameObjects.get("player1").setXVel(0);
			}else if(gameObjects.get("player1").getXVel()>0){
				gameObjects.get("player1").changeXPos(-10);
				gameObjects.get("player1").setXVel(0);
			}
			if(gameObjects.get("player2").getXVel()<0){
				gameObjects.get("player2").changeXPos(10);
				gameObjects.get("player2").setXVel(0);
			}else if(gameObjects.get("player2").getXVel()>0){
				gameObjects.get("player2").changeXPos(-10);
				gameObjects.get("player2").setXVel(0);
			}
		}

		if (powerLevel <= 500) {	// incrementing power level until reaches 500
			this.powerLevel++;
		}

		if (((Player) gameObjects.get("player1")).hasPower()) { 	// checking if player 1 has power and applying power if it is touching the ball
			if (((Player) gameObjects.get("player1")).usingPower) {
				((Player) gameObjects.get("player1")).getLabel().setIcon(poweredplayer1);
				if (isCircleCollision(gameObjects.get("player1").collisionArea,
						gameObjects.get("ball").collisionArea)) {
					this.applyPower(1);
				}
			}
		} else {
			gameObjects.get("player1").getLabel().setIcon(gameObjects.get("player1").getImg()); //setting player 1 power image
		}
		if (((Player) gameObjects.get("player2")).hasPower()) {	// checking if player 2 has power and applying power if it is touching the ball
			if (((Player) gameObjects.get("player2")).usingPower) {
				((Player) gameObjects.get("player2")).getLabel().setIcon(poweredplayer2);
				if (isCircleCollision(gameObjects.get("player2").collisionArea,
						gameObjects.get("ball").collisionArea)) {
					this.applyPower(2);
				}
			}

		} else {
			gameObjects.get("player2").getLabel().setIcon(gameObjects.get("player2").getImg());
		}

	}

	// updatePos method updates the position of the player based on changes made to
	// the x and y velocities //
	public void updatePos() {
		changeYPos((int) (getYVel() * dt));
		changeXPos((int) (getXVel() * dt));
		gameObjects.get(id).collisionArea.setCenterX(getXPos() + getSize() / 2);
		gameObjects.get(id).collisionArea.setCenterY(getYPos() + getSize() / 2);
	}

	public int getPowerLevel() {
		return powerLevel;
	}
	
	public void setPowerLevel(int powerLevel) {
		this.powerLevel = powerLevel;
	}

	public boolean hasPower() {
		if (this.powerLevel > 500) {
			return true;
		} else {
			return false;
		}
	}

	public void applyPower(int whichPlayer) {
		if (whichPlayer == 1) {
			gameObjects.get("ball").setXPos(((Player) gameObjects.get("player1")).getXPos() + ((Player) gameObjects.get("player1")).getSize());
			gameObjects.get("ball").setYPos(((Player) gameObjects.get("player1")).getYPos());
			gameObjects.get("ball").setXVel(50);
			((Player) gameObjects.get("player1")).setPowerLevel(0);
			((Player) gameObjects.get("player1")).setUsingPower(false);
		}
		if (whichPlayer == 2) {
			gameObjects.get("ball").setXPos(((Player) gameObjects.get("player2")).getXPos());
			gameObjects.get("ball").setYPos(((Player) gameObjects.get("player2")).getYPos());
			gameObjects.get("ball").setXVel(-50);
			((Player) gameObjects.get("player2")).setPowerLevel(0);
			((Player) gameObjects.get("player2")).setUsingPower(false);
		}
		SoundEffect.powerup.play();
	}

	public void setUsingPower(boolean usingPower) {
		if(this.hasPower() && usingPower) {
			this.usingPower = usingPower;
			SoundEffect.fireball.play();
		}
		this.usingPower = usingPower;
	}

	public boolean UsingPower() {
		return this.usingPower;
	}

	public int getScore() {
		return this.score;
	}

}
