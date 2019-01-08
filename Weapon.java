import javax.swing.ImageIcon;

public class Weapon extends GameObject{
	
	private double radius;
	private double orthoX;
	private double orthoY;
	private double newDeltaX;
	private double newDeltaY;
	private double deltaY;
	private double deltaX;
	private double newLength;
	private GameObject attached;
	private int whichPlayer;
	private double aX;
	private double aY;
	private double d= 0;
	private boolean weaponSwing = false;
	
	public Weapon(ImageIcon img, int x, int y, int size, String key,int whichPlayer) {
		super(img, x, y, size, key);
		this.whichPlayer = whichPlayer;
	}
	
	public void updatePos() {
		rotations++;
		d+=2.5;
		deltaX = getXPos()-(attached.getXPos() + attached.getSize()/2);
		deltaY = getYPos()-(attached.getYPos() + attached.getSize()/2);
		//radius = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
		radius = attached.getSize()/2;
		orthoX = -deltaY*d/radius;
		orthoY = deltaX*d/radius;
		newDeltaX = deltaX+orthoX; newDeltaY = deltaY+orthoY;
		newLength = Math.sqrt(newDeltaX*newDeltaX+newDeltaY*newDeltaY);
		aX = attached.getXPos()+newDeltaX*radius/newLength; aY = attached.getYPos()+newDeltaY*radius/newLength;
		setXPos((int) aX);
		setYPos((int) aY);
		if(rotations<40) {
		rotate(0.05*rotations);
		}
	}

	public void events() {
		if(weaponSwing==false){
			resetPos();
			rotate(-0.05*rotations);
			rotations=0;
		}else if(weaponSwing == true) {
			updatePos();
		}
	}

	public void init() {
		gameObjects.put(id, this);
		if (whichPlayer == 1) {
			attached = gameObjects.get("player1");
		} else if (whichPlayer == 2) {
			attached = gameObjects.get("player2");
		}
	}
	
	public void setWeaponSwing(boolean a) {
		weaponSwing = a;
	}

	public boolean getWeaponSwing() {
		return weaponSwing;
	}
	
	public void resetPos() {
		rotate(-30);
		setXPos(attached.getXPos());
		setYPos(attached.getYPos()-800);
	}

	@Override
	public void resetPosition() {
		// TODO Auto-generated method stub
		
	}

}
