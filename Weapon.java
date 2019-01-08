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
	private double aX;
	private double aY;
	private double d= 0;
	
	public Weapon(ImageIcon img, int x, int y, int size, String key, GameObject attached) {
		super(img, x, y, size, key);
		this.attached = attached;
	}

	
	
	public void updatePos() {
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
		rotate(3*getRotations());
	}

	public void events() {
		
	}

	public void init() {
		gameObjects.put(id, this);
	}





	
}
