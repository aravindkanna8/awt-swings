package jsmSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JewelRates implements WindowListener{
	JFrame g;
    JLabel f;
    float t1,t2;
    JewelRates() {
    	g= new JFrame();
        f = new JLabel(new ImageIcon("C:\\Users\\madhavan_vr\\Desktop\\images\\bg1.jpg"));
        g.add(f);
        //f.setBounds(200,200,550,330);
       // f.setResizable(false);
        f.setLayout(null);
        g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font f1 = new Font("Times New Roman",Font.BOLD,30);
        Font f2 = new Font("Times New Roman",Font.PLAIN,25);

        JLabel desc = new JLabel("Enter the rate to be updated...");
        desc.setBounds(40,40,400,33);
        desc.setFont(f1);
        f.add(desc);

        JTextField rate = new JTextField();
        rate.setFont(f2);
        rate.setBounds(40,98,150,40);
        f.add(rate);

        JRadioButton gold,silver;

        gold = new JRadioButton("Gold",true);
        gold.setBounds(250,91,80,27);
        gold.setFont(f2);
        f.add(gold);

        silver = new JRadioButton("Silver");
        silver.setBounds(250,135,90,27);
        silver.setFont(f2);
        f.add(silver);

        ButtonGroup selection = new ButtonGroup();
        selection.add(gold);
        selection.add(silver);

        
       g.addWindowListener(this);
        
        
        JButton submit = new JButton("Update");
        submit.setBounds(184,200,182,73);
        submit.setFont(f1);
        f.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(gold.isSelected())
                        rateUpdate("gold",Float.parseFloat(rate.getText()));
                    else if(silver.isSelected())
                        rateUpdate("silver",Float.parseFloat(rate.getText()));

                }
                catch (Exception e1) {
                    rate.setText("");
                }
            }
        });
        g.setBounds(900,250,550,330);
        g.setVisible(true);
        //setBounds(900,250,550,330);
       // setVisible(true);
      
    }

    public static void main(String[] args) {
      //  new JewelRates();
    }

    void rateUpdate(String type,float rate) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsmSystem", "pass");
            Statement st = con.createStatement();
            String query = "update Rates set " + type + "=" + rate;
            st.executeQuery(query);
            String q1="select * from rates";
            ResultSet rs=st.executeQuery(q1);
            while(rs.next())
            { 
                System.out.println(rs.getFloat("gold") + " " + rs.getFloat("silver"));
                t1 = rs.getFloat("gold");
                t2 = rs.getFloat("silver");
            }    
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("HAI !!!");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		OwnerDashboard.txt_gold.setText(Float.toString(t1));
		OwnerDashboard.txt_silver.setText(Float.toString(t2));
		
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