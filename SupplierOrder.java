package jsmSystem;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import database.DataBase1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupplierOrder extends JFrame implements WindowListener {
    JLabel f;
    String mail,name;
    SupplierOrder() {

        f = new JLabel(new ImageIcon("C:\\Users\\madhavan_vr\\Desktop\\images\\bg1.jpg"));
        add(f);
       // f.setBounds(200,200,550,330);
       // f.setVisible(true);
        f.setLayout(null);
      // f.setResizable(false);
       // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        JLabel supid,desc,quantity;
        JTextField sup_id,txt_desc,txt_quantity;
       
        Font titleF = new Font("Times New Roman",Font.BOLD,25);

        supid = new JLabel("SUPPLIER ID :");
        supid.setBounds(30,30,279,45);
        supid.setFont(new Font("Times New Roman",Font.BOLD,25));
        f.add(supid);

        desc = new JLabel("JEWEL DESCRIPTION :");
        desc.setBounds(30,105,285,45);
        desc.setFont(titleF);
        f.add(desc);

        quantity = new JLabel("QUANTITY :");
        quantity.setBounds(30,152,279,45);
        quantity.setFont(titleF);
        f.add(quantity);

        JButton submit = new JButton("PLACE ORDER");
        submit.setBounds(211,238,300,51);
        submit.setFont(new Font("Times New Roman",Font.BOLD,30));
        f.add(submit);

       

        sup_id = new JTextField();
        sup_id.setBounds(330,30,300,27);
        sup_id.setFont(titleF);
        f.add(sup_id);

        txt_desc = new JTextField();
        txt_desc.setBounds(330,109,300,27);
        txt_desc.setFont(titleF);
        
        f.add(txt_desc);
        
        txt_quantity = new JTextField();
        txt_quantity.setBounds(330,155,300,27);
        txt_quantity.setFont(titleF);
        
        f.add(txt_quantity);
        
        setBounds(700, 400,800,330);
        setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		
            String supid=sup_id.getText();
            String dp=txt_desc.getText();
            int quan=Integer.parseInt((txt_quantity.getText()));
            
            Connection con = DataBase1.getConnection();
            Statement stmt=con.createStatement();
       
            String q1="select * from supplier where sup_id="+supid;
            ResultSet rs=stmt.executeQuery(q1);
            while(rs.next())
            { 
               mail=rs.getString("sup_mailid");
                name=rs.getString("sup_name");
            }    
            System.out.print(mail);
            SupMail ob=new SupMail(mail,name, dp, quan);
            JOptionPane.showMessageDialog(null, "ORDER PLACED");
            	}
            	catch(Exception ex)
            	{
            	System.out.print(ex);	
            	}
            }
        });
    }
    
    public static void main(String[] args) {
      new SupplierOrder();
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