package jsmSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

public class Customer implements WindowListener{
    JFrame f;
    String id;
    Customer() {
        f = new JFrame("Customer Details");
        f.setSize(375,458);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.addWindowListener(this);

        JLabel lbl_title,lbl_name,lbl_address,lbl_city,lbl_pin,lbl_mob,lbl_aadhaar,lbl_mail;
        JTextField nameT,cityT,pinT,mobT,aadharT,mailT;
        JTextArea addT;
        JButton submit;

        Font f1 = new Font("Times New Roman",Font.BOLD,20);
        Font f2 = new Font("Times New Roman",Font.PLAIN,20);

        lbl_title = new JLabel("Add Customer Details");
        lbl_title.setBounds(93,43,190,22);
        lbl_title.setFont(f1);
        f.add(lbl_title);

        lbl_name = new JLabel("Name:");
        lbl_name.setBounds(25,85,58,22);
        lbl_name.setFont(f2);
        f.add(lbl_name);

        lbl_address = new JLabel("Address:");
        lbl_address.setBounds(25,127,76,22);
        lbl_address.setFont(f2);
        f.add(lbl_address);

        lbl_city = new JLabel("City:");
        lbl_city.setBounds(25,187,45,22);
        lbl_city.setFont(f2);
        f.add(lbl_city);

        lbl_pin = new JLabel("Pin:");
        lbl_pin.setBounds(218,187,32,22);
        lbl_pin.setFont(f2);
        f.add(lbl_pin);

        lbl_mob = new JLabel("Mobile Number:");
        lbl_mob.setBounds(25,229,139,22);
        lbl_mob.setFont(f2);
        f.add(lbl_mob);

        lbl_aadhaar = new JLabel("Aadhaar ID:");
        lbl_aadhaar.setBounds(25,271,135,22);
        lbl_aadhaar.setFont(f2);
        f.add(lbl_aadhaar);

        lbl_mail = new JLabel("Mail ID:");
        lbl_mail.setBounds(25,313,74,22);
        lbl_mail.setFont(f2);
        f.add(lbl_mail);

        nameT = new JTextField();
        nameT.setBounds(164,85,177,22);
        nameT.setFont(f2);
        f.add(nameT);

        addT = new JTextArea();
        addT.setBounds(164,127,177,40);
        addT.setFont(new Font("Times New Roman",Font.PLAIN,15));
        f.add(addT);

        cityT = new JTextField();
        cityT.setBounds(74,187,140,22);
        cityT.setFont(f2);
        f.add(cityT);

        pinT = new JTextField();
        pinT.setBounds(254,187,87,22);
        pinT.setFont(f2);
        f.add(pinT);

        mobT = new JTextField();
        mobT.setBounds(164,229,177,22);
        mobT.setFont(f2);
        f.add(mobT);

        aadharT = new JTextField();
        aadharT.setBounds(164,271,177,22);
        aadharT.setFont(f2);
        f.add(aadharT);

        mailT = new JTextField();
        mailT.setBounds(164,313,177,22);
        mailT.setFont(f2);
        f.add(mailT);

        submit = new JButton("Submit");
        submit.setBounds(126,350,123,55);
        submit.setFont(f1);
        f.add(submit);

        f.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean PlainText = nameT.getText().equals("") || addT.getText().equals("") || cityT.getText().equals("");

                if( !(PlainText) &&
                        Pattern.matches("^[^0-9]+$",nameT.getText()) &&
                        Pattern.matches("^[^0-9]+$",cityT.getText()) &&
                        Pattern.matches("^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$",pinT.getText()) &&
                        Pattern.matches("(0/91)?[6-9][0-9]{9}",mobT.getText()) &&
                        Pattern.matches("^[2-9]{1}[0-9]{11}$",aadharT.getText()) &&
                        Pattern.matches("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",mailT.getText()))
                    add(nameT.getText(),addT.getText(),cityT.getText(),pinT.getText(),mobT.getText(),aadharT.getText(),mailT.getText());
                else
                    JOptionPane.showMessageDialog(null,"Please check the data you have entered");
            }
        });
    }

    void add(String name1,String add1,String city1,String pin1,String mob1,String aadhaar1,String mail1) {
        int count = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsmSystem", "pass");
            Statement st = con.createStatement();

            String q1 = "select count(*) from customer";
            ResultSet rs = st.executeQuery(q1);

            while (rs.next()) {
                count = rs.getInt(1);
            }

            System.out.println(count);

            id = "CU000" + ++count;
            String query = "insert into Customer(id,name,address,city,pin,mobile,aadhaar,mail) values('" + id + "','" + name1 + "','" + add1 + "','" + city1 + "','" + pin1 +
                    "','" + mob1 + "','" + aadhaar1 + "','" + mail1 + "')";
            st.executeUpdate(query);
            con.close();
            JOptionPane.showMessageDialog(null, "Success!");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {
        Checkout.idT.setText(id);
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}