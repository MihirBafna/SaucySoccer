import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;
public class Player extends GameObject{

	public Player(ImageIcon image, int x, int y, int size, String key){
		super(image,x,y,size, key);
	}

	public void init() {
		gameObjects.put(id, this);
		gameObjects.get(id).collisionArea = new Circle((double) getXPos() + getSize() / 2,
				(double) getYPos() + getSize() / 2, (double) getSize() / 2);
	}

	// events method contains a series of if statements that affect the players velocity and position //
	public void events(){
		if(getYPos() <= playerground) changeYVel(gravity*dt); //above the ground
		if (getYPos() >= playerground) {				//on the ground
			setYPos(playerground);
			setYVel(0);
		}
		if(getXPos() >= screenwidth - getSize()){		//past right wall
			setXPos(screenwidth - getSize());
		}
		if(getXPos() <= 0){								//past left wall
			setXPos(0);
		}
		if(jump && getYPos()>= ground - 30){			//if the player is jumping
			setYVel(-jumpspeed);
			jump = false;
		}
		if (leftSlide) {
			setXVel(-10);
		} else if (rightSlide) {
			setXVel(10);
		}else{
			setXVel(0);
		}
		if(isCollision(gameObjects.get("player1").collisionArea,gameObjects.get("ball").collisionArea)){
		}
		if (isCollision(gameObjects.get("player2").collisionArea, gameObjects.get("ball").collisionArea)) {
		}		
		if (isCollision(gameObjects.get("player1").collisionArea, gameObjects.get("player2").collisionArea)) {
		}
	}

	// updatePos method updates the position of the player based on changes made to the x and y velocities // 
	public void updatePos(){
		changeYPos((int)(getYVel()*dt));
		changeXPos((int)(getXVel()*dt));
		gameObjects.get(id).collisionArea.setCenterX(getXPos()+getSize()/2);
		gameObjects.get(id).collisionArea.setCenterY(getYPos()+getSize()/2);
	}


}