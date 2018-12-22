import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Field{
    public int screenwidth = 1000;
    public int screenheight = 600;
    private JLabel field; 

    public Field(ImageIcon img){
        field = new JLabel(img);
        field.setBounds(0,0,screenwidth,screenheight);
    }

    public JLabel getLabel(){
        return this.field;
    }


}