import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class GameObject{
    protected JLabel object;
    protected int xPos;
    protected int yPos;
    protected double xVel;
    protected double yVel;
    protected double dt = 0.7;
    protected double t = 0;
    protected double g = 1.0;

    public GameObject(ImageIcon img){
        object = new JLabel(img);
    }

    //abstract methods
    public abstract int getSize();

    public abstract void update();

    // getters
    public double getGravity(){
        return this.g;
    }
    
    public double getTime(){
        return this.dt;
    }
    
    public JLabel getLabel() {
        return this.object;
    }

    public int getXPos(){
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public double getXVel(){
        return this.xVel;
    }

    public double getYVel() {
        return this.yVel;
    }

    // Setters 
    public void setXPos(int x) {
        this.xPos = x;
        object.setBounds(this.xPos,this.yPos,getSize(),getSize());
    }

    public void setYPos(int y) {
        this.yPos = y;
        object.setBounds(this.xPos, this.yPos, getSize(), getSize());

    }

    public void changeXPos(int dx) {
        this.xPos += dx;
        object.setBounds(this.xPos, this.yPos, getSize(), getSize());
    }

    public void changeYPos(int dy) {
        this.yPos += dy;
        object.setBounds(this.xPos, this.yPos, getSize(), getSize());

    }

    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    public void setYVel(double yVel) {
        this.yVel = yVel;
    }
    
}