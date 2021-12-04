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

class Sup_table extends JFrame implements ActionListener,WindowListener {

	private static final long serialVersionUID = 1L;
	JFrame f1=new JFrame();
	JLabel l1;
	JButton b1;
	String[] columns = new String[] {
            "SUPPLIER-ID", "NAME", "ITEM-TYPE", "MOBILE.NO", "MAIL-ID", "ADDRESS","CITY"
        };
	Object[][] Datas = new Object[][] {};
	Sup_table(){
		 f1.setTitle(" MANAGE SUPPLIER ");
		 f1.setLayout(null);
		
		 l1=new JLabel("SUPPLIER DETAILS");
		 l1.setBounds(650, 50, 300, 30);
		 Font f2 = new Font(Font.SANS_SERIF , Font.ROMAN_BASELINE | Font.BOLD , 24);
		 l1.setFont(f2);
		 f1.add(l1);
		 
		 DefaultTableModel model = new DefaultTableModel(Datas, columns);
		 JTable table = new JTable(model);
		 table.setRowHeight(table.getRowHeight()+20);
		 table.getColumnModel().getColumn(0).setPreferredWidth(5);
		 table.getColumnModel().getColumn(1).setPreferredWidth(5);
		 table.getColumnModel().getColumn(2).setPreferredWidth(5);
		 table.getColumnModel().getColumn(3).setPreferredWidth(10);
		 table.getColumnModel().getColumn(4).setPreferredWidth(5);
		 table.getColumnModel().getColumn(5).setPreferredWidth(5);
		 

		 
		 Font f3 = new Font(Font.SANS_SERIF , Font.ROMAN_BASELINE | Font.BOLD , 12);
		 
		 
		 table.setFont(f3);
		 
		 setData(model, table);
		 f1.add(new JScrollPane(table));
		 table.setBackground(new Color(255, 255, 153));//255, 228, 225
		 table.setForeground(new Color(51,51,51));
		 table.setBounds(50,100,1400,600);
		 f1.add(table);
		 b1=new JButton("ADD SUPPLIER");
		 b1.addActionListener(this); 
		 b1.setBounds(1100, 750, 150, 40);
		 f1.add(b1);
		 f1.setSize(1550,950);
		 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		 f1.setVisible(true);
		 addWindowListener(this);
		 
		 
		 }
		
		public static void setData(DefaultTableModel model, JTable table){
			 try {
				
			 Class.forName("oracle.jdbc.driver.OracleDriver");
	          Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","jsmsystem","pass");
			 String q ="select * from Supplier";
			 PreparedStatement p = con.prepareStatement(q);
			 ResultSet result = p.executeQuery();
			 while(result.next()){
			 Object[] rowData = {
			 result.getString("SUP_ID"),
			 result.getString("SUP_NAME"),
			 result.getString("SUP_TYPE"),
			 result.getString("SUP_MOBILE"),
			 result.getString("SUP_MAILID"),
			 result.getString("SUP_ADDRESS"),
			 result.getString("CITY")
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
			 new Sup_table();
			 }

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj_source=e.getSource();
				if(obj_source==b1) {
					Supplier ob=new Supplier();
				}// TODO Auto-generated method stub
				
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