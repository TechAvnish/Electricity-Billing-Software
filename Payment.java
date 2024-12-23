package electricity.billing.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Payment extends JFrame implements ActionListener {
    
    JButton back_Button;
    String meter_no_pass;

    Payment(String meter_no_pass) {
        super("Payment Window");

        this.meter_no_pass = meter_no_pass;

        // Frame properties
        setBounds(300, 100, 800, 600); // Adjusted for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JEditorPane to display the webpage
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false); // Ensure the user cannot edit the content

        try {
            editorPane.setPage("https://paytm.com/online-payments"); // Set the URL
        } catch (Exception e) {
            e.printStackTrace();
            editorPane.setContentType("text/html");
            editorPane.setText("<html><h1>Error loading the page!</h1></html>");
        }

        // Add the editor pane to a scroll pane to handle large pages
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Back button at the top
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE);
        
        back_Button = new JButton("Back");
        back_Button.setFont(new Font("Serif", Font.PLAIN, 16));
        back_Button.setBackground(new Color(138, 138, 92));
        back_Button.addActionListener(this);
        topPanel.add(back_Button);

        // Add components to the frame
        add(topPanel, BorderLayout.NORTH); // Add the back button to the top
        add(scrollPane, BorderLayout.CENTER); // Add the scrollable web page to the center

        setVisible(true); // Make the frame visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle the back button click
        if (e.getSource() == back_Button) {
            setVisible(false);
            new Pay_bill(meter_no_pass); // Navigate to the previous frame
        }
    }

    public static void main(String[] args) {
        new Payment(""); // Pass an empty string for testing
    }
}

