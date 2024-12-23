package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.*;

public class calculate_bill extends JFrame implements ActionListener{
    Choice meter_number_choice,month_choice;
    JTextField consumed_TextField;
    JButton submit_Button,cancel_Button;
    JLabel meter_number_Label,name_Label,name_TextField,address_Label,address_TextField,consumed_Label,month_Label;
    calculate_bill(){
        super("Calculate Bill Window");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 179, 255));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setFont(new Font("serif",Font.BOLD,23));
        heading.setBounds(80,20,450,40);
        panel.add(heading);

        meter_number_Label = new JLabel("Meter Number");
        meter_number_Label.setFont(new Font("serif",Font.PLAIN,18));
        meter_number_Label.setBounds(50,80,150,20);
        panel.add(meter_number_Label);

        meter_number_choice = new Choice();
        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer");
            while (resultSet.next()) {
                meter_number_choice.add(resultSet.getString("METER_NO"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        meter_number_choice.setBounds(220,80,150,20);
        panel.add(meter_number_choice);

        name_Label = new JLabel("Name");
        name_Label.setFont(new Font("serif",Font.PLAIN,18));
        name_Label.setBounds(50,130,150,20);
        panel.add(name_Label);

        name_TextField = new JLabel("");
        name_TextField.setBounds(220,130,150,20);
        panel.add(name_TextField);

        address_Label = new JLabel("Address");
        address_Label.setFont(new Font("serif",Font.PLAIN,18));
        address_Label.setBounds(50,180,150,20);
        panel.add(address_Label);

        address_TextField = new JLabel("");
        address_TextField.setBounds(220,180,150,20);
        panel.add(address_TextField);

        // this is used for default name and address
        
        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer WHERE METER_NO = '"+meter_number_choice.getSelectedItem()+"'");
            while (resultSet.next()) {
                name_TextField.setText(resultSet.getString("NAME"));
                address_TextField.setText(resultSet.getString("ADDRESS"));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        meter_number_choice.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database d1 = new database();
                    ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer WHERE METER_NO = '"+meter_number_choice.getSelectedItem()+"'");
                    while (resultSet.next()) {
                        name_TextField.setText(resultSet.getString("NAME"));
                        address_TextField.setText(resultSet.getString("ADDRESS"));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            
        });

        consumed_Label = new JLabel("Unit Consumed");
        consumed_Label.setFont(new Font("serif",Font.PLAIN,18));
        consumed_Label.setBounds(50,230,150,20);
        panel.add(consumed_Label);

        consumed_TextField = new JTextField();
        consumed_TextField.setBounds(220,230,150,20);
        panel.add(consumed_TextField);

        month_Label = new JLabel("Month");
        month_Label.setFont(new Font("serif",Font.PLAIN,18));
        month_Label.setBounds(50,280,150,20);
        panel.add(month_Label);

        month_choice = new Choice();
        month_choice.add("Januray");
        month_choice.add("Feburay");
        month_choice.add("March");
        month_choice.add("April");
        month_choice.add("May");
        month_choice.add("June");
        month_choice.add("July");
        month_choice.add("August");
        month_choice.add("September");
        month_choice.add("October");
        month_choice.add("November");
        month_choice.add("December");
        month_choice.setBounds(220,280,150,20);
        panel.add(month_choice);

        submit_Button = new JButton("Submit");
        submit_Button.setFont(new Font("serif",Font.PLAIN,18));
        submit_Button.setBackground(new Color(138, 138, 92));
        submit_Button.setBounds(100,340,100,25);
        submit_Button.addActionListener(this);
        panel.add(submit_Button);

        cancel_Button = new JButton("Cancel");
        cancel_Button.setFont(new Font("serif",Font.PLAIN,18));
        cancel_Button.setBackground(new Color(138, 138, 92));
        cancel_Button.setBounds(240,340,100,25);
        cancel_Button.addActionListener(this);
        panel.add(cancel_Button);

        ImageIcon bill_image = new ImageIcon(ClassLoader.getSystemResource("icon/calculate_bill_image.jpg"));
        Image bill_One = bill_image.getImage().getScaledInstance(300, 400,Image.SCALE_DEFAULT);
        ImageIcon bill_image2 = new ImageIcon(bill_One);
        JLabel imagLabel = new JLabel(bill_image2);
        imagLabel.setBounds(390,1,300,400);
        panel.add(imagLabel);

        setSize(700, 440);
        setLocation(400, 150);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit_Button) {
            String S_meter_no_choice = meter_number_choice.getSelectedItem();
            String S_consumed = consumed_TextField.getText();
            String S_month = month_choice.getSelectedItem();

            int total_bill = 0;
            int units = Integer.parseInt(S_consumed);
            try {
                database d1 = new database();
                ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM Tax");
                while (resultSet.next()) {
                    total_bill = units + Integer.parseInt(resultSet.getString("COST_PER_UNIT"));
                    total_bill += Integer.parseInt(resultSet.getString("METER_RENT"));
                    total_bill +=Integer.parseInt(resultSet.getString("SERVICE_CHARGE"));
                    total_bill +=Integer.parseInt(resultSet.getString("SERVICE_TAX"));
                    total_bill +=Integer.parseInt(resultSet.getString("SWACCH_BHARAT_TAX"));
                    total_bill +=Integer.parseInt(resultSet.getString("FIXED_TAX"));
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
            try {
                database d1 = new database();
                d1.statement.executeUpdate("INSERT INTO Bill (METER_NO,MONTH,UNIT,TOTAL_BILL,STATUS) VALUES ('"+S_meter_no_choice+"','"+S_month+"','"+S_consumed+"','"+total_bill+"','Not Paid')");
                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully" );
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new calculate_bill();
    }
    
}
