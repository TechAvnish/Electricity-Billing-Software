package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_info extends JFrame implements ActionListener{
    String S_meter_no_pass;
    JButton cancel_Button;
    view_info(String S_meter_no_pass){
        super("Customer Information Window");
        
        this.S_meter_no_pass = S_meter_no_pass;

        setBounds(300,80,850,550);
        getContentPane().setBackground(new Color(102, 179, 255));
        // setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 179, 255));
        add(panel);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,30,300,25);
        heading.setFont(new Font("serif",Font.BOLD,25));
        panel.add(heading);

        JLabel customer_name_Label = new JLabel("Name");
        customer_name_Label.setFont(new Font("serif",Font.PLAIN,20));
        customer_name_Label.setBounds(80,100,120,25);
        panel.add(customer_name_Label);

        JLabel customer_name_Text = new JLabel("");
        customer_name_Text.setBounds(240,100,150,25);
        customer_name_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(customer_name_Text);

        JLabel meter_number_Label = new JLabel("Meter Number");
        meter_number_Label.setFont(new Font("serif",Font.PLAIN,20));
        meter_number_Label.setBounds(80,150,120,25);
        panel.add(meter_number_Label);

        JLabel meter_number_Text = new JLabel("");
        meter_number_Text.setBounds(240,150,150,25);
        meter_number_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(meter_number_Text);

        JLabel address_Label = new JLabel("Adresss");
        address_Label.setFont(new Font("serif",Font.PLAIN,20));
        address_Label.setBounds(80,200,120,25);
        panel.add(address_Label);

        JLabel address_Text = new JLabel("");
        address_Text.setBounds(240,200,150,25);
        address_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(address_Text);

        JLabel city_Label = new JLabel("City");
        city_Label.setFont(new Font("serif",Font.PLAIN,20));
        city_Label.setBounds(80,250,120,25);
        panel.add(city_Label);

        JLabel city_Text = new JLabel("");
        city_Text.setBounds(240,250,150,25);
        city_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(city_Text);

        JLabel state_Label = new JLabel("State");
        state_Label.setFont(new Font("serif",Font.PLAIN,20));
        state_Label.setBounds(500,100,120,25);
        panel.add(state_Label);

        JLabel state_Text = new JLabel("");
        state_Text.setBounds(650,100,150,25);
        state_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(state_Text);

        JLabel email_Label = new JLabel("Email");
        email_Label.setFont(new Font("serif",Font.PLAIN,20));
        email_Label.setBounds(500,150,120,25);
        panel.add(email_Label);

        JLabel email_Text = new JLabel("");
        email_Text.setBounds(650,150,150,25);
        email_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(email_Text);

        JLabel phone_Label = new JLabel("Phone");
        phone_Label.setFont(new Font("serif",Font.PLAIN,20));
        phone_Label.setBounds(500,200,120,25);
        panel.add(phone_Label);

        JLabel phone_Text = new JLabel("");
        phone_Text.setBounds(650,200,150,25);
        phone_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(phone_Text);

        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer WHERE METER_NO = '"+S_meter_no_pass+"'");
            while (resultSet.next()) {
                customer_name_Text.setText(resultSet.getString("NAME"));
                meter_number_Text.setText(resultSet.getString("METER_NO"));
                address_Text.setText(resultSet.getString("ADDRESS"));
                city_Text.setText(resultSet.getString("CITY"));
                state_Text.setText(resultSet.getString("STATE"));
                email_Text.setText(resultSet.getString("EMAIL"));
                phone_Text.setText(resultSet.getString("PHONE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel_Button = new JButton("Cancel");
        cancel_Button.setFont(new Font("serif",Font.PLAIN,18));
        cancel_Button.setBounds(350,300,100,25);
        cancel_Button.setBackground(new Color(138, 138, 92));
        cancel_Button.addActionListener(this);
        panel.add(cancel_Button);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel_Button) {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new view_info("");
    }
   
}
