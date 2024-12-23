package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class Generate_bill extends JFrame implements ActionListener{
    String meter_no_pass;
    JTextArea textArea;
    Choice month_choice;
    JButton generate_bill_Button;
    Generate_bill(String meter_no_pass){
        super("Generate Bill Window");

        this.meter_no_pass = meter_no_pass;

        setBounds(470,30,468,665);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 450, 665);
        panel.setBackground(Color.white);
        add(panel);

        JLabel generate_bill_Label = new JLabel("Generate Bill");
        generate_bill_Label.setFont(new Font("serif", Font.BOLD, 20));
        generate_bill_Label.setBounds(30, 20, 160, 20);
        panel.add(generate_bill_Label);

        JLabel meter_no_text = new JLabel(meter_no_pass);
        meter_no_text.setFont(new Font("serif", Font.BOLD, 20));
        meter_no_text.setBounds(160, 20, 80, 20);
        panel.add(meter_no_text);


        month_choice = new Choice();
        month_choice.add("January");
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
        month_choice.setBounds(240, 20, 150, 20);
        panel.add(month_choice);


        textArea = new JTextArea(30,15);
        textArea.setText("\n--------------------------------- Click on the ------------------------------------\n--------------------------------- Generate Bill ----------------------------------");
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 50, 450, 550);
        panel.add(scrollPane);
        
        generate_bill_Button = new JButton("Submit");
        generate_bill_Button.setFont(new Font("serif",Font.PLAIN,18));
        generate_bill_Button.setBackground(new Color(138, 138, 92));
        generate_bill_Button.setBounds(0,600,450,25);
        generate_bill_Button.addActionListener(this);
        panel.add(generate_bill_Button);
        

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generate_bill_Button) {
            try {
                database d1 = new database();
                String S_month = month_choice.getSelectedItem();
                textArea.setText(" \t \tPower Limited \n \t Elctricity Bill for months of "+S_month+",2024");
                ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer WHERE METER_NO = '"+meter_no_pass+"'");
                while (resultSet.next()) {
                    textArea.append("\n   Customer Name           \t    :"+resultSet.getString("NAME"));
                    textArea.append("\n   Customer Meter Number   \t    :"+resultSet.getString("METER_NO"));
                    textArea.append("\n   Customer Adress         \t    :"+resultSet.getString("ADDRESS"));
                    textArea.append("\n   Customer City           \t    :"+resultSet.getString("CITY"));
                    textArea.append("\n   Customer State          \t    :"+resultSet.getString("STATE"));
                    textArea.append("\n   Customer Email          \t    :"+resultSet.getString("EMAIL"));
                    textArea.append("\n   Customer Phone          \t    :"+resultSet.getString("PHONE"));
                }
                resultSet = d1.statement.executeQuery("SELECT * FROM Meter_info WHERE METER_NUMBER ='"+meter_no_pass+"'");
                while (resultSet.next()) {
                    textArea.append("\n   Customer Meter Location  \t   :"+resultSet.getString("METER_LOCATION"));
                    textArea.append("\n   Customer Meter Type      \t   :"+resultSet.getString("METER_TYPE"));
                    textArea.append("\n   Customer Phase Code      \t   :"+resultSet.getString("PHASE_CODE"));
                    textArea.append("\n   Customer Bill Type       \t   :"+resultSet.getString("BILL_TYPE"));
                    textArea.append("\n   Customer Days            \t   :"+resultSet.getString("DAYS"));
                    
                }
                resultSet = d1.statement.executeQuery("SELECT * FROM Tax ");
                while (resultSet.next()) {
                    textArea.append("\n   Cost Per Unit            \t   :"+resultSet.getString("COST_PER_UNIT"));
                    textArea.append("\n   Meter Rent               \t   :"+resultSet.getString("METER_RENT"));
                    textArea.append("\n   Service Charge           \t   :"+resultSet.getString("SERVICE_CHARGE"));
                    textArea.append("\n   Service Tax              \t   :"+resultSet.getString("SERVICE_TAX"));
                    textArea.append("\n   Swacch Bharat Tax        \t   :"+resultSet.getString("SWACCH_BHARAT_TAX"));
                    textArea.append("\n   Fixed Tax                \t   :"+resultSet.getString("FIXED_TAX"));
                }
                resultSet = d1.statement.executeQuery("SELECT * FROM Bill WHERE METER_NO = '"+meter_no_pass+"' AND MONTH = '"+month_choice.getSelectedItem()+"'");
                while (resultSet.next()) {
                    textArea.append("\n   Current Month            \t   :"+resultSet.getString("MONTH"));
                    textArea.append("\n   Units Consumed           \t   :"+resultSet.getString("UNIT"));
                    textArea.append("\n   Total Charges            \t   :"+resultSet.getString("TOTAL_BILL"));
                    textArea.append("\n   Total Payable            \t   :"+resultSet.getString("TOTAL_BILL"));
                    
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Generate_bill("");
    }
    
}
