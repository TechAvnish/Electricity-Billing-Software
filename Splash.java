package electricity.billing.system;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame{
    
    Splash(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/instruct.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(600, 400,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imagLabel = new JLabel(imageIcon2);
        add(imagLabel);


        setSize(600, 400);
        setLocation(500, 200);
        setVisible(true);

        try {
            Thread.sleep(5000);
            setVisible(false);

            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}
