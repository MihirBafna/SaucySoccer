import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;

public class Ball extends GameObject{
    public Ball(ImageIcon img, int x, int y, int size, String key){
        super(img,x,y,size,key);
    }

    public void init(){
        gameObjects.put(id, this);
        gameObjects.get(id).collisionArea = new Circle((double) getXPos() + getSize() / 2,
                (double) getYPos() + getSize() / 2, (double) getSize() / 2);
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
        gameObjects.get(id).collisionArea.setCenterX(getXPos() + getSize() / 2);
        gameObjects.get(id).collisionArea.setCenterY(getYPos()+getSize()/2);
    }

}