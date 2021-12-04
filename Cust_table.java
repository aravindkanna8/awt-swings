package jsmSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Cust_table extends JFrame implements WindowListener {

	
	private static final long serialVersionUID = 1L;
	JFrame f1=new JFrame();
	JLabel l1;
	JButton b1;
	
	String[] columns = new String[] {
            "CUSTOMER-ID", "NAME", "ADDRESS", "MOBILE.NO", "AADHAR-ID", "MAIL-ID","CITY"
        };
	Object[][] Datas = new Object[][] {};
	Cust_table(){
		
		 f1.setLayout(null);
		 
		 
		 l1=new JLabel("CUSTOMER DETAILS");
		 l1.setBounds(600, 50, 250, 30);
		 Font f2 = new Font(Font.SANS_SERIF , Font.ROMAN_BASELINE | Font.BOLD , 24);
		 l1.setFont(f2);
		 f1.add(l1);
		 
		 DefaultTableModel model = new DefaultTableModel(Datas, columns) {
			 @Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		 }; 
		 
		 JTable table = new JTable(model);
		 table.setRowHeight(table.getRowHeight()+20);
		 table.getColumnModel().getColumn(0).setPreferredWidth(5);
		 table.getColumnModel().getColumn(1).setPreferredWidth(5);
		 table.getColumnModel().getColumn(2).setPreferredWidth(5);
		 table.getColumnModel().getColumn(3).setPreferredWidth(10);
		 table.getColumnModel().getColumn(4).setPreferredWidth(5);
		 table.getColumnModel().getColumn(5).setPreferredWidth(5);
		 table.getColumnModel().getColumn(6).setPreferredWidth(5);
		 
		model.insertRow(table.getRowCount(), columns);
	
		
		 Font f3 = new Font(Font.SANS_SERIF , Font.ROMAN_BASELINE | Font.BOLD , 12);
		 
		 
		 table.setFont(f3);
		 
		 setData(model, table);
		 f1.add(new JScrollPane(table));
		// table.setBackground(new Color(255, 255, 153));
		// table.setForeground(new Color(51,51,51));
		 table.setBounds(50,100,1400,600);
		 f1.add(table);
		
		
		 
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 f1.setVisible(true);
		 f1.setSize(1650,950);
		 addWindowListener(this);
		 }
		
		public static void setData(DefaultTableModel model, JTable table){
			 try {
				
			 Class.forName("oracle.jdbc.driver.OracleDriver");
	          Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","jsmsystem","pass");
			 String q ="select * from Customer";
			 PreparedStatement p = con.prepareStatement(q);
			 ResultSet result = p.executeQuery();
			 while(result.next()){
			 Object[] rowData = {
			 result.getString("CUST_ID"),
			 result.getString("CUST_NAME"),
			 result.getString("CUST_ADDRESS"),
			 result.getString("CUST_MOBILE"),
			 result.getString("CUST_AADHAR_ID"),
			 result.getString("CUST_MAILID"),
			 result.getString("CUST_CITY")
			
			 };
			 model.insertRow(table.getRowCount(), rowData);
			 }
			 con.setAutoCommit(true);
			 con.close();
			 
				
			 }
			 catch (Exception e) {
			 System.out.println(e);
			 }
			 }

		
			 public static void main(String[] args) {
			 new Cust_table();
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