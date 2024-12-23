package electricity.billing.system;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

// import java.awt.*;
import java.sql.ResultSet;

public class Bill_details extends JFrame{
    String meter_no_pass;
    Bill_details(String meter_no_pass){
        super("Bill Details Window");

        this.meter_no_pass = meter_no_pass;

        setBounds(400,100,600,500);
        // getContentPane().setBackground(new Color(102, 179, 255));
        setLayout(null);

        JTable table = new JTable();

        try {
            database d1 = new database();
            String query = "SELECT * FROM Bill WHERE METER_NO = '"+meter_no_pass+"'";
            ResultSet resultSet = d1.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,0,600,500);
        add(scrollPane);

        setVisible(true);
    }
    public static void main(String[] args) {
        new Bill_details("");
    }
}
