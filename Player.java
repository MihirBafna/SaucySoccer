import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;
public class Player extends GameObject{

	public Player(ImageIcon image, int x, int y, int size){
		super(image,x,y,size);
		pColObject = new Circle((double) x+size/2, (double) y+size/2, (double) size/2);
	}

	// events method contains a series of if statements that affect the players velocity and position //
	public void events(){
		if(getYPos() <= playerground) changeYVel(g*dt); //above the ground
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
		if(leftSlide){
			setXVel(-playerspeed);
		}
		else if(rightSlide) {
			setXVel(playerspeed);
		}else{
			setXVel(0);
		}
		if(intersects()){
			System.out.println("collided");
		}
		
	}

	// updatePos method updates the position of the player based on changes made to the x and y velocities // 
	public void updatePos(){
		changeYPos((int)(getYVel()*dt));
		changeXPos((int)(getXVel()*dt));
		pColObject.setCenterX(getXPos()+getSize()/2);
		pColObject.setCenterY(getYPos() + getSize() / 2);
		// System.out.println(pColObject.toString());
	}


}