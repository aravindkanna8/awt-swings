package jsmSystem;
import java.text.SimpleDateFormat;  
import java.util.Date; 
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

import database.DataBase1;

public class JewelryModule  extends JFrame implements ActionListener {

	  JLabel lbl_product_id,lbl_product_type,lbl_sup_id,lbl_desc,lbl_hsn,lbl_gross_wt,lbl_stone_wt,lbl_net_wt,lbl_stone_value,lbl_VA,lbl_total,lbl_purity,lbl_p_date;
	    JTextField txt_product_id,txt_sup_id,txt_desc,txt_hsn,txt_gross_wt,txt_stone_wt,txt_net_wt,txt_stone_value,txt_total,txt_VA,txt_purity,txt_p_date;
	
	    JComboBox jcb_product_type;
	    JButton jbtn_submit;
	  //  Container co;
	  
	    public  JewelryModule (){
	    	
	    	setTitle("Background Color for JFrame");
	    	//setLocationRelativeTo(null);
	    	
	    	setLayout(new BorderLayout());
            JLabel co=new JLabel(new ImageIcon("C:\\Users\\madhavan_vr\\Desktop\\images\\bg1.jpg"));
	    	add(co);
	    	
	    	//co.setLayout(new FlowLayout());
	    	
	    	
	    	JFrame JForm = new JFrame();
	       // co=getContentPane();
	       // co.setLayout(null);	    
	        
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        lbl_product_id=new JLabel("PRODUCT ID");
	        lbl_product_type=new JLabel("PRODUCT TYPE");
	        lbl_sup_id=new JLabel("SUPPLIER ID");
	        lbl_desc=new JLabel("DESCRIPTION");
	        lbl_hsn=new JLabel("HSN NUMBER");
	        lbl_gross_wt=new JLabel("GROSS WEIGHT");
	        lbl_stone_wt=new JLabel("STONE WEIGHT");
	        lbl_net_wt=new JLabel("NET WEIGHT");
	        lbl_stone_value=new JLabel("STONE VALUE");
	        lbl_VA =new JLabel("VALUE ADDED:(in % )");
	        lbl_total=new JLabel("STOCK AVAILABILITY");
	        lbl_purity=new JLabel("PURITY(22K or 24K )");
	        lbl_p_date=new JLabel("PURCHASING DATE (dd/mm/yyyy)");
	        
	        
	        Font f3 = new Font(Font.SERIF, Font.BOLD|Font.ITALIC, 13);
	        lbl_product_id.setFont(f3);
	        lbl_product_type.setFont(f3);
	        lbl_sup_id.setFont(f3);
	        lbl_desc.setFont(f3);
	        lbl_hsn.setFont(f3);
	        lbl_gross_wt.setFont(f3);
	        lbl_stone_wt.setFont(f3);
	        lbl_net_wt.setFont(f3);
	        lbl_stone_value.setFont(f3);
	        lbl_VA.setFont(f3);
	        lbl_total.setFont(f3);
	        lbl_purity.setFont(f3);
	        lbl_p_date.setFont(f3);
	        
	        
	        txt_product_id=new JTextField();
	        txt_sup_id=new JTextField();
	        txt_desc=new JTextField();
	        txt_hsn=new JTextField();
	        txt_gross_wt=new JTextField();
	        txt_stone_wt=new JTextField();
	        txt_net_wt=new JTextField();
	        txt_stone_value=new JTextField();
	        txt_VA=new JTextField();
	        txt_total=new JTextField();
	        txt_purity=new JTextField();
	        txt_p_date=new JTextField();
	        
	        jcb_product_type=new JComboBox();
	        jcb_product_type.setBackground(new Color(245, 248, 225));
	        jcb_product_type.setFont(f3);

	        jcb_product_type.addItem("^^^^^^^");
	     //   jcb_product_type.addItem("Anklet");
	        jcb_product_type.addItem("Chain");
	        jcb_product_type.addItem("Bracelet");
	        jcb_product_type.addItem("Broocher");
	        jcb_product_type.addItem("Earing");
	        jcb_product_type.addItem("Necklace");
	        jcb_product_type.addItem("Ring");
	        
	        
	        jbtn_submit=new JButton("submit");

	       
	        jbtn_submit.addActionListener(this);
	        
	        lbl_product_id.setBounds(100, 50, 120, 25);
	        lbl_product_type.setBounds(100, 100, 120, 25);
	        lbl_sup_id.setBounds(100, 150, 120, 25);
	        lbl_desc.setBounds(100, 200, 120, 25);
	        lbl_hsn.setBounds(100, 250, 120, 25);
	        lbl_gross_wt.setBounds(100, 300, 120, 25);
	        lbl_stone_wt.setBounds(100, 350, 120, 25);
	        lbl_net_wt.setBounds(100, 400, 120, 25);
	        lbl_stone_value.setBounds(100, 450, 120, 25);
	        lbl_VA.setBounds(100, 500, 145, 25);
	        lbl_total.setBounds(100,550,145,25);
	        lbl_purity.setBounds(100,600,145,25);
	        lbl_p_date.setBounds(100,650,290,25);
	        
	        jbtn_submit.setBounds(100,700, 80, 25);
	      

	        txt_product_id.setBounds(280, 50, 120, 25);
	        jcb_product_type.setBounds(280,100,120,25);
	    	txt_sup_id.setBounds(280, 150, 120, 25);
	        txt_desc.setBounds(280, 200, 120, 25);
	        txt_hsn.setBounds(280, 250, 120, 25);
	        txt_gross_wt.setBounds(280, 300, 120, 25);
	        txt_stone_wt.setBounds(280, 350, 120, 25);
	        txt_net_wt.setBounds(280, 400, 120, 25);
	        txt_stone_value.setBounds(280, 450, 120, 25);
	        txt_VA.setBounds(280, 500, 120, 25);
	        txt_total.setBounds(280,550,120,25);
	        txt_purity.setBounds(280,600,120,25);
	        txt_p_date.setBounds(300,650,100,25);
	        


	        co.add(lbl_product_id);
	        co.add(lbl_product_type);
	        co.add(lbl_sup_id);
	        co.add(lbl_desc);
	        co.add(lbl_hsn);
	        co.add(lbl_gross_wt);
	        co.add(lbl_stone_wt);
	        co.add(lbl_net_wt);
	        co.add(lbl_stone_value);
	        co.add(lbl_VA);
	        co.add(lbl_total);
	        co.add(lbl_purity);
	        co.add(lbl_p_date);

	        co.add(jbtn_submit);
	        co.add(txt_product_id);
	        co.add(jcb_product_type);
	        co.add(txt_sup_id);
	        co.add(txt_desc);
	        co.add(txt_hsn);
	        co.add(txt_gross_wt);
	        co.add(txt_stone_wt);
	        co.add(txt_net_wt);
	        co.add(txt_stone_value);
	        co.add(txt_VA);
	        co.add(txt_total);
	        co.add(txt_purity);
	        co.add(txt_p_date);
	        
	 
	        
	        setSize(600,800);
	       // setSize(100,200);
	      
	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent ae)
	    {
	    	Object obj_source=ae.getSource();

	        if(obj_source==jbtn_submit){

	            try{
	            	
	                String str_pid=txt_product_id.getText();
	                String str_pt=jcb_product_type.getSelectedItem().toString();
	                String str_sid=txt_sup_id.getText();
	                String str_desc=txt_desc.getText();
	                String str_hsn=txt_hsn.getText();
	                float gwt=Float.parseFloat(txt_gross_wt.getText());
	                float swt=Float.parseFloat(txt_stone_wt.getText());
	                float net_wt=Float.parseFloat(txt_net_wt.getText());
	                float st_value=Float.parseFloat(txt_stone_value.getText());
	                float VA=Float.parseFloat(txt_VA.getText());
	                int stock=Integer.parseInt(txt_total.getText());
	                int purity=Integer.parseInt(txt_purity.getText());
	              String date=txt_p_date.getText();
	             // Date date1=new SimpleDateFormat("dd/MMM/yyyy").parse(date);  
	          //   String d[]=date.split("/");
	          //   System.out.println(d[0]+"   "+d[1]+"   "+d[2]);
	             //if(d)
	              System.out.println(date);
	                   Connection con = DataBase1.getConnection();
	                   Statement stmt=con.createStatement();
	              
	                    String query =  "insert into goldjewel values('" + str_pid + "','" + str_pt + "','" + str_sid + "','" + str_desc + "','" + str_hsn + "','" + gwt + "','" + swt + "','" + net_wt + "','" + st_value + "','" + VA + "'," + stock + "," + purity + ",to_date('" + date + "','dd/mm/yyyy'))";
	                  // String query="insert into goldjewel values("+str_pid+",'"+str_pt+"','"+str_sid+"','"+str_desc+"','"+str_hsn+"','"+gwt+"','"+swt+"','"+net_wt+"','"+st_value+"','"+VA+"','"+stock+"','"+purity+"',"+date1+")" ;
	                   stmt.executeUpdate(query);
	                   con.setAutoCommit(true);
	                   JOptionPane.showMessageDialog(this, "UPDATION SUCCESS");
	            }
	            catch(Exception ex){
	                JOptionPane.showMessageDialog(this, ex.toString());
	            }

	        }

	    }

	    public static void main(String args[]){
	        new JewelryModule();
}
}