package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meter_info extends JFrame implements ActionListener{

    JLabel heading,meter_no_Label,meter_location_Label,meter_type_Label,phase_code_Label,bill_type_Label,time_Label,note_Label,message_Label,meter_no_TextField;
    JButton submit_Button;
    Choice meter_location_Choice,meter_type_Choice,phase_code_Choice,bill_type_Choice;
    String meter_number;
    meter_info(String meter_number){
        super("Meter Information Window");
        this.meter_number = meter_number;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 179, 255));
        add(panel);

        heading = new JLabel("Meter Information");
        heading.setFont(new Font("serif",Font.BOLD,25));
        heading.setBounds(200,20,200,20);
        panel.add(heading);

        meter_no_Label = new JLabel("Meter Number");
        meter_no_Label.setFont(new Font("serif",Font.PLAIN,18));
        meter_no_Label.setBounds(70,100,120,20);
        panel.add(meter_no_Label);

        meter_no_TextField = new JLabel(meter_number);
        meter_no_TextField.setBounds(250,100,150,20);
        panel.add(meter_no_TextField);

        meter_location_Label = new JLabel("Meter Location");
        meter_location_Label.setFont(new Font("serif",Font.PLAIN,18));
        meter_location_Label.setBounds(70,140,120,20);
        panel.add(meter_location_Label);

        meter_location_Choice = new Choice();
        meter_location_Choice.add("Outside");
        meter_location_Choice.add("Inside");
        meter_location_Choice.setBounds(250,140,150,20);
        panel.add(meter_location_Choice);

        meter_type_Label = new JLabel("Meter Type");
        meter_type_Label.setFont(new Font("serif",Font.PLAIN,18));
        meter_type_Label.setBounds(70,180,120,20);
        panel.add(meter_type_Label);

        meter_type_Choice = new Choice();
        meter_type_Choice.add("Electric Meter");
        meter_type_Choice.add("Solar Meter");
        meter_type_Choice.add("Smart Meter");
        meter_type_Choice.setBounds(250,180,150,20);
        panel.add(meter_type_Choice);

        
        phase_code_Label = new JLabel("Phase Code");
        phase_code_Label.setFont(new Font("serif",Font.PLAIN,18));
        phase_code_Label.setBounds(70,220,120,20);
        panel.add(phase_code_Label);

        phase_code_Choice = new Choice();
        phase_code_Choice.add("011");
        phase_code_Choice.add("022");
        phase_code_Choice.add("033");
        phase_code_Choice.add("044");
        phase_code_Choice.add("055");
        phase_code_Choice.add("066");
        phase_code_Choice.add("077");
        phase_code_Choice.add("088");
        phase_code_Choice.add("099");
        phase_code_Choice.setBounds(250,220,150,20);
        panel.add(phase_code_Choice);


        bill_type_Label = new JLabel("Bill Type");
        bill_type_Label.setFont(new Font("serif",Font.PLAIN,18));
        bill_type_Label.setBounds(70,260,120,20);
        panel.add(bill_type_Label);

        bill_type_Choice = new Choice();
        bill_type_Choice.add("Normal");
        bill_type_Choice.add("Industrial");
        bill_type_Choice.setBounds(250,260,150,20);
        panel.add(bill_type_Choice);

        time_Label = new JLabel("30 Days Billing Time...");
        time_Label.setFont(new Font("serif",Font.BOLD,15));
        time_Label.setBounds(70,300,150,20);
        panel.add(time_Label);

        note_Label = new JLabel("Note-");
        note_Label.setFont(new Font("serif",Font.BOLD,15));
        note_Label.setBounds(70,340,150,20);
        panel.add(note_Label);

        message_Label = new JLabel("By default bill is calculated for 30 days only");
        message_Label.setFont(new Font("serif",Font.BOLD,15));
        message_Label.setBounds(70,360,350,20);
        panel.add(message_Label);

        submit_Button = new JButton("Submit");
        submit_Button.setFont(new Font("serif",Font.PLAIN,18));
        submit_Button.setBounds(260,410,100,25);
        submit_Button.setBackground(new Color(138, 138, 92));
        submit_Button.addActionListener(this);
        panel.add(submit_Button);

        ImageIcon meter_image = new ImageIcon(ClassLoader.getSystemResource("icon/meter_info_image.jpg"));
        Image meter_One = meter_image.getImage().getScaledInstance(320, 465,Image.SCALE_DEFAULT);
        ImageIcon meter_image2 = new ImageIcon(meter_One);
        JLabel imagLabel = new JLabel(meter_image2);
        imagLabel.setBounds(420,0,320,465);
        panel.add(imagLabel); 

        setSize(750, 500);
        setLocation(350, 150);
        setVisible(true);
    }
    public static void main(String[] args) {
        new meter_info("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit_Button) {
            String S_meter_no = meter_number;
            String S_meter_location = meter_location_Choice.getSelectedItem();
            String S_meter_type = meter_type_Choice.getSelectedItem();
            String S_phase_code = phase_code_Choice.getSelectedItem();
            String S_bill_type = bill_type_Choice.getSelectedItem();
            String S_day = "30";

            try {
                String meter_info_query = "INSERT INTO Meter_info (METER_NUMBER,METER_LOCATION,METER_TYPE,PHASE_CODE,BILL_TYPE,DAYS) VALUES ('"+S_meter_no+"','"+S_meter_location+"','"+S_meter_type+"','"+S_phase_code+"','"+S_bill_type+"','"+S_day+"')";
                database d1 = new database();
                d1.statement.executeUpdate(meter_info_query);
                JOptionPane.showMessageDialog(null,"Meter information added successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }
}
