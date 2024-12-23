package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{
    JTextField user_TextField,password_TextField;
    Choice login_choice;
    JButton login_Button,cancel_Button,signup_Button;

    Login(){
        // set the tittle name of frame

        super("Login Window");

        // set background color

        getContentPane().setBackground(new Color(102, 179, 255));

        // set the label and textfield in frame

        JLabel username_Label = new JLabel("Username");
        username_Label.setBounds(300,40,100,20);
        add(username_Label);

        user_TextField = new JTextField();
        user_TextField.setBounds(400,40,150,20);
        add(user_TextField);

        JLabel password_Label = new JLabel("Password");
        password_Label.setBounds(300,80,100,20);
        add(password_Label);

        password_TextField = new JTextField();
        password_TextField.setBounds(400,80,150,20);
        add(password_TextField);
        
        JLabel login_Label = new JLabel("Login in as");
        login_Label.setBounds(300,120,100,20);
        add(login_Label);

        login_choice = new Choice();
        login_choice.add("Admin");
        login_choice.add("Customer");
        login_choice.setBounds(400,120,150,20);
        add(login_choice);

        // set the location of buttons

        login_Button = new JButton("Login");
        login_Button.setBounds(300,160,100,20);
        login_Button.setForeground(Color.white);
        login_Button.setBackground(new Color(138, 138, 92));
        login_Button.addActionListener(this);
        add(login_Button);

        cancel_Button = new JButton("Cancel");
        cancel_Button.setBounds(450,160,100,20);
        cancel_Button.setForeground(Color.white);
        cancel_Button.setBackground(new Color(138, 138, 92));
        cancel_Button.addActionListener(this);
        add(cancel_Button);

        signup_Button = new JButton("Signup");
        signup_Button.setBounds(370,200,100,20);
        signup_Button.setForeground(Color.white);
        signup_Button.setBackground(new Color(138, 138, 92));
        signup_Button.addActionListener(this);
        add(signup_Button);

        // set the image in frame

        ImageIcon login_image = new ImageIcon(ClassLoader.getSystemResource("icon/login_image.jpg"));
        Image login_One = login_image.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon login_image2 = new ImageIcon(login_One);
        JLabel imagLabel = new JLabel(login_image2);
        imagLabel.setBounds(5,5,250,250);
        add(imagLabel);

        // set the loaction and size of frame
        setSize(640, 300);
        setLocation(430, 200);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Login();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login_Button) {
            System.out.println("You clicked in login button!");
            String S_username = user_TextField.getText();
            String S_password = password_TextField.getText();
            String S_choice = login_choice.getSelectedItem();

            try {
                database d1 = new database();
                String S_meter_no = null;
                if (S_choice.equals("Admin")) {
                    String query = "select * from Sign_up_admin where USER_NAME = '"+S_username+"'and PASSWORD = '"+S_password+"' and ACCOUNT = '"+S_choice+"'";
                    ResultSet result_set = d1.statement.executeQuery(query);
                    while (result_set.next()) {

                        setVisible(false);
                        JOptionPane.showMessageDialog(null,"Login Successfully!");
                        new main_class(S_choice,S_meter_no);                    
                        
                    }
                }else{
                    String query = "select * from Sign_up_customer where USER_NAME = '"+S_username+"'and PASSWORD = '"+S_password+"' and ACCOUNT = '"+S_choice+"'";
                    ResultSet result_set = d1.statement.executeQuery(query);
                    while (result_set.next()) {
                        S_meter_no = result_set.getString("METER_NO");
                        setVisible(false);
                        JOptionPane.showMessageDialog(null,"Login Successfully!");
                        new main_class(S_choice,S_meter_no);
                    }
                }    
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if (e.getSource() == cancel_Button) {
            setVisible(false);
        }
        else if (e.getSource() == signup_Button) {
            setVisible(false);
            new SignUp();
        }
    }
}
