package electricity.billing.system;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener{

    JLabel search_meter_no, search_name;
    Choice search_meter_choice, search_name_choice;
    JTable customer_Table;
    JButton search_Button,print_Button,close_Button;

    customer_details() {
        super("Customer Details Window");

        // Frame settings
        getContentPane().setBackground(new Color(102, 179, 255));
        setSize(750, 500);
        setLocation(350, 150);
        setLayout(new BorderLayout());

        // **Search Panel (NORTH)**
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null); // Using absolute positioning within the panel
        searchPanel.setPreferredSize(new Dimension(750, 120)); // Fixed height for the search panel
        searchPanel.setBackground(new Color(102, 179, 255));

        // Search by Meter Number
        search_meter_no = new JLabel("Search by Meter Number");
        search_meter_no.setFont(new Font("serif", Font.PLAIN, 15));
        search_meter_no.setBounds(10, 20, 160, 20);
        searchPanel.add(search_meter_no);

        search_meter_choice = new Choice();
        search_meter_choice.setBounds(190, 20, 150, 20);
        searchPanel.add(search_meter_choice);

        // Populate meter choices
        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer");
            while (resultSet.next()) {
                search_meter_choice.add(resultSet.getString("METER_NO"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // Search by Name
        search_name = new JLabel("Search by Name");
        search_name.setFont(new Font("serif", Font.PLAIN, 15));
        search_name.setBounds(420, 20, 150, 20);
        searchPanel.add(search_name);

        search_name_choice = new Choice();
        search_name_choice.setBounds(570, 20, 150, 20);
        searchPanel.add(search_name_choice);

        // Populate name choices
        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer");
            while (resultSet.next()) {
                search_name_choice.add(resultSet.getString("NAME"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // Add search panel to the NORTH region
        add(searchPanel, BorderLayout.NORTH);


        // **Table Panel (CENTER)**
        customer_Table = new JTable();
        
        try {
            database d1 = new database();
            ResultSet resultSet = d1.statement.executeQuery("SELECT * FROM New_Customer");
            customer_Table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add JTable inside a JScrollPane
        JScrollPane scroll_bar = new JScrollPane(customer_Table);
        scroll_bar.setBackground(Color.white);
        

        // Add JScrollPane to the CENTER region
        add(scroll_bar, BorderLayout.CENTER);

        search_Button = new JButton("Search");
        search_Button.setFont(new Font("serif",Font.PLAIN,15));
        search_Button.setBackground(new Color(138, 138, 92));
        search_Button.setBounds(20,80,80,20);
        search_Button.addActionListener(this);
        searchPanel.add(search_Button);

        print_Button = new JButton("Print");
        print_Button.setFont(new Font("serif",Font.PLAIN,15));
        print_Button.setBackground(new Color(138, 138, 92));
        print_Button.setBounds(150,80,80,20);
        print_Button.addActionListener(this);
        searchPanel.add(print_Button);

        close_Button = new JButton("Close");
        close_Button.setFont(new Font("serif",Font.PLAIN,15));
        close_Button.setBackground(new Color(138, 138, 92));
        close_Button.setBounds(600,80,80,20);
        close_Button.addActionListener(this);
        searchPanel.add(close_Button);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search_Button) {
            String search_query = "SELECT * FROM New_Customer WHERE METER_NO = '"+search_meter_choice.getSelectedItem()+"' AND NAME = '"+search_name_choice.getSelectedItem()+"'";
            try {
                database d1 = new database();
                ResultSet resultSet = d1.statement.executeQuery(search_query);
                customer_Table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if (e.getSource() == print_Button) {
            try {
                customer_Table.print();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new customer_details();
    }

   
}
