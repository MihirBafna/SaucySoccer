import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Weapon extends GameObject {

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
	private double d = 0;
	private boolean weaponSwing = false;
	private boolean up;
	private Rectangle collisionArea;

	public Weapon(ImageIcon img, int x, int y, int size, String key,int whichPlayer, boolean isUp) {
		super(img, x, y, size, key);
		this.whichPlayer = whichPlayer;
		this.up = isUp;
		collisionArea = new Rectangle(x, y, 7, size);

	}

	public void updatePos() {
		if (up) {
			if (whichPlayer == 1) {
				rotations++;
				d += 7.5;
				deltaX = getXPos() - (attached.getXPos() + attached.getSize() / 2);
				deltaY = getYPos() - (attached.getYPos() + attached.getSize() / 2);
				// radius = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
				radius = attached.getSize() / 2 + 5;
				orthoX = -deltaY * d / radius;
				orthoY = deltaX * d / radius;
				newDeltaX = deltaX + orthoX;
				newDeltaY = deltaY + orthoY;
				newLength = Math.sqrt(newDeltaX * newDeltaX + newDeltaY * newDeltaY);
				aX = attached.getXPos() + newDeltaX * radius / newLength;
				aY = attached.getYPos() + newDeltaY * radius / newLength;
				setXPos((int) aX);
				setYPos((int) aY);
				collisionArea.setLocation((int)aX, (int)aY);
				if (rotations < 35) {
					rotate(0.08 * rotations);
				}
			} else if (whichPlayer == 2) {
				rotations++;
				d -= 7.5;
				deltaX = getXPos() - (attached.getXPos() + attached.getSize() / 2);
				deltaY = getYPos() + (attached.getYPos() + attached.getSize() / 2);
				// radius = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
				radius = attached.getSize() / 2 + 10;
				orthoX = deltaY * d / radius;
				orthoY = deltaX * d / radius;
				newDeltaX = deltaX + orthoX;
				newDeltaY = -deltaY + orthoY;
				newLength = Math.sqrt(newDeltaX * newDeltaX + newDeltaY * newDeltaY);
				aX = attached.getXPos() + newDeltaX * radius / newLength;
				aY = attached.getYPos() + newDeltaY * radius / newLength;
				setXPos((int) aX);
				setYPos((int) aY);
				if (rotations < 35) {
					rotate(-0.08 * rotations);
				}
			}
		}
		if (!up) {
			if(whichPlayer==1) {
				rotations++;
				d -= 7.5;
				deltaX = getXPos() - (attached.getXPos() + attached.getSize() / 2);
				deltaY = getYPos() + (attached.getYPos() + attached.getSize() / 2);
				// radius = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
				radius = attached.getSize() / 2 + 15;
				orthoX = -deltaY * d / radius;
				orthoY = deltaX * d / radius;
				newDeltaX = deltaX + orthoX;
				newDeltaY = deltaY + orthoY;
				newLength = Math.sqrt(newDeltaX * newDeltaX + newDeltaY * newDeltaY);
				aX = attached.getXPos() + newDeltaX * radius / newLength;
				aY = attached.getYPos() + newDeltaY * radius / newLength;
				setXPos((int) aX);
				setYPos((int) aY);
				if (rotations < 32) {
					rotate(-0.08 * rotations);
				}
			}else if(whichPlayer==2) {
				rotations++;
				d += 7.5;
				deltaX = getXPos() - (attached.getXPos() + attached.getSize() / 2);
				deltaY = getYPos() + (attached.getYPos() + attached.getSize() / 2);
				// radius = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
				radius = attached.getSize() / 2 + 15;
				orthoX = -deltaY * d / radius;
				orthoY = deltaX * d / radius;
				newDeltaX = deltaX + orthoX;
				newDeltaY = deltaY + orthoY;
				newLength = Math.sqrt(newDeltaX * newDeltaX + newDeltaY * newDeltaY);
				aX = attached.getXPos() + newDeltaX * radius / newLength;
				aY = attached.getYPos() + newDeltaY * radius / newLength;
				setXPos((int) aX);
				setYPos((int) aY+10);
				if (rotations < 32) {
					rotate(0.08 * rotations);
				}
			}
		}
	}

	public void events() {
		if(weaponSwing==false){
			resetPos();
			if (whichPlayer == 1) {
				if (up) {
					rotate(-0.05 * rotations);
				} else {
					rotate(0.05 * rotations);
				}
				rotations = 0;
			} else if (whichPlayer == 2) {
				if (!up) {
					rotate(-0.05 * rotations);
				} else {
					rotate(0.05 * rotations);
				}
				rotations = 0;
			}
		}else if(weaponSwing == true) {
			if(rotations==0) {
				setXPos(attached.getXPos());
				setYPos(attached.getYPos()-25);
			}
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

	public void rotateCollisionArea(double theta){
		rotations++;
		Graphics2D g2d = (Graphics2D)g;
		g2d.rotate(theta);
	}

	public void setWeaponSwing(boolean a) {
		weaponSwing = a;
	}

	public boolean getWeaponSwing() {
		return weaponSwing;
	}

	public void resetPos() {
		// rotate(-30);
		d = 0;
		setXPos(attached.getXPos());
		setYPos(attached.getYPos()-800);
	}


}