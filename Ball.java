import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;

public class Ball extends GameObject{
    public Ball(ImageIcon img, int x, int y, int size){
        super(img,x,y,size);
        bColObject = new Circle((double) x+size/2, (double) y+size/2, (double) size/2);
    }

    public void events(){
        if (getYPos() <= ground) changeYVel(g*dt);
        if (getYPos() + getYVel() >= ground) {
            if (Math.abs((int) getYVel()) < minVel) {
                setYVel(0);
            } else {
                setYVel((getYVel() * (-friction)));
            }
            setYPos(ground);
        }
    }

    public void updatePos(){
        changeYPos((int)(getYVel()*dt));
        bColObject.setCenterX(getXPos() + getSize() / 2);
        bColObject.setCenterY(getYPos()+getSize()/2);
    }

}