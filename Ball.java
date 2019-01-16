import javax.swing.ImageIcon;
import javafx.scene.shape.Circle;

public class Ball extends GameObject{
    private static int rotatecounter;
    private Circle ball;
    private Circle player1;
    private Circle player2;

    public Ball(ImageIcon img, int x, int y, int size, String key){
        super(img,x,y,size,key);
    }

    public void init(){
        gameObjects.put(id, this);
        gameObjects.get(id).collisionArea = new Circle((double) getXPos() + getSize() / 2,
                (double) getYPos() + getSize() / 2, (double) getSize() / 2);
    }

    public void events(){
        ball = gameObjects.get("ball").collisionArea;
        player1 = gameObjects.get("player1").collisionArea;
        player2 = gameObjects.get("player2").collisionArea;
        rotatecounter = (rotatecounter+1)%3;
        if(rotatecounter == 0){
        	rotate(getXVel() * 2);
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
        if(rectangleCircleCollision(ball, goalCrossBar1)|| rectangleCircleCollision(ball, goalCrossBar2)){
            if (getYPos() >= goalCrossBar1.getY()) {
                setYPos((int) (goalCrossBar1.getY()+goalCrossBar1.getHeight()));
            }
            setYVel(-(getYVel()+3));
        }
        if (isCircleCollision(ball, player1)) {
            if(player1.getCenterX()<= ball.getCenterX()){
                setXVel(12);
            }else if(player1.getCenterX()>=ball.getCenterX()){
                setXVel(-12);
            }
            if (player1.getCenterY() <= ball.getCenterY() && Math.abs(gameObjects.get("player1").getYVel()) > 0) {
                setYVel(10);
            } else if (player1.getCenterY() >= ball.getCenterY()
                    && Math.abs(gameObjects.get("player1").getYVel()) > 0) {
                setYVel(-10);
            }
            if (gameObjects.get("player1").kick) {
                setYVel(-12);
            }
        }
        if (isCircleCollision(ball, player2)) {
            if(player2.getCenterX()<=ball.getCenterX()){
                setXVel(10);
            }else if(player2.getCenterX()>=ball.getCenterX()){
                setXVel(-10);
            }
            if(player2.getCenterY()<=ball.getCenterY()&& Math.abs(gameObjects.get("player2").getYVel())>0){
                setYVel(10);
            }else if(player2.getCenterY() >= ball.getCenterY() && Math.abs(gameObjects.get("player2").getYVel()) > 0) {
                setYVel(-10);
            }
            if(gameObjects.get("player2").kick){
                setYVel(-12);
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