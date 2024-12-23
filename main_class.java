package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class main_class extends JFrame implements ActionListener{
    String account_type;
    String M_number;
    main_class(String account_type, String M_number){

        this.account_type = account_type;
        this.M_number = M_number;

        // it is used to extend frame in full window
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // set the image in frame

        ImageIcon bg_image = new ImageIcon(ClassLoader.getSystemResource("icon/main_class_bg_image.jpg"));
        Image bg_One = bg_image.getImage().getScaledInstance(1370, 670,Image.SCALE_DEFAULT);
        ImageIcon bg_image2 = new ImageIcon(bg_One);
        JLabel imagLabel = new JLabel(bg_image2);
        add(imagLabel);

        // set the menu dialog box in the main class

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif",Font.PLAIN,20));
        

        JMenuItem new_customer = new JMenuItem("New Customer");
        new_customer.setFont(new Font("Sans-serif",Font.PLAIN,18));
        new_customer.addActionListener(this);
        menu.add(new_customer);

        JMenuItem customer_details = new JMenuItem("Customer Details");
        customer_details.setFont(new Font("Sans-serif",Font.PLAIN,18));
        customer_details.addActionListener(this);
        menu.add(customer_details);

        JMenuItem deposit_details = new JMenuItem("Deposit Details");
        deposit_details.setFont(new Font("Sans-serif",Font.PLAIN,18));
        deposit_details.addActionListener(this);
        menu.add(deposit_details);

        JMenuItem calculate_bill = new JMenuItem("Calculate Bill");
        calculate_bill.setFont(new Font("Sans-serif",Font.PLAIN,18));
        calculate_bill.addActionListener(this);
        menu.add(calculate_bill);

        // set the infomation dialog box in the main class

        JMenu info_bar = new JMenu("Information");
        info_bar.setFont(new Font("serif",Font.PLAIN,20));
        

        JMenuItem update_info_item = new JMenuItem("Update Information");
        update_info_item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        update_info_item.addActionListener(this);
        info_bar.add(update_info_item);

        JMenuItem view_info_Item = new JMenuItem("View Information");
        view_info_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        view_info_Item.addActionListener(this);
        info_bar.add(view_info_Item);

        // set the Users dialog box in the main class

        JMenu users_bar = new JMenu("Users");
        users_bar.setFont(new Font("serif",Font.PLAIN,20));
        

        JMenuItem pay_bill_Item = new JMenuItem("Pay Bill");
        pay_bill_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        pay_bill_Item.addActionListener(this);
        users_bar.add(pay_bill_Item);

        JMenuItem bill_details_Item = new JMenuItem("Bill Details");
        bill_details_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        bill_details_Item.addActionListener(this);
        users_bar.add(bill_details_Item);

        // set the Bill dialog box in the main class

        JMenu bill_bar = new JMenu("Bill");
        bill_bar.setFont(new Font("serif",Font.PLAIN,20));
         

        JMenuItem generate_biil_Item = new JMenuItem("Generate Bill");
        generate_biil_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        generate_biil_Item.addActionListener(this);
        bill_bar.add(generate_biil_Item);

        // set the Utility dialog box in the main class

        JMenu utility_bar = new JMenu("Utility");
        utility_bar.setFont(new Font("serif",Font.PLAIN,20));
        

        JMenuItem calculator_Item = new JMenuItem("Calculator");
        calculator_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        calculator_Item.addActionListener(this);
        utility_bar.add(calculator_Item);

        JMenuItem notepad_Item = new JMenuItem("Notepad");
        notepad_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        notepad_Item.addActionListener(this);
        utility_bar.add(notepad_Item);

        // set the Exit dialog box in the main class

        JMenu exit_bar = new JMenu("Exit");
        exit_bar.setFont(new Font("serif",Font.PLAIN,20));
        

        JMenuItem close_Item = new JMenuItem("Close");
        close_Item.setFont(new Font("Sans-serif",Font.PLAIN,18));
        close_Item.addActionListener(this);
        exit_bar.add(close_Item);

        if (account_type.equals("Admin")) {
            menuBar.add(menu);
        }else{
            menuBar.add(bill_bar);            
            menuBar.add(users_bar);
            menuBar.add(info_bar);
        }        
        menuBar.add(utility_bar);
        menuBar.add(exit_bar);

        // setSize(1530, 830);
        // setLocation(430, 200);
        // setLayout(new FlowLayout());
        setVisible(true);
    }
    public static void main(String[] args) {
        new main_class("","");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String message = e.getActionCommand();
        if (message.equals("New Customer")) {
            new new_customer();
        }else if (message.equals("Customer Details")) {
            new customer_details();
        }else if (message.equals("Deposit Details")) {
            new deposit();
        }else if (message.equals("Calculate Bill")) {
            new calculate_bill();
        }else if (message.equals("View Information")) {
            new view_info(M_number);
        }else if (message.equals("Update Information")) {
            new Update_info(M_number);
        }else if (message.equals("Bill Details")) {
            new Bill_details(M_number);
        }else if (message.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else if (message.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else if (message.equals("Close")) {
            setVisible(false);
            new Login();
        }else if (message.equals("Pay Bill")) {
            new Pay_bill(M_number);
        }else if (message.equals("Generate Bill")) {
            new Generate_bill(M_number);
        }
        
    }
}
