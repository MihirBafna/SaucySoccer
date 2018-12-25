import javax.swing.ImageIcon;
public class Ball extends GameObject{


    public Ball(ImageIcon img, int x, int y, int size){
        super(img,x,y,size);
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
        // if(ball.intersects(player))
    }

    public void updatePos(){
        changeYPos((int)(getYVel()*dt));
    }


}