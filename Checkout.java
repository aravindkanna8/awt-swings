package jsmSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.time.*;

public class Checkout {
//    public static String id;
    JFrame f;
    static JTable table;
    JComboBox itemList;
    static JTextField idT;
    static String mailid,name;
    static String[] tableColumns = {"DESCRIPTION","RATE"};
    static Object[][] tableRowData = new Object[][]{};

    static DefaultTableModel model = new DefaultTableModel(tableRowData,tableColumns) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    static float exchangeRate ,finalRate,cgst,sgst,totalTax,totalAmt,unTax;
    static String[] unTaxRate,exchange,cgst1,sgst1,tot,grandT,fRate;

    Checkout(String empID, ArrayList<String> items,float unTAX) {

        unTax = unTAX;

        cgst = (float) (unTAX * (0.015));
        sgst = (float) (unTAX * (0.015));
        totalTax = cgst + sgst;
        totalAmt = unTAX + totalTax;
        finalRate = totalAmt-exchangeRate;


        f = new JFrame("Checkout");
        f.setSize(1500,800);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lbl_title,lbl_custid,lbl_exchange,lbl_mode,lbl_summary;
        JRadioButton new_cust,exist_cust,cash,card;
        ButtonGroup cust,mode;
        JButton submit,purchase;

        Font f1 = new Font("Times New Roman",Font.BOLD,50);
        Font f2 = new Font("Times New Roman",Font.BOLD,40);

        JTextField temp1 = new JTextField();
        temp1.setBackground(Color.BLACK);
        temp1.setBounds(0,140,1500,10);
        f.add(temp1);

        lbl_title = new JLabel("CHECKOUT");
        lbl_title.setBounds(604,42,320,56);
        lbl_title.setFont(f1);
        f.add(lbl_title);

        lbl_custid = new JLabel("Enter Customer ID:");
        lbl_custid.setBounds(64,260,343,45);
        lbl_custid.setFont(f2);
        f.add(lbl_custid);
        lbl_custid.setVisible(false);

        lbl_exchange = new JLabel("Do you have a jewel to exchange?");
        lbl_exchange.setBounds(64,345,575,45);
        lbl_exchange.setFont(f2);
        f.add(lbl_exchange);

        lbl_mode = new JLabel("Mode of Payment");
        lbl_mode.setBounds(64,493,302,45);
        lbl_mode.setFont(f2);
        f.add(lbl_mode);

        lbl_summary = new JLabel("Summary");
        lbl_summary.setBounds(858,247,169,45);
        lbl_summary.setFont(f2);
        f.add(lbl_summary);

        idT = new JTextField();
        idT.setBounds(430,260,250,45);
        idT.setFont(new Font("Times New Roman",Font.PLAIN,30));
        f.add(idT);
        idT.setVisible(false);

        new_cust = new JRadioButton("New Customer");
        new_cust.setFont(f2);
        new_cust.setBounds(64,192,280,45);
        f.add(new_cust);

        exist_cust = new JRadioButton("Existing Customer");
        exist_cust.setFont(f2);
        exist_cust.setBounds(372,192,350,45);
        f.add(exist_cust);

        cust = new ButtonGroup();
        cust.add(new_cust);
        cust.add(exist_cust);

        cash = new JRadioButton("Cash");
        cash.setBounds(64,563,110,45);
        cash.setFont(new Font("Times New Roman",Font.PLAIN,40));
        cash.setActionCommand("Cash");
        f.add(cash);

        card = new JRadioButton("Card");
        card.setBounds(64,618,110,45);
        card.setFont(new Font("Times New Roman",Font.PLAIN,40));
        card.setActionCommand("Card");
        f.add(card);

        mode = new ButtonGroup();
        mode.add(cash);
        mode.add(card);

        submit = new JButton("Yes");
        submit.setBounds(309,410,82,48);
        submit.setFont(new Font("Times New Roman",Font.PLAIN,30));
        f.add(submit);

        purchase = new JButton("Purchase");
        purchase.setBounds(1047,663,200,60);
        purchase.setFont(f2);
        f.add(purchase);

        table = new JTable(model);
        table.setRowHeight(table.getRowHeight()+20);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(1);

        table.setBounds(858,302,578,339);
        table.setFont(new Font("Times New Roman",Font.PLAIN,30));
        table.setBackground(new Color(137, 130, 130));
        f.add(table);

        model.insertRow(table.getRowCount(),tableColumns);

        unTaxRate = new String[]{"Taxable Rate", String.valueOf(unTAX)};
        exchange = new String[]{"Exchange Value (-)", String.valueOf(exchangeRate)};
        fRate = new String[]{"Rate After Exchange (=)", String.valueOf(unTAX-exchangeRate)};
        cgst1 = new String[]{"Central GST (+)", String.valueOf(cgst)};
        sgst1 = new String[]{"State GST (+)", String.valueOf(sgst)};
        tot = new String[]{"Total Tax (=)", String.valueOf(totalTax)};
        grandT = new String[]{"GRAND TOTAL (=)", String.valueOf(totalAmt)};

//        String[] arr = new String[items.size()];
//
//        for (int j=0;j<items.size();j++) {
//            arr[j] = items.get(j);
//        }
//
//        Arrays.toString(arr);

        model.insertRow(table.getRowCount(),unTaxRate);
        model.insertRow(table.getRowCount(),exchange);
        model.insertRow(table.getRowCount(),fRate);
        model.insertRow(table.getRowCount(),cgst1);
        model.insertRow(table.getRowCount(),sgst1);
        model.insertRow(table.getRowCount(),tot);
        model.insertRow(table.getRowCount(),grandT);
//        model.insertRow(table.getRowCount(),arr);


        new_cust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Customer();
                idT.setVisible(true);
                lbl_custid.setVisible(true);
            }
        });

