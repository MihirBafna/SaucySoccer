import javax.swing.ImageIcon;
public class Player extends GameObject{

	public Player(ImageIcon image, int x, int y, int size){
		super(image,x,y,size);
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
			setYVel(-playerspeed);
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
		// if(player.intersects(ball))
		
	}

	// updatePos method updates the position of the player based on changes made to the x and y velocities // 
	public void updatePos(){
		changeYPos((int)(getYVel()*dt));
		changeXPos((int)(getXVel()*dt));

	}


}