package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
// import java.util.EventListener;

public class SignUp extends JFrame implements ActionListener{

    JTextField emp_id_TextField,userName_TextField,contact_TextField,password_TextField,meter_no_TextField,name_TextField;
    Choice create_choice;
    JButton create_Button,back_Button;
    

    SignUp(){
        // set the tittle name of frame

        super("SignUp Window");

        getContentPane().setBackground(new Color(102, 179, 255));

        // set the label and textfield in frame

        JLabel create_Label = new JLabel("Create account as");
        create_Label.setBounds(40,50,125,20);
        add(create_Label);

        create_choice = new Choice();
        create_choice.add("Admin");
        create_choice.add("Customer");
        create_choice.setBounds(190,50,150,20);
        add(create_choice);

        JLabel meter_no_Label = new JLabel("Meter No");
        meter_no_Label.setBounds(40,90,125,20);
        meter_no_Label.setVisible(false);
        add(meter_no_Label);

        meter_no_TextField = new JTextField();
        meter_no_TextField.setBounds(190,90,150,20);
        meter_no_TextField.setVisible(false);
        add(meter_no_TextField);

        JLabel emp_id_Label = new JLabel("Employee Id");
        emp_id_Label.setBounds(40,90,125,20);
        emp_id_Label.setVisible(true);
        add(emp_id_Label);

        emp_id_TextField = new JTextField();
        emp_id_TextField.setBounds(190,90,150,20);
        emp_id_TextField.setVisible(true);
        add(emp_id_TextField);

        JLabel userName_Label = new JLabel("Username");
        userName_Label.setBounds(40,130,125,20);
        add(userName_Label);

        userName_TextField = new JTextField();
        userName_TextField.setBounds(190,130,150,20);
        add(userName_TextField);

        JLabel name_Label = new JLabel("Name");
        name_Label.setBounds(40,170,125,20);
        add(name_Label);

        name_TextField = new JTextField();
        name_TextField.setBounds(190,170,150,20);
        add(name_TextField);

        JLabel contact_Label = new JLabel("Contact");
        contact_Label.setBounds(40,210,125,20);
        add(contact_Label);

        contact_TextField = new JTextField();
        contact_TextField.setBounds(190,210,150,20);
        add(contact_TextField);

        JLabel password_Label = new JLabel("Password");
        password_Label.setBounds(40,250,125,20);
        add(password_Label);

        password_TextField = new JTextField();
        password_TextField.setBounds(190,250,150,20);
        add(password_TextField);

        // set the location of buttons

        create_Button = new JButton("Create");
        create_Button.setBounds(50,300,100,20);
        create_Button.setForeground(Color.white);
        create_Button.setBackground(new Color(138, 138, 92));
        create_Button.addActionListener(this);
        add(create_Button);

        back_Button = new JButton("Back");
        back_Button.setBounds(200,300,100,20);
        back_Button.setForeground(Color.white);
        back_Button.setBackground(new Color(138, 138, 92));
        back_Button.addActionListener(this);
        add(back_Button);

        // set the image in frame

        ImageIcon signup_image = new ImageIcon(ClassLoader.getSystemResource("icon/signup_image.png"));
        Image signup_One = signup_image.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon signup_image2 = new ImageIcon(signup_One);
        JLabel imagLabel = new JLabel(signup_image2);
        imagLabel.setBounds(370,30,250,250);
        add(imagLabel);

        // create a function for create choice

        create_choice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                String user = create_choice.getSelectedItem();
                if (user.equals("Customer")) {
                    meter_no_Label.setVisible(true);
                    meter_no_TextField.setVisible(true);
                    emp_id_Label.setVisible(false);
                    emp_id_TextField.setVisible(false);
                }
                else{
                    meter_no_Label.setVisible(false);
                    meter_no_TextField.setVisible(false);
                    emp_id_Label.setVisible(true);
                    emp_id_TextField.setVisible(true);
                }
            }
        });

        setSize(640, 400);
        setLocation(430, 200);
        setLayout(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new SignUp();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create_Button) {
            System.out.println("You clicked on a create button!");
            String S_choice = create_choice.getSelectedItem();
            String S_meter_no = meter_no_TextField.getText();
            String S_emp_id = emp_id_TextField.getText();
            String S_username = userName_TextField.getText();
            String S_name = name_TextField.getText();
            String S_contact = contact_TextField.getText();
            String S_password = password_TextField.getText();

            try {
                database d1 = new database();
                
                if (S_choice.equals("Admin")) {
                    String query_admin = "INSERT INTO Sign_up_admin (ACCOUNT,EMPLOYEE_ID,USER_NAME,NAME,CONTACT,PASSWORD) VALUES('"+S_choice+"','"+S_emp_id+"','"+S_username+"','"+S_name+"','"+S_contact+"','"+S_password+"');";
                    d1.statement.executeUpdate(query_admin);
                }else{
                   String query_customer = "INSERT INTO Sign_up_customer (ACCOUNT,METER_NO,USER_NAME,NAME,CONTACT,PASSWORD) VALUES('"+S_choice+"','"+S_meter_no+"','"+S_username+"','"+S_name+"','"+S_contact+"','"+S_password+"');";
                   d1.statement.executeUpdate(query_customer);
                }
               
                

                JOptionPane.showMessageDialog(null,"Account Created Successfully!");
                setVisible(false);
                new Login();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == back_Button){
            setVisible(false);
            new Login();
        }
    }
}
