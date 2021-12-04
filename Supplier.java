package jsmSystem;

import java.util.regex.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import javax.swing.*;


import java.io.*;
class Supplier extends JFrame implements ActionListener,ItemListener,Serializable,WindowListener{
	
	private static final long serialVersionUID=1L;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTextField t1,t2,t4,t5,t6,t7;
	JRadioButton r1,r2;
	JButton b1;
	Container co;
	ButtonGroup btngroup;
	String sup_type="";
	Supplier(){
		 setTitle("SUPPLIER DETAILS");
		 co=getContentPane();
	     co.setLayout(null);

      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		l1=new JLabel("SUPPLIER-ID");
		l2=new JLabel("NAME");
		l3=new JLabel("ITEM TYPE");
		l4=new JLabel("MOBILE");
		l5=new JLabel("MAIL-ID");
		l6=new JLabel("ADDRESS");
		l7=new JLabel("CITY");
		
	 
	    
	    t1=new JTextField();   
        t2=new JTextField();
       
        t4=new JTextField();
        t5=new JTextField();
        t6=new JTextField();
        t7=new JTextField();
        
        btngroup=new ButtonGroup();

        r1=new JRadioButton("GOLD");
        r2=new JRadioButton("SILVER");

        btngroup.add(r1);
        btngroup.add(r2);
        r1.addItemListener(this);
        r2.addItemListener(this); 
     
        b1=new JButton("Submit");
       // b2=new JButton("VIEW");
	    b1.addActionListener(this); 
	   // b2.addActionListener(this); 
	    
        l1.setBounds(40, 30, 400, 30);  
        l2.setBounds(40, 70, 200, 30);  
        l3.setBounds(40, 110, 200, 30);  
        l4.setBounds(40, 190, 200, 30);  
        l5.setBounds(40, 230, 200, 30);
        l6.setBounds(40, 270, 200, 30);   
        l7.setBounds(40, 340, 200, 30);   
        

        t1.setBounds(200,30, 200, 30);
        t2.setBounds(200,70, 200, 30);
        r1.setBounds(200,110,100,30);
        r2.setBounds(200,150,100,30);
        t4.setBounds(200,190, 200, 30);
        t5.setBounds(200,230, 200, 30);
        t6.setBounds(200, 270, 200, 60);
        t7.setBounds(200, 340, 200, 30);   
        
              
	    b1.setBounds(200,400,100,30);
        //b2.setBounds(350,350,100,30);
        
        co.add(l1);
	    co.add(l2);
	    co.add(l3);
	    co.add(l4);
	    co.add(l5);
	    co.add(l6);
	    co.add(l7);
	   
	   
        co.add(t1);
        co.add(t2);
        co.add(r1);
        co.add(r2);
        co.add(t4);
        co.add(t5);
        co.add(t6);
        co.add(t7);
        
     addWindowListener(this);
      

        co.add(b1);
        //co.add(b2);
      // co.setBackground(Color.BLUE);
       //co.setFont(Times New MacRoman);
       setSize(500, 550);  
       setVisible(true);
	}	
	    
       public void itemStateChanged(ItemEvent ie){
    	   	
           ItemSelectable itemselected=ie.getItemSelectable();

           if(itemselected==r1){
                   sup_type="GOLD";
           }
           else if(itemselected==r2){
                   sup_type="SILVER";
           }
       
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj_source=e.getSource();
	       if(obj_source==b1){
	    	               
	      
		            try{
		            	 String sup_id = t1.getText();  
		                 String sup_name=t2.getText();
		                 String sup_mobile = t4.getText();
		                 String sup_mailid = t5.getText();  
		                 String sup_address=t6.getText();
		                 String sup_city=t7.getText();
		                 String regex1 = "^(.+)@(.+)$";
			                
			                Pattern pattern = Pattern.compile(regex1);
			                Matcher matcher = pattern.matcher(sup_mailid);
			                 boolean mailcheck=matcher.matches();
			                 
			                 Pattern p1= Pattern.compile("^\\d{6}$");
			                 Matcher m1=p1.matcher(sup_id);
			                 boolean idcheck=m1.matches();
			                
			                 
			                 Pattern p2 = Pattern.compile("^\\d{10}$");
			                 Matcher m2 = p2.matcher(sup_mobile);
			                 boolean phnocheck=m2.matches();
			                
			                	if(mailcheck==false)
			                	{
			                		JOptionPane.showMessageDialog(this, "INVALID EMAIL ID !");
			                	}
			                	else if(phnocheck==false)
			                	{
			                		JOptionPane.showMessageDialog(this, "INVALID MOBILE NUMBER");
			                	}
			                	else if(idcheck==false)
			                	{
			                		JOptionPane.showMessageDialog(this, "ENTER ONLY DIGITS");
			                	}
			                	else {


	                Class.forName("oracle.jdbc.driver.OracleDriver");
	                Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","jsmsystem","pass");
	                Statement stmt=con.createStatement();
	                String query="insert into supplier values('"+sup_id+"','"+sup_name+"','"+sup_type+"','"+sup_mobile+"','"+sup_mailid+"','"+sup_address+"','"+sup_city+"')";

	                stmt.executeUpdate(query);
	                con.setAutoCommit(true);
	                JOptionPane.showMessageDialog(co,"INCLUDED");
		            

	         }
		            }
	         catch(Exception ex){
	             JOptionPane.showMessageDialog(co, ex.toString());
	         }

	     }
	       }
	 public static void main(String args[]){
	     new Supplier();
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