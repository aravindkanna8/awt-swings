package jsmSystem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class EmployeeDashboard  extends JFrame implements ActionListener,WindowListener {

	  JLabel lbl_title;
	  JLabel lbl_welcome,lbl_gold,lbl_silver;
	  JLabel lbl_GGram,lbl_SGram;
	  JTextField txt_gold,txt_silver;
	
	    JButton jbtn_update,jbtn_customer,jbtn_update_ad,jbtn_exchange,jbtn_jewelry,jbtn_order;
	    
	    
	    public EmployeeDashboard(String name,String uid){
	    	
	    	setTitle("EMPLOYEE DASHBOARD");
	    	//setLocationRelativeTo(null);
	    	
	    	setLayout(new BorderLayout());
            JLabel co=new JLabel(new ImageIcon("C:\\Users\\madhavan_vr\\Desktop\\images\\m3.jpg"));
	    	add(co);
	    	
	    	/*String on="C:\\Users\\madhavan_vr\\Desktop\\images\\owner.jpg";
	    	JLabel jewel=new JLabel(new ImageIcon(on));
	    	co.add(jewel);*/
	    	
	    	//jewel.setBounds(30,30,200,200);
	    	//jewel.setBorder();
	    	//co.setLayout(new FlowLayout());
	    	/*co.setAlignmentX(LEFT_ALIGNMENT);
	    	co.setAlignmentY(CENTER_ALIGNMENT);
	    	
	    	co.setSize(1550,830);*/
	    	
	    //	JFrame JForm = new JFrame();
	    	
	    	
	       // co=getContentPane();
	       // co.setLayout(null);
	    
	        
	      // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        lbl_title=new JLabel("EMPLOYEE DASHBOARD");
	    
	        String s1="WELCOME ";
	        String s2=name;
	        String s=s1+s2;
	        
	       lbl_welcome=new JLabel(s);
	        
	       lbl_gold=new JLabel("GOLD RATE :");
	       lbl_silver=new JLabel("SILVER RATE :");
	       
	       lbl_GGram=new JLabel("1 gram");
	       lbl_SGram=new JLabel("10 gram");
	       
	        Font f1 = new Font("Comic Sans MS",Font.ROMAN_BASELINE|Font.BOLD,45);
	        Font f3 = new Font("Times New Roman",Font.BOLD,25);
	        
	        
	        
	        lbl_title.setFont(f1);
	        lbl_welcome.setFont(f1);

	        jbtn_update=new JButton("UPDATE RATE ");
	        
	        jbtn_exchange=new JButton("EXCHANGE OF JEWELS");
	        jbtn_update_ad=new JButton("UPDATE ATTENDANCE");
	        jbtn_customer=new JButton("CUSTOMER DETAILS");
	        jbtn_jewelry=new JButton("JEWELRY");
	        jbtn_order=new JButton("PLACE NEW ORDER");
	      
	        
	        txt_gold=new JTextField("4515");
	        txt_silver=new JTextField("676.70");
	        
	        
	        txt_gold.setFont(f3);
	        txt_silver.setFont(f3);
	        lbl_gold.setFont(f3);
	        lbl_silver.setFont(f3);
	        lbl_GGram.setFont(f3);
	        lbl_SGram.setFont(f3);
	        
	        jbtn_customer.setFont(f3);
	        jbtn_update_ad.setFont(f3);
	        jbtn_exchange.setFont(f3);
	        jbtn_jewelry.setFont(f3);
	        jbtn_order.setFont(f3);
	        jbtn_update.setFont(f3);
	        
	      
	        jbtn_update.addActionListener(this);
	        
	        jbtn_customer.addActionListener(this);
	        jbtn_jewelry.addActionListener(this);
	        jbtn_order.addActionListener(this);
	        jbtn_update_ad.addActionListener(this);
	        jbtn_exchange.addActionListener(this);
	        
	        lbl_title.setBounds(600,10,750,80);
	        lbl_welcome.setBounds(400,120,700,50);
	        
	        jbtn_update.setBounds(20,350,400,40);
	        lbl_gold.setBounds(470,340,400,18);
	        lbl_silver.setBounds(470,379,400,18);
	        	        
	        
	        txt_gold.setBounds(660,334,250,30);
	        txt_silver.setBounds(660,376,250,30);

	        lbl_GGram.setBounds(912,334,300,30);
	        lbl_SGram.setBounds(912,378,300,30);
	        
	  
	       /* jbtn_owner.setBounds(1157,382,372,117);
	        jbtn_manager.setBounds(1157,482,372,117);
	        jbtn_employee.setBounds(1157,582,372,117);*/
	        
	        
	        
	        jbtn_customer.setBounds(20,550,450,35);
	        jbtn_exchange.setBounds(500,550,450,35);
	        jbtn_order.setBounds(1000,600,450,35);
	        
	        
	        jbtn_update_ad.setBounds(20,650,450,35);
	        jbtn_jewelry.setBounds(500,650,450,35);
	       
	        co.add(lbl_title);
	        co.add(lbl_welcome);
	        
	        co.add(jbtn_customer);
	        co.add(jbtn_update_ad);
	        co.add(jbtn_jewelry);
	        co.add(jbtn_exchange);
	        co.add(jbtn_order);
	        co.add(jbtn_update);
	           
	        co.add(lbl_GGram);
	        co.add(lbl_gold);
	        co.add(lbl_silver);
	        co.add(lbl_SGram);
	        co.add(lbl_welcome);
	        
	        co.add(txt_gold);
	        co.add(txt_silver);
	
	        
	 
	       // setSize(100,500);
	        setSize(1550,825);
	       addWindowListener(this);
	      
	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent ae)
	    {
	    	Object obj_source=ae.getSource();
	    	
	    	
	    	 if(obj_source==jbtn_update)
		        {
			       JewelRates ob=new JewelRates();
		        }
	    	 if(obj_source==jbtn_customer)
		        {
	    		 Cust_table ob=new Cust_table();
		        }
	    	  if(obj_source==jbtn_update_ad)
		        {
		        	
		        }
	    	  if(obj_source==jbtn_exchange)
		        {
		        	
		        }
	        if(obj_source==jbtn_jewelry)
	        {
	        ViewJewelry ob=new ViewJewelry();
	        }
	      
	        if(obj_source==jbtn_order)
	        {
	        	
	        }
	        
	    }

	    public static void main(String args[]){
	       new ManagerDashboard("","");
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