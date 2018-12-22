import javax.swing.ImageIcon;

public class Player extends GameObject{
	private int size = 50;

	public Player(ImageIcon image, int x, int y){
		super(image);
		xPos = x;
		yPos = y;
		super.getLabel().setBounds(xPos,yPos,size,size);
	}

	public int getSize(){
		return this.size;
	}

	public void update(){
		
	}


}