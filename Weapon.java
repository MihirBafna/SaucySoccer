import javax.swing.ImageIcon;

public class Weapon extends GameObject{
	
	private double radius;
	private double orthoX;
	private double orthoY;
	private double newDeltaX;
	private double newDeltaY;
	private double oX;
	private double cX;
	private double oY;
	private double cY;
	private double deltaY;
	private double deltaX;
	private double newLength;
	private GameObject attached;
	private double aX;
	private double aY;
	private double angle=0;
	private double d= 0;
	
	public Weapon(ImageIcon img, int x, int y, int size, String key, GameObject attached) {
		super(img, x, y, size, key);
		this.setAttached(attached);
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
		System.out.println("x = " + aX);
		System.out.println("y = " + aY);
		
		
		/*angle = .8;
		angle = (angle ) * (Math.PI/180); // Convert to radians
		double pointx = getXPos();
		double pointy = getYPos();
		double centerx = attached.getXPos() + attached.getSize()/2;
		double centery = attached.getYPos() + attached.getSize()/2;
        double rotatedX = Math.cos(angle) * (pointx - centerx) - Math.sin(angle) * (pointy-centery) + centerx;
        double rotatedY = Math.sin(angle) * (pointx - centerx) + Math.cos(angle) * (pointy - centery) + centery;
        setXPos((int)rotatedX);
        setYPos((int) rotatedY);
        */

	}


	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getOrthoX() {
		return orthoX;
	}

	public void setOrthoX(double orthoX) {
		this.orthoX = orthoX;
	}

	public double getOrthoY() {
		return orthoY;
	}

	public void setOrthoY(double orthoY) {
		this.orthoY = orthoY;
	}

	public double getNewDeltaX() {
		return newDeltaX;
	}

	public void setNewDeltaX(double newDeltaX) {
		this.newDeltaX = newDeltaX;
	}

	public double getNewDeltaY() {
		return newDeltaY;
	}

	public void setNewDeltaY(double newDeltaY) {
		this.newDeltaY = newDeltaY;
	}

	public double getoX() {
		return oX;
	}

	public void setoX(double oX) {
		this.oX = oX;
	}

	public double getcX() {
		return cX;
	}

	public void setcX(double cX) {
		this.cX = cX;
	}

	public double getoY() {
		return oY;
	}

	public void setoY(double oY) {
		this.oY = oY;
	}

	public double getcY() {
		return cY;
	}

	public void setcY(double cY) {
		this.cY = cY;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getNewLength() {
		return newLength;
	}

	public void setNewLength(double newLength) {
		this.newLength = newLength;
	}

	public double getaX() {
		return aX;
	}

	public void setaX(double aX) {
		this.aX = aX;
	}

	public double getaY() {
		return aY;
	}

	public void setaY(double aY) {
		this.aY = aY;
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		gameObjects.put(id, this);
		
	}



	public GameObject getAttached() {
		return attached;
	}



	public void setAttached(GameObject attached) {
		this.attached = attached;
	}





	
}
