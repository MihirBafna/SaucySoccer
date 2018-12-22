// import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Ball extends GameObject{
    public int screenwidth = 1000;
    public int screenheight = 600;
    private int size = 21;

    public Ball(ImageIcon img){
        super(img);
        xPos = screenwidth/2-size/2;
        yPos = 50-size/2;
        super.getLabel().setBounds(xPos, yPos, size, size);
    }

    public int getSize(){
        return this.size;
    }

    public void update(){
        yPos += (0.5)*(g)*dt*dt + yVel*dt;
        yVel += g * dt;
        this.t += dt;
        setYPos(yPos);
        setYVel(yVel);
    }


}