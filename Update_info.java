package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_info extends JFrame implements ActionListener{
    String meter_no_pass;
    JButton update_Button,cancel_Button;
    JTextField customer_name_TextField,address_TextField,city_TextField,state_TextField,email_TextField,phone_TextField;

    Update_info(String meter_no_pass){
        super("Update Customer Window");

        this.meter_no_pass = meter_no_pass;

        setBounds(320,100,750,550);
        // getContentPane().setBackground(new Color(102, 179, 255));
        // setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 179, 255));
        add(panel);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(100,30,350,25);
        heading.setFont(new Font("serif",Font.BOLD,23));
        panel.add(heading);

        JLabel customer_name_Label = new JLabel("Customer Name");
        customer_name_Label.setFont(new Font("serif",Font.PLAIN,18));
        customer_name_Label.setBounds(50,100,120,15);
        panel.add(customer_name_Label);

        JTextField customer_name_TextField = new JTextField();
        customer_name_TextField.setBounds(250,100,150,20);
        panel.add(customer_name_TextField);

        JLabel meter_no_Label = new JLabel("Meter No.");
        meter_no_Label.setFont(new Font("serif",Font.PLAIN,18));
        meter_no_Label.setBounds(50,150,120,15);
        panel.add(meter_no_Label);

        JLabel meter_no_Text = new JLabel("");
        meter_no_Text.setBounds(250,150,150,20);
        panel.add(meter_no_Text);

        JLabel address_Label = new JLabel("Address");
        address_Label.setFont(new Font("serif",Font.PLAIN,18));
        address_Label.setBounds(50,200,120,15);
        panel.add(address_Label);

        JTextField address_TextField = new JTextField();
        address_TextField.setBounds(250,200,150,20);
        panel.add(address_TextField);

        JLabel city_Label = new JLabel("City");
        city_Label.setFont(new Font("serif",Font.PLAIN,18));
        city_Label.setBounds(50,250,120,20);
        panel.add(city_Label);

        JTextField city_TextField = new JTextField();
        city_TextField.setBounds(250,250,150,20);
        panel.add(city_TextField);

        JLabel state_Label = new JLabel("State");
        state_Label.setFont(new Font("serif",Font.PLAIN,18));
        state_Label.setBounds(50,300,120,15);
        panel.add(state_Label);

        JTextField state_TextField = new JTextField();
        state_TextField.setBounds(250,300,150,20);
        panel.add(state_TextField);

        JLabel email_Label = new JLabel("Email");
        email_Label.setFont(new Font("serif",Font.PLAIN,18));
        email_Label.setBounds(50,350,120,15);
        panel.add(email_Label);

        JTextField email_TextField = new JTextField();
        email_TextField.setBounds(250,350,150,20);
        panel.add(email_TextField);

        JLabel phone_Label = new JLabel("Pnone No.");
        phone_Label.setFont(new Font("serif",Font.PLAIN,18));
        phone_Label.setBounds(50,400,120,15);
        panel.add(phone_Label);

        JTextField phone_TextField = new JTextField();
        phone_TextField.setBounds(250,400,150,20);
        panel.add(phone_TextField);

        ImageIcon update_image = new ImageIcon(ClassLoader.getSystemResource("icon/update_info_image.jpg"));
        Image update_One = update_image.getImage().getScaledInstance(300, 550,Image.SCALE_DEFAULT);
        ImageIcon update_image2 = new ImageIcon(update_One);
        JLabel imagLabel = new JLabel(update_image2);
        imagLabel.setBounds(435,0,300,550);
        panel.add(imagLabel);

        JButton update_Button = new JButton("Update");
        update_Button.setFont(new Font("serif",Font.PLAIN,18));
        update_Button.setBounds(130,455,100,25);
        update_Button.setBackground(new Color(138, 138, 92));
        update_Button.addActionListener(this);
        panel.add(update_Button);

        JButton cancel_Button = new JButton("Cancel");
        cancel_Button.setFont(new Font("serif",Font.PLAIN,18));
        cancel_Button.setBounds(270,455,100,25);
        cancel_Button.setBackground(new Color(138, 138, 92));
        cancel_Button.addActionListener(this);
        panel.add(cancel_Button);

        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer WHERE METER_NO = '"+meter_no_pass+"'");
            while (resultSet.next()) {
                customer_name_TextField.setText(resultSet.getString("NAME"));
                meter_no_Text.setText(resultSet.getString("METER_NO"));
                address_TextField.setText(resultSet.getString("ADDRESS"));
                city_TextField.setText(resultSet.getString("CITY"));
                state_TextField.setText(resultSet.getString("STATE"));
                email_TextField.setText(resultSet.getString("EMAIL"));
                phone_TextField.setText(resultSet.getString("PHONE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update_Button) {
            String S_name = customer_name_TextField.getText();
            String S_address = address_TextField.getText();
            String S_city = city_TextField.getText();
            String S_state = state_TextField.getText();
            String S_email = email_TextField.getText();
            String S_phone = phone_TextField.getText();

            try {
                database d1 = new database();
                d1.statement.executeUpdate("UPDATE New_Customer SET NAME = '"+S_name+"',ADDRESS = '"+S_address+"',CITY = '"+S_city+"',STATE = '"+S_state+"',EMAIL = '"+S_email+"',PHONE = '"+S_phone+"' WHERE METER_NO = '"+meter_no_pass+"' LIMIT 1");
                JOptionPane.showMessageDialog(null , "Information Updated Successfully");
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Update_info("");
    }
    
}
