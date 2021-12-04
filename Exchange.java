package jsmSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exchange implements WindowListener {
    JFrame f;
    float fRate,grand_total;

    Exchange(String type,float total) {
        f = new JFrame("Exchange of Jewels");
        f.setBounds(500,500,550,330);
        f.setVisible(true);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.addWindowListener(this);

        final float[] rate = new float[1];
        JLabel lbl_title,lbl_weight,lbl_evalue,lbl_grams,lbl_evalueT;
        JTextField valueT;
        JButton submit;

        this.grand_total = total;

        Font f1 = new Font("Times New Roman",Font.BOLD,40);
        Font f2 = new Font("Times New Roman",Font.PLAIN,25);

        lbl_title = new JLabel("Exchange of Jewels");
        lbl_title.setFont(f1);
        lbl_title.setBounds(110,25,331,45);
        f.add(lbl_title);

        lbl_weight = new JLabel("Enter the weight of the jewel to be exchanged:");
        lbl_weight.setBounds(46,88,470,27);
        lbl_weight.setFont(f2);
        f.add(lbl_weight);

        lbl_grams = new JLabel("(grams)");
        lbl_grams.setFont(f2);
        lbl_grams.setBounds(280,139,78,27);
        f.add(lbl_grams);

        lbl_evalue = new JLabel("Exchange Value:");
        lbl_evalue.setFont(new Font("Times New Roman",Font.BOLD,30));
        lbl_evalue.setBounds(51,184,230,33);
        f.add(lbl_evalue);

        valueT = new JTextField();
        valueT.setFont(f2);
        valueT.setBounds(100,138,100,27);
        f.add(valueT);

        lbl_evalueT = new JLabel("0");
        lbl_evalueT.setBounds(285,176,163,50);
        lbl_evalueT.setFont(new Font("Times New Roman",Font.BOLD,30));
        f.add(lbl_evalueT);

        submit = new JButton("SUBMIT");
        submit.setBounds(198,220,154,62);
        submit.setFont(new Font("Times New Roman",Font.BOLD,30));
        f.add(submit);

        f.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsmSystem", "pass");
                    Statement st = con.createStatement();
                    String query = "select * from rates";
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        rate[0] = rs.getFloat(type);
                    }
                    con.close();
                }
                catch (Exception e1) {
                    System.out.println(e1);
                }
                fRate = Float.parseFloat(valueT.getText()) * rate[0];
                lbl_evalueT.setText(String.valueOf(fRate));
            }
        });
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {
        grand_total-=fRate;
        Checkout.cgst = (float) (grand_total * (0.015));
        Checkout.sgst = (float) (grand_total * (0.015));
        Checkout.totalTax = Checkout.cgst + Checkout.sgst;
        Checkout.totalAmt = grand_total + Checkout.totalTax;
        Checkout.exchangeRate = fRate;

        Checkout.unTaxRate = new String[]{"Taxable Rate", String.valueOf(Checkout.unTax)};
        Checkout.exchange = new String[]{"Exchange Value (-)", String.valueOf(Checkout.exchangeRate)};
        Checkout.fRate = new String[]{"Rate After Exchange(=)", String.valueOf(grand_total)};
        Checkout.cgst1 = new String[]{"Central GST (+)", String.valueOf(Checkout.cgst)};
        Checkout.sgst1 = new String[]{"State GST (+)", String.valueOf(Checkout.sgst)};
        Checkout.tot = new String[]{"Total Tax (=)", String.valueOf(Checkout.totalTax)};
        Checkout.grandT = new String[]{"GRAND TOTAL (=)", String.valueOf(Checkout.totalAmt)};

        Checkout.model.removeRow(7);
        Checkout.model.removeRow(6);
        Checkout.model.removeRow(5);
        Checkout.model.removeRow(4);
        Checkout.model.removeRow(3);
        Checkout.model.removeRow(2);
        Checkout.model.removeRow(1);

        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.unTaxRate);
        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.exchange);
        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.fRate);
        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.cgst1);
        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.sgst1);
        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.tot);
        Checkout.model.insertRow(Checkout.table.getRowCount(),Checkout.grandT);
    }
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}

