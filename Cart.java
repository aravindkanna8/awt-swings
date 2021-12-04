
package jsmSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;

public class Cart implements WindowListener{
	

    JFrame f;
    JTable table;
    static JLabel lbl_title,lbl_id,lbl_quantity,lbl_total,lbl_totalvalue;
    JTextField idT;
    static JComboBox quantity;
    JButton add,submit;

    String[] tableColumns = {"S.NO","DESCRIPTION","Quantity","HSN NO","GROSS WT","STONE WT","NET WT","METAL VALUE","VA","STONE VALUE","TAXABLE VALUE"};
    Object[][] tableRowData = new Object[][] {};
    DefaultTableModel model = new DefaultTableModel(tableRowData,tableColumns) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    static float grand_total=0;
    static ArrayList <String> items = new ArrayList<>();

    Cart(String empID) {
        f = new JFrame("Cart");
        f.setSize(1500,800);
        f.addWindowListener(this);
        f.setLayout(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField temp1 = new JTextField();
        temp1.setBackground(Color.BLACK);
        temp1.setBounds(0,140,1500,10);
        f.add(temp1);

        Font f1 = new Font("Times New Roman",Font.BOLD,50);
        Font f2 = new Font("Times New Roman",Font.BOLD,40);

        lbl_title = new JLabel("CART");
        lbl_title.setBounds(680,48,145,56);
        lbl_title.setFont(f1);
        f.add(lbl_title);

        lbl_id = new JLabel("Enter Ornament ID:");
        lbl_id.setFont(f2);
        lbl_id.setBounds(40,191,363,45);
        f.add(lbl_id);

        lbl_quantity = new JLabel("Quantity:");
        lbl_quantity.setBounds(703,191,175,50);
        lbl_quantity.setFont(f2);
        f.add(lbl_quantity);

        
        lbl_total = new JLabel("Grand Total");
        lbl_total.setBounds(996,640,223,45);
        lbl_total.setFont(f2);
        f.add(lbl_total);

        lbl_totalvalue = new JLabel("0");
        lbl_totalvalue.setBounds(1229,628,231,70);
        lbl_totalvalue.setFont(f2);
        f.add(lbl_totalvalue);

        table = new JTable(model);
        table.setRowHeight(table.getRowHeight()+20);
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
//        table.getColumnModel().getColumn(2).setPreferredWidth(5);
//        table.getColumnModel().getColumn(3).setPreferredWidth(5);
//        table.getColumnModel().getColumn(4).setPreferredWidth(5);
//        table.getColumnModel().getColumn(5).setPreferredWidth(5);
//        table.getColumnModel().getColumn(6).setPreferredWidth(5);
//        table.getColumnModel().getColumn(7).setPreferredWidth(5);
//        table.getColumnModel().getColumn(8).setPreferredWidth(5);
        table.getColumnModel().getColumn(10).setPreferredWidth(100);

        table.add(new Scrollbar());

        table.setBounds(40,271,1420,357);
        f.add(table);

        model.insertRow(table.getRowCount(),tableColumns);

        idT = new JTextField();
        idT.setBounds(408,191,205,45);
        idT.setFont(new Font("Times New Roman",Font.PLAIN,40));
        f.add(idT);

        String[] quants = {"1","2","3","4","5","6","7","8","9","10"};
        quantity = new JComboBox(quants);
        quantity.setFont(new Font("Times New Roman",Font.PLAIN,30));
        quantity.setBounds(875,191,100,45);
        f.add(quantity);

        add = new JButton("ADD");
        add.setFont(f2);
        add.setBounds(1200,184,150,60);
        f.add(add);

        submit = new JButton("CHECKOUT");
        submit.setFont(f1);
        submit.setBounds(584,648,333,74);
        f.add(submit);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setData(model,table,idT.getText());
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getRowCount()>1)
                    new Checkout(empID,items,grand_total);
                else
                    JOptionPane.showMessageDialog(null,"No items in cart");
            }
        });

        f.setVisible(true);
    }


    public static void setData(DefaultTableModel model, JTable table, String s) {
        float rate = 0,metalV,vaP,tot;
        int stock = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsmSystem", "pass");
            Statement st = con.createStatement();

            String query1 = "select * from Rates";
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {
                rate = rs.getFloat("Gold");
            }

            String query2 = "select * from goldjewel where ornament_id='" + s + "'";
            ResultSet result = st.executeQuery(query2);
            while (result.next()) {
                stock = result.getInt("STOCK_AVAILABILTY");
                if(stock<Integer.parseInt((String) quantity.getItemAt(quantity.getSelectedIndex()))) {
                    JOptionPane.showMessageDialog(null,"Stock Unavailable");
                    return;
                }

                items.add(result.getString("ornament_id"));

                metalV = rate * result.getFloat("NET_WT");
                vaP = metalV * (result.getFloat("VA")/100);
                tot = metalV + vaP + result.getFloat("STONE_VALUE");
                grand_total+=tot;

                Object[] rowData = {
                        table.getRowCount(),
                        result.getString("DESCRIPTION"),
                        quantity.getItemAt(quantity.getSelectedIndex()),
                        result.getString("HSN_NO"),
                        result.getString("GROSS_WT"),
                        result.getString("STONE_WT"),
                        result.getString("NET_WT"),
                        metalV,
                        vaP,
                        result.getString("STONE_VALUE"),
                        tot
                };
                model.insertRow(table.getRowCount(),rowData);
            }
            int balanceStock = stock - Integer.parseInt((String) quantity.getItemAt(quantity.getSelectedIndex()));
//            System.out.println(balanceStock);

            String quer1 = "update goldjewel set STOCK_AVAILABILTY=" + balanceStock + " where ornament_id='" + s + "'";
            st.executeUpdate(quer1);

            con.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        lbl_totalvalue.setText(String.valueOf(grand_total));
    }

    public static void main(String[] args) {
        new Cart("EM001");
    }


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
