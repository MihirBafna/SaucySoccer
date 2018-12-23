import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class GameObject{
    protected JLabel object;
    private int xPos;
    private int yPos;
    private double xVel;
    private double yVel;
    private int size;
    protected double dt = 0.7;
    protected double g = 1.0;
    protected int playerground = 430;
    protected int ground = 450;
    protected int minVel = 3;
    protected double friction = 0.7;
    protected boolean jump = false;
    protected boolean slide = false;

    public GameObject(ImageIcon img, int x, int y, int size){
        object = new JLabel(img);
        this.size = size;
        this.xPos = x;
        this.yPos = y;
        this.getLabel().setBounds(xPos, yPos, size, size);
    }

    //abstract methods
    public abstract void updatePos();

    public abstract void events();

    // getters
    public int getSize(){
        return this.size;
    }

    public double getGravity(){
        return this.g;
    }
    
    public double getDT(){
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
    public void setJump(boolean x){
        this.jump = x;
    }
    
    public void setSlide(boolean x) {
        this.slide = x;
    }

    public void setSize(int size){
        this.size = size;
    }

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

    public void changeXVel(double dxVel) {
        this.xVel += dxVel;
    }

    public void changeYVel(double dyVel) {
        this.yVel += dyVel;

    }
    
}