import javax.swing.ImageIcon;

public class Player extends GameObject{

	public Player(ImageIcon image, int x, int y, int size){
		super(image,x,y,size);
	}

	public void events(){
		if(getYPos() <= playerground) changeYVel(g*dt);
		if(getYPos() >= playerground){
			setYPos(playerground);
			setYVel(0);	
		}
		if(jump && getYPos() >= ground-30){
			setYVel(-10);
			jump = false;
		}
		if(getXVel()>0){
			slide = false;
			changeXVel(-0.35);
		}
		if(getXVel()<0){
			slide = false;
			changeXVel(0.35);
		}

	}

	public void updatePos(){
		changeYPos((int)(getYVel()*dt));
		changeXPos((int)(getXVel()*dt));
	}


}