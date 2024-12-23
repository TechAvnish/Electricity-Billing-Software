package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;;

public class Pay_bill extends JFrame implements ActionListener{
    String meter_no_pass;
    JButton pay_Button, back_Button;
    Choice month_Choice;
    Pay_bill(String meter_no_pass){
        super("Pay Bill Window");

        this.meter_no_pass = meter_no_pass;

        setBounds(300,100,750,500);
        // setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 179, 255));
        add(panel);

        JLabel heading = new JLabel("Pay Bill");
        heading.setBounds(230,30,300,25);
        heading.setFont(new Font("serif",Font.BOLD,25));
        panel.add(heading);

        JLabel customer_name_Label = new JLabel("Name");
        customer_name_Label.setFont(new Font("serif",Font.PLAIN,20));
        customer_name_Label.setBounds(80,100,120,25);
        panel.add(customer_name_Label);

        JLabel customer_name_Text = new JLabel("");
        customer_name_Text.setBounds(250,100,150,25);
        customer_name_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(customer_name_Text);

        JLabel meter_number_Label = new JLabel("Meter Number");
        meter_number_Label.setFont(new Font("serif",Font.PLAIN,20));
        meter_number_Label.setBounds(80,150,120,25);
        panel.add(meter_number_Label);

        JLabel meter_number_Text = new JLabel("");
        meter_number_Text.setBounds(250,150,150,25);
        meter_number_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(meter_number_Text);

        JLabel month_Label = new JLabel("Month");
        month_Label.setFont(new Font("serif",Font.PLAIN,20));
        month_Label.setBounds(80,200,120,25);
        panel.add(month_Label);

        month_Choice = new Choice();
        month_Choice.add("January");
        month_Choice.add("Feburay");
        month_Choice.add("March");
        month_Choice.add("April");
        month_Choice.add("May");
        month_Choice.add("June");
        month_Choice.add("July");
        month_Choice.add("August");
        month_Choice.add("September");
        month_Choice.add("October");
        month_Choice.add("November");
        month_Choice.add("December");
        month_Choice.setBounds(250,200,150,25);
        // month_Choice.addItemListener(this);
        panel.add(month_Choice);

        JLabel unit_Label = new JLabel("Unit");
        unit_Label.setFont(new Font("serif",Font.PLAIN,20));
        unit_Label.setBounds(80,250,120,25);
        panel.add(unit_Label);

        JLabel unit_Text = new JLabel("");
        unit_Text.setBounds(250,250,150,25);
        unit_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(unit_Text);

        JLabel total_bill_Label = new JLabel("Total Bill");
        total_bill_Label.setFont(new Font("serif",Font.PLAIN,20));
        total_bill_Label.setBounds(80,300,120,25);
        panel.add(total_bill_Label);

        JLabel total_bill_Text = new JLabel("");
        total_bill_Text.setBounds(250,300,150,25);
        total_bill_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(total_bill_Text);

        JLabel status_Label = new JLabel("Status");
        status_Label.setFont(new Font("serif",Font.PLAIN,20));
        status_Label.setBounds(80,350,120,25);
        panel.add(status_Label);

        JLabel status_Text = new JLabel("");
        status_Text.setBounds(250,350,150,25);
        status_Text.setForeground(Color.red);
        status_Text.setFont(new Font("serif",Font.PLAIN,18));
        panel.add(status_Text);

        ImageIcon pay_image = new ImageIcon(ClassLoader.getSystemResource("icon/pay_bill_image.jpg"));
        Image pay_One = pay_image.getImage().getScaledInstance(320, 480,Image.SCALE_DEFAULT);
        ImageIcon pay_image2 = new ImageIcon(pay_One);
        JLabel imagLabel = new JLabel(pay_image2);
        imagLabel.setBounds(420,0,320,480);
        panel.add(imagLabel);

        pay_Button = new JButton("Pay");
        pay_Button.setFont(new Font("serif",Font.PLAIN,18));
        pay_Button.setBackground(new Color(138, 138, 92));
        pay_Button.setBounds(150,400,100,25);
        pay_Button.addActionListener(this);
        panel.add(pay_Button);

        back_Button = new JButton("Back");
        back_Button.setFont(new Font("serif",Font.PLAIN,18));
        back_Button.setBackground(new Color(138, 138, 92));
        back_Button.setBounds(300,400,100,25);
        back_Button.addActionListener(this);
        panel.add(back_Button);


        try {

            
            database d1 = new database();
            String query = "SELECT * FROM New_Customer WHERE METER_NO = '"+meter_no_pass+"'";
            ResultSet resultSet = d1.statement.executeQuery(query);
            while (resultSet.next()) {
                customer_name_Text.setText(resultSet.getString("NAME"));
                meter_number_Text.setText(meter_no_pass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        month_Choice.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                
                try {
                    database d1 = new database();
                    String query = "SELECT * FROM Bill WHERE METER_NO ='"+meter_no_pass+"' AND  MONTH = '"+month_Choice.getSelectedItem()+"'";
                    ResultSet resultSet = d1.statement.executeQuery(query);
                    while (resultSet.next()) {
                        unit_Text.setText(resultSet.getString("UNIT"));
                        total_bill_Text.setText(resultSet.getString("TOTAL_BILL"));
                        status_Text.setText(resultSet.getString("STATUS"));
                    }
                    
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
            
        });

        


        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay_Button) {
            
            String S_month_choice = month_Choice.getSelectedItem();
            
            try {
                if ( S_month_choice == null) {
                    JOptionPane.showMessageDialog(this, "Month selection is invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                database d1 = new database();
                d1.statement.executeUpdate("UPDATE Bill SET STATUS = 'PAID' WHERE METER_NO = '"+meter_no_pass+"' AND MONTH = '"+S_month_choice+"' LIMIT 1");
            } catch (Exception E) {
                E.printStackTrace();
            }
            setVisible(false);
            new Payment(meter_no_pass);
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Pay_bill("");
    }
    
}