//        System.out.println(id);

        exist_cust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idT.setVisible(true);
                lbl_custid.setVisible(true);
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Exchange("Gold",unTAX);
            }
        });

        purchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cash.isSelected() || card.isSelected())
                    addReport(idT.getText().toUpperCase(),empID,items,exchangeRate,totalAmt,mode.getSelection().getActionCommand());
                else
                    JOptionPane.showMessageDialog(null,"Please enter mode of payment");
            }
        });
        f.setVisible(true);
    }

    void addReport(String custID,String empID,ArrayList<String> items,float exchangeValue,float totalAmt,String mode) {

        int billNo = 0;
        String date1 = java.time.LocalDate.now().toString();
//        System.out.println(date1);

        StringBuffer sb = new StringBuffer();
        for (String s : items) {
            sb.append(s);
            sb.append(",");
        }

        String str = sb.toString();
        //System.out.println(str);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsmSystem", "pass");
            Statement st = con.createStatement();

            String query1 = "select count(*) from reports";
            ResultSet rs = st.executeQuery(query1);

            while (rs.next())
                billNo = rs.getInt(1);
         //   System.out.println(billNo);

            String bill = "000" + ++billNo;

            String query2 = " insert into reports values('" + bill + "','" + custID + "','" + empID + "','" + str + "'," + exchangeValue + "," + totalAmt + ",to_date('" + date1 + "','yyyy/mm/dd'),'" + mode + "')";
//            System.out.println(query2);
            st.executeUpdate(query2);

            if(empID.contains("MA"))
                return;

            String query3 = "select performance from employee where id='" + empID + "'";
            ResultSet r1 = st.executeQuery(query3);

            int perf = 0;
            while (r1.next()) {
                perf = r1.getInt(1);
            }

            String query4 = "update employee set performance=" + ++perf + "where id='" + empID + "'";
            st.executeUpdate(query4);

            String query5 = "select * from customer where id='" + custID + "'";
            r1 = st.executeQuery(query5);

            int expense = 0;
            while (r1.next()) {
                expense = r1.getInt("expense");
                mailid=r1.getString("MAIL");
                name=r1.getString("NAME");
            }

            expense+= (int) totalAmt;

            String query6 = "update customer set expense=" + expense + "where id='" + custID + "'";
            st.executeUpdate(query6);
            
            CusMail ob1=new CusMail(mailid,name,expense);
            
            JOptionPane.showMessageDialog(null,"Transaction Successful");
        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}