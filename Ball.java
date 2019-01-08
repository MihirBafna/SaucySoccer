import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;

public class Ball extends GameObject{
    private static int rotatecounter;

    public Ball(ImageIcon img, int x, int y, int size, String key){
        super(img,x,y,size,key);
    }

    public void init(){
        gameObjects.put(id, this);
        gameObjects.get(id).collisionArea = new Circle((double) getXPos() + getSize() / 2,
                (double) getYPos() + getSize() / 2, (double) getSize() / 2);
    }

    public void events(){
        Circle ball = gameObjects.get("ball").collisionArea;
        Circle player1 = gameObjects.get("player1").collisionArea;
        Circle player2 = gameObjects.get("player2").collisionArea;
        rotatecounter = (rotatecounter+1)%3;
        if(rotatecounter == 0){
            if (getXVel() < 0) {
                rotate(-12.0);
            }
            else if (getXVel() > 0) {
                rotate(12.0);
            }
        }
        if (getYPos() <= ground) changeYVel(gravity*dt);
        if (getYPos() + getYVel() >= ground) {
            if (Math.abs((int) getYVel()) < minVel) {
                setYVel(0);
            } else {
                setYVel((getYVel() * (-bouncefriction)));
            }
            setYPos(ground);
        }
        if(getYPos()>= ground && getXVel()>0){
            setXVel(getXVel()-slidefriction);
        }else if(getYPos()>= ground && getXVel()<0){
            setXVel(getXVel()+slidefriction);
        }
        if (getXPos() >= screenwidth - getSize()) {
            setXPos(screenwidth-getSize());
            setXVel(-getXVel());
        }
        if(getXPos() <= 0){
            setXPos(0);
            setXVel(-getXVel());
        }
        if(getXPos() >= 900 && getXPos() <= 960 && getYPos() >= 323){
        }else if(getXPos() >= 885 && getYPos() <= 325 && getYPos() >= 320){
            setYVel(-getYVel());
        }else if (getXPos() >= 885 && getYPos() <= 330 && getYPos() >= 325) {
            setYVel(-getYVel());
        }
        if (isCollision(ball, player1)) {
            if(player1.getCenterX()<= ball.getCenterX()){
                setXVel(10);
            }else if(player1.getCenterX()>=ball.getCenterX()){
                setXVel(-10);
            }
            if (gameObjects.get("player1").kick) {
                setYVel(-15);
            }
        }
        if (isCollision(ball, player2)) {
            if(player2.getCenterX()<=ball.getCenterX()){
                setXVel(10);
            }else if(player2.getCenterX()>=ball.getCenterX()){
                setXVel(-10);
            }
            if(gameObjects.get("player2").kick){
                setYVel(-15);
            }
        }
    }

    public void updatePos(){
        changeYPos((int)(getYVel()*dt));
        changeXPos((int)(getXVel()*dt));
        gameObjects.get(id).collisionArea.setCenterX(getXPos() + getSize() / 2);
        gameObjects.get(id).collisionArea.setCenterY(getYPos()+getSize()/2);
    }

}