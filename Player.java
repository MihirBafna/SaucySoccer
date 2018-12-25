import javax.swing.ImageIcon;
public class Player extends GameObject{

	public Player(ImageIcon image, int x, int y, int size){
		super(image,x,y,size);
	}

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

		if(jump && getYPos() >= ground-30){				//if the player is jumping
			setYVel(-10);
			jump = false;
		}
		if(getXVel()>10){
			setXVel(10);
		}
		if (getXVel() < -10) {
			setXVel(-10);
		}
		if(getXVel()>0){								// if velocity is positive or negative direction
			changeXVel(-0.15);							// subtracting energy loss due to friction respectively
		}else if(getXVel()<0){
			changeXVel(0.15);
		}
		// if(player.intersects(ball))
		
	}

	public void updatePos(){
		changeYPos((int)(getYVel()*dt));
		changeXPos((int)(getXVel()*dt));
	}


}