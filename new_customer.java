package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class new_customer extends JFrame implements ActionListener{
    JLabel heading,customer_name_Label,meter_no_Label,address_Label,city_Label,state_Label,email_Label,phone_Label,meter_no_TextField;

    JTextField customer_name_TextField,address_TextField,city_TextField,state_TextField,email_TextField,phone_TextField;

    JButton next_Button,cancel_Button;
    new_customer(){

        super("New Customer Window");

        

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 179, 255));
        add(panel);

        // set the image 
       
        ImageIcon customer_image = new ImageIcon(ClassLoader.getSystemResource("icon/new_customer_image.jpg"));
        Image customer_One = customer_image.getImage().getScaledInstance(300, 465,Image.SCALE_DEFAULT);
        ImageIcon customer_image2 = new ImageIcon(customer_One);
        JLabel imagLabel = new JLabel(customer_image2);
        imagLabel.setBounds(0,0,300,465);
        panel.add(imagLabel); 

        // set the heading of frame

        heading = new JLabel("New Customer");
        heading.setBounds(380,30,200,20);
        heading.setFont(new Font("serif",Font.BOLD,25));
        panel.add(heading);

        // set the label and textfield in the frame

        customer_name_Label = new JLabel("Customer Name");
        customer_name_Label.setFont(new Font("serif",Font.PLAIN,18));
        customer_name_Label.setBounds(320,90,120,15);
        panel.add(customer_name_Label);

        customer_name_TextField = new JTextField();
        customer_name_TextField.setBounds(500,90,150,20);
        panel.add(customer_name_TextField);

        meter_no_Label = new JLabel("Meter No.");
        meter_no_Label.setFont(new Font("serif",Font.PLAIN,18));
        meter_no_Label.setBounds(320,130,120,15);
        panel.add(meter_no_Label);

        meter_no_TextField = new JLabel();
        meter_no_TextField.setBounds(500,130,150,20);
        panel.add(meter_no_TextField);

        // set the random meter no 

        Random ran = new Random();
        Long number = ran.nextLong() % 10000;
        meter_no_TextField.setText(""+ Math.abs(number));

        address_Label = new JLabel("Address");
        address_Label.setFont(new Font("serif",Font.PLAIN,18));
        address_Label.setBounds(320,170,120,15);
        panel.add(address_Label);

        address_TextField = new JTextField();
        address_TextField.setBounds(500,170,150,20);
        panel.add(address_TextField);

        city_Label = new JLabel("City");
        city_Label.setFont(new Font("serif",Font.PLAIN,18));
        city_Label.setBounds(320,210,120,15);
        panel.add(city_Label);

        city_TextField = new JTextField();
        city_TextField.setBounds(500,210,150,20);
        panel.add(city_TextField);

        state_Label = new JLabel("State");
        state_Label.setFont(new Font("serif",Font.PLAIN,18));
        state_Label.setBounds(320,250,120,20);
        panel.add(state_Label);

        state_TextField = new JTextField();
        state_TextField.setBounds(500,250,150,20);
        panel.add(state_TextField);

        email_Label = new JLabel("Email");
        email_Label.setFont(new Font("serif",Font.PLAIN,18));
        email_Label.setBounds(320,290,120,15);
        panel.add(email_Label);

        email_TextField = new JTextField();
        email_TextField.setBounds(500,290,150,20);
        panel.add(email_TextField);

        phone_Label = new JLabel("Pnone No.");
        phone_Label.setFont(new Font("serif",Font.PLAIN,18));
        phone_Label.setBounds(320,330,120,15);
        panel.add(phone_Label);

        phone_TextField = new JTextField();
        phone_TextField.setBounds(500,330,150,20);
        panel.add(phone_TextField);

        next_Button = new JButton("Next");
        next_Button.setFont(new Font("serif",Font.PLAIN,18));
        next_Button.setBounds(380,400,100,25);
        next_Button.setBackground(new Color(138, 138, 92));
        next_Button.addActionListener(this);
        panel.add(next_Button);

        cancel_Button = new JButton("Cancel");
        cancel_Button.setFont(new Font("serif",Font.PLAIN,18));
        cancel_Button.setBounds(510,400,100,25);
        cancel_Button.setBackground(new Color(138, 138, 92));
        cancel_Button.addActionListener(this);
        panel.add(cancel_Button);

        setSize(750, 500);
        setLocation(350, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new new_customer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next_Button) {
            String S_new_name = customer_name_TextField.getText();
            String S_meter_no = meter_no_TextField.getText();
            String S_address = address_TextField.getText();
            String S_city = city_TextField.getText();
            String S_state = state_TextField.getText();
            String S_email = email_TextField.getText();
            String S_phone = phone_TextField.getText();

            try {
                String customer_query = "INSERT INTO New_Customer (NAME,METER_NO,ADDRESS,CITY,STATE,EMAIL,PHONE) VALUES ('"+S_new_name+"','"+S_meter_no+"','"+S_address+"','"+S_city+"','"+S_state+"','"+S_email+"','"+S_phone+"')";
                // excute sign_up query

                database d1 = new database();
                d1.statement.executeUpdate(customer_query);
                // sign_up query also

                JOptionPane.showMessageDialog(null, "Customer Details addedd Succesfully");
                setVisible(false);
                new meter_info(S_meter_no);
            } catch (Exception E) {
                E.printStackTrace();
            }
            
        }else{
            setVisible(false);
        }
    }
}
