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
		if(jump){
			setYVel(-10);
			jump = false;
			System.out.println(getYVel());
		}

	}

	public void updatePos(){
		changeYPos((int)(getYVel()*dt));
	}


}